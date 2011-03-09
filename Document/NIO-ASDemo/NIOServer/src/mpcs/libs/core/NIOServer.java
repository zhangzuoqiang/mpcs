package mpcs.libs.core;

import java.io.IOException;
import java.net.InetSocketAddress;
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
	private static NIOServer instance;
	// 回应池
	private static List<SelectionKey> wpool = new LinkedList<SelectionKey>();
	// 解码
	protected CharsetDecoder decoder;
	// 在线用户列表
	public List<Integer> ids = new ArrayList<Integer>();
	public HashMap<Integer,ICommand> commands = new HashMap<Integer,ICommand>();
	
	public NIOServer(int port) throws IOException {
		instance = this;
		selector = this.getSelector(port);
		Charset charset = Charset.forName(ServerConfig.LOCAL_CHARSET);
		decoder = charset.newDecoder();
		// 获取事件触发器
        notifier = Notifier.getNotifier();
        // 创建读/写线程池
        for (int i = 0; i < ServerConfig.MAX_THREADS; i++) {
            Thread reader = new Reader();
            Thread writer = new Writer();
            reader.start();
            writer.start();
        }
	}
	
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
	
	/**
	 * 注册command
	 * @param commandID
	 * @param command
	 */
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
            notifier.fireOnAccepted((ByteArrayPacket) key.attachment());
			// 注册读操作,以进行下一步的读操作
			channel.register(selector, SelectionKey.OP_READ);
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
		// 提交读服务线程读取客户端数据
		Reader.processRequest(key);
        key.cancel();//将本socket的事件在选择器中删除
	}
	
	/**
	 * 写处理
	 * @param key
	 * @throws IOException
	 */
	public void write(SelectionKey key) throws IOException{
		if(key.isValid()){ // 测试有效性
			// 提交写服务线程向客户端发送回应数据
			Writer.processRequest(key);  
            key.cancel();
		}
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
                        notifier.fireOnClosed((ByteArrayPacket) key.attachment());
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
    
    /**
     * 获取NIOServer单例
     * @return
     */
    public static NIOServer getInstance(){
    	return instance;
    }
}