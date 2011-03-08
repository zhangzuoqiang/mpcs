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
import java.util.List;

import mpcs.libs.configs.ServerConfig;
import mpcs.libs.data.ByteArrayPacket;
import mpcs.libs.interfaces.ICommand;
import mpcs.libs.utils.MoreUtil;

public class NIOServer implements Runnable {

	public Selector selector;
	/**客户端缓存区**/
	protected ByteBuffer clientBuffer = ByteBuffer.allocate(ServerConfig.BUFFER_SIZE);
	/**解码**/
	protected CharsetDecoder decoder;
	/**在线用户列表**/
	public List<Integer> ids=new ArrayList<Integer>();
	/****/
	private HashMap<Integer,ICommand> commands=new HashMap<Integer,ICommand>();

	public NIOServer(int port) throws IOException {
		selector = this.getSelector(port);
		Charset charset = Charset.forName(ServerConfig.LOCAL_CHARSET);
		decoder = charset.newDecoder();
	}

	@Override
	public void run() {
	}
	
	public void registerCommand(int commandID,ICommand command){
		if(!commands.containsKey(commandID)){
			MoreUtil.trace("registeCommand  "+commandID+"  ,  "+command);
			commands.put(commandID, command);
		}
	}
	
	/**
	 * 获取Selector,创建无阻塞网络套接
	 * @param port
	 * @return
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

	/**
	 * 监听端口
	 */
	public void listen() {
		try {
			while(true){
				int num=selector.select();
				if (num > 0) {
					// 获得就绪信道的键迭代器
					Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
					while (iter.hasNext()) {
						SelectionKey key = iter.next();
						iter.remove();
						process(key);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 处理I/O事件
	 * @param key
	 * @throws IOException
	 */
	protected void process(SelectionKey key) throws IOException {
		if (key.isAcceptable()) { 
			// 接收新的连接请求
			ServerSocketChannel server = (ServerSocketChannel) key.channel();
			SocketChannel channel = server.accept();
			// 设置非阻塞模式
			channel.configureBlocking(false);
			// 注册读操作,以进行下一步的读操作
			channel.register(selector, SelectionKey.OP_READ);
		} else if (key.isReadable()) { 
			// 读信息
			read(key);
		} else if (key.isWritable()) { 
			// 写事件
			if(key.isValid()){
				ByteArrayPacket p=new ByteArrayPacket(100);
				p.writeInt(300);
				p.writeInt(400);
				p.writeString("野草工革革", "utf-8");
				send(key,p);
			}else {
				ByteArrayPacket p=new ByteArrayPacket(100);
				p.writeInt(600);
				p.writeInt(800);
				p.writeString("QQQQQQQ", "utf-8");
				send(key,p);
			}
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
		if(count>0){
			clientBuffer.flip();
			ByteArrayPacket packet=new ByteArrayPacket(clientBuffer);
			//读取包长
			int data_len=packet.readInt();
			MoreUtil.trace("data_len " + data_len);
			//读取命令号
			int command=packet.readShort();
			
			if(commands.containsKey(command)){
				ICommand comm=commands.get(command);
				int result = comm.execute(this, channel, packet);
				key.attach(result);
				//System.out.println("attachment  "+key.attachment().getClass());
			}
			
			clientBuffer.clear();
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
		
		//发送的bytes，4为数据包的长度信息，为int型，占用4个字节
		ByteBuffer bts=ByteBuffer.allocate(dataLen + 4);
		//写入数据包的长度
		bts.putInt(dataLen);
		//写入数据内容
		bts.put(buffer);
		
		if(bts.position()>0){
			bts.flip();
		}
		int l=channel.write(bts);
		bts.clear();
		buffer.clear();
		
		//注册读事件
		channel.register(selector, SelectionKey.OP_READ);
		//注销写事件
		key.interestOps(key.interestOps()&~SelectionKey.OP_WRITE);
		return l;
	}
}
