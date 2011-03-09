package mpcs.libs.core;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import mpcs.libs.cmds.ConnectCmd;
import mpcs.libs.configs.ServerConfig;
import mpcs.libs.data.ByteArrayPacket;
import mpcs.libs.interfaces.ICommand;
import mpcs.libs.utils.MoreUtil;

public class NIOServer implements Runnable {
	
	private static Selector selector;
	private Notifier notifier;
	// 回应池
	private static List<SelectionKey> wpool = new LinkedList<SelectionKey>();
	// 客户端缓存区
	protected ByteBuffer clientBuffer = ByteBuffer.allocate(ServerConfig.BUFFER_SIZE);
	// 解码
	protected CharsetDecoder decoder;
	// 在线用户列表
	public List<Integer> ids = new ArrayList<Integer>();
	private HashMap<Integer,ICommand> commands = new HashMap<Integer,ICommand>();
	
	public NIOServer(int port) throws IOException {
		selector = this.getSelector(port);
		Charset charset = Charset.forName(ServerConfig.LOCAL_CHARSET);
		decoder = charset.newDecoder();
		// 获取事件触发器
        notifier = Notifier.getNotifier();
        
	}
	
	@Override
	public void run() {
		registerCommand(1000,new ConnectCmd());
		MoreUtil.trace("listening on " + ServerConfig.LISTENNING_PORT);
		try {
			listen();
		} catch (Exception e) {
			e.printStackTrace();
			notifier.fireOnError("Error occured in listen by run: " + e.getMessage());
		}
	}
	
	public void registerCommand(int commandID, ICommand command){
		if(!commands.containsKey(commandID)){
			MoreUtil.trace("registeCommand  " + commandID + "  ,  " + command);
			commands.put(commandID, command);
		}
	}
	
	/**
	 * 获取Selector,创建无阻塞网络套接
	 * @param port
	 * @return Selector
	 * @throws IOException
	 */
	protected Selector getSelector(int port) throws IOException {
		ServerSocketChannel server = ServerSocketChannel.open();
		Selector selector = Selector.open();
		server.socket().bind(new InetSocketAddress(port));
		server.configureBlocking(false);
		server.register(selector, SelectionKey.OP_ACCEPT);
		return selector;
	}
    
    /**获取选择器**/
    public Selector getSelector(){
    	return selector;
    }
	
	/**
	 * 监听端口
	 * @throws Exception 
	 */
	public void listen() throws Exception {
		try {
			while(true){
				int num=selector.select();
				if (num > 0) {
					// 获得就绪信道的键迭代器
					Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
					while (iter.hasNext()) {
						SelectionKey key = iter.next();
						iter.remove();
						processIO(key);
					}
				}else {
					addRegister(); // 在Selector中注册新的写通道
				}
			}
		} catch (IOException e) {
			notifier.fireOnError("Error occured in listen function: " + e.getMessage());
		}
	}
	
	/**
	 * 处理I/O事件
	 * @param key
	 * @throws Exception 
	 */
	protected void processIO(SelectionKey key) throws Exception {
		if (key.isAcceptable()) { 
			// 接收新的连接请求
			ServerSocketChannel server = (ServerSocketChannel) key.channel();
			notifier.fireOnAccept();
			SocketChannel channel = server.accept();
			// 设置非阻塞模式
			channel.configureBlocking(false);
			// 触发接受连接事件
            Request request = new Request(channel);
            notifier.fireOnAccepted(request);
			// 注册读操作,以进行下一步的读操作
			channel.register(selector, SelectionKey.OP_READ, request);
		} else if (key.isReadable()) {
			// 读信息
			read(key);
		} else if (key.isWritable()) { 
			// 写事件
			write(key);
		}
	}
	
	/**
	 * 读取客户端发送的数据
	 * @param channel
	 * @throws IOException
	 */
	public void read(SelectionKey key) throws IOException{
		SocketChannel channel = (SocketChannel) key.channel();
		int count = channel.read(clientBuffer);
		int command = 0;
		if(count>0){
			clientBuffer.flip();
			ByteArrayPacket packet = new ByteArrayPacket(clientBuffer);
			//读取包长
			int data_len = packet.readInt();
			MoreUtil.trace("data_len " + data_len);
			//读取命令号
			command = packet.readShort();
			
			if(commands.containsKey(command)){
				ICommand comm = commands.get(command);
				int result = comm.execute(this, channel, packet);
				key.attach(result);
			}
			clientBuffer.clear();
		}
	}
	
	/**
	 * 写处理
	 * @param key
	 * @throws IOException
	 */
	public void write(SelectionKey key) throws IOException{
		if(key.isValid()){ // 测试有效性
			ByteArrayPacket p = new ByteArrayPacket(100);
			p.writeInt(300);
			p.writeInt(400);
			p.writeString("获得服务器返回数据", "utf-8");
			send(key,p);
		}
	}
	
	/**
	 * 发送数据
	 * @param channel
	 * @param packet
	 * @throws IOException
	 */
	public int send(SelectionKey key,ByteArrayPacket packet) throws IOException{
		return send(key,packet.byteBuffer());
	}
	
	/**
	 * 发送数据
	 * @param key
	 * @param buffer
	 * @return 发出Bytes的总长度
	 * @throws IOException
	 */
	public int send(SelectionKey key,ByteBuffer buffer) throws IOException{
		SocketChannel channel = (SocketChannel) key.channel();
		
		//发送数据的实际长度
		int dataLen=buffer.limit()-buffer.remaining();
		
		if(buffer.position()>0){
			buffer.flip();
		}
		
		//发送的bytes，4为数据包的长度信息，int型，占用4个字节
		ByteBuffer bts = ByteBuffer.allocate(dataLen + 4);
		//写入数据包的长度
		bts.putInt(dataLen);
		//写入数据内容
		bts.put(buffer);
		
		if(bts.position() > 0){
			bts.flip();
		}
		int len = channel.write(bts);
		bts.clear();
		buffer.clear();
		
		//注册读事件
		channel.register(selector, SelectionKey.OP_READ);
		//注销写事件
		key.interestOps(key.interestOps()&~SelectionKey.OP_WRITE);
		return len;
	}
	
	 /**
     * 添加新的通道注册
     */
    private void addRegister() {
        synchronized (wpool) {
            while (!wpool.isEmpty()) {
                SelectionKey key = (SelectionKey) wpool.remove(0);
                SocketChannel schannel = (SocketChannel)key.channel();
                try {
                    schannel.register(selector,  SelectionKey.OP_WRITE, key.attachment());
                }catch (Exception e) {
                    try {
                        schannel.finishConnect();
                        schannel.close();
                        schannel.socket().close();
                        notifier.fireOnClosed((Request)key.attachment());
                    }catch (Exception ep) {
                    	notifier.fireOnError("Error occured in close channel: " + e.getMessage());
                    }
                    notifier.fireOnError("Error occured in addRegister: " + e.getMessage());
                }
            }
        }
    }
    
    /**
     * 提交新的客户端写请求于主服务线程的回应池中
     * @param key
     */
    public static void processWriteRequest(SelectionKey key) {
        synchronized (wpool) {
            wpool.add(wpool.size(), key);
            wpool.notifyAll();
        }
        // 解除selector的阻塞状态，以便注册新的通道
        selector.wakeup();
    }
}