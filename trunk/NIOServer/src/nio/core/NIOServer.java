package nio.core;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nio.configs.ServerConfig;
import nio.control.SwitchController;
import nio.data.Packet;
import nio.utils.MoreUtils;
/**
 * 服务器核心类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-9
 */
public class NIOServer implements Runnable{
	
	private static NIOServer instance;
	public Selector selector;
	protected ByteBuffer clientBuffer = ByteBuffer.allocate(ServerConfig.BUFFER_SIZE);
	public List<Integer> ids = new ArrayList<Integer>();
	
	/**
	 * 构造方法 
	 * @param port* 指定端口
	 */
	public NIOServer(int port) {
		instance = this;
		try {
			selector = this.getSelector(port);
		} catch (IOException e) {
			e.printStackTrace();
			MoreUtils.trace("Error occured in getSelector: " + e.getMessage());
		}
	}
	
	/**
	 * 获取Selector，创建无阻塞网络套接
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
	
	public void run() {
		MoreUtils.trace("Server started ...");
		MoreUtils.trace("listening on " + ServerConfig.LISTENNING_PORT);
		listen();
	}

	/**
	 * 监听端口
	 */
	public void listen() {
		try {
			while(true){
				int num = selector.select();
				if (num > 0) {
					Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
					while (iter.hasNext()) {
						SelectionKey key = iter.next();
						iter.remove();
						processIO(key);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 处理IO事件
	 * @param key
	 * @throws IOException
	 */
	protected void processIO(SelectionKey key) throws IOException {
		if (key.isAcceptable()) { // 接收请求
			ServerSocketChannel server = (ServerSocketChannel) key.channel();
			SocketChannel channel = server.accept();
			// 设置非阻塞模式
			channel.configureBlocking(false);
			channel.register(selector, SelectionKey.OP_READ);
		} else if (key.isReadable()) { // 读信息
			read(key);
		} else if (key.isWritable()) { // 写事件
			if(key.isValid()){
				Packet p = new Packet(100);
				p.writeInt(300);
				p.writeInt(400);
				p.writeString("客户端你好！", "utf-8");
				send(key, p);
			}
		}
	}
	/**
	 * 读取数据
	 * @param channel
	 * @throws IOException
	 */
	public void read(SelectionKey key){
		SocketChannel channel = (SocketChannel) key.channel();
		int count = 0;
		try {
			count = channel.read(clientBuffer);
		} catch (IOException e) {
			MoreUtils.trace("Error occured in read(clientBuffer): " + e.getMessage());
			key.cancel();
		}
		if(count > 0){
			clientBuffer.flip();
			Packet packet = new Packet(clientBuffer);
			// 读取包长
			packet.readInt();
			// 读取消息号
			int command = packet.readInt();
			
			// 分发业务逻辑
			SwitchController.switchCmd(command, this, channel, packet, key);
			
			clientBuffer.clear();
		}
	}
	/**
	 * 发送数据
	 * @param channel
	 * @param packet
	 * @throws IOException
	 */
	public int send(SelectionKey key, Packet packet) throws IOException{
		return send(key, packet.byteBuffer());
	}
	/**
	 * 发送数据
	 * @param key
	 * @param buffer
	 * @return 发出Bytes的总长度
	 * @throws IOException
	 */
	public int send(SelectionKey key, ByteBuffer buffer) throws IOException{
		SocketChannel channel = (SocketChannel) key.channel();
		
		//发送数据的实际长度
		int dataLen = buffer.limit() - buffer.remaining();
		
		if(buffer.position() > 0){
			buffer.flip();
		}
		
		//发送的bytes，4为数据包的长度信息，为int型，占用4个字节
		ByteBuffer bts = ByteBuffer.allocate(dataLen + 4);
		//写入数据包的长度
		bts.putInt(dataLen);
		//写入数据内容
		bts.put(buffer);
		
		if(bts.position()>0){
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
	
	public static NIOServer getInstance(){
		return instance;
	}
}