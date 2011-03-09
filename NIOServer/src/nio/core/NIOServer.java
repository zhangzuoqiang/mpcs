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
import nio.control.SwitchReadCtrl;
import nio.control.SwitchWriteCtrl;
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
			doRead(key);
		} else if (key.isWritable()) { // 写事件
			if(key.isValid()){
				doWrite(key);
			}
		}
	}
	/**
	 * 读取数据
	 * @param channel
	 * @throws IOException
	 */
	private void doRead(SelectionKey key){
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
			
			// 分发业务逻辑（读操作）
			SwitchReadCtrl.switchCmd(command, this, channel, packet, key);
			
			clientBuffer.clear();
		}
	}
	
	private void doWrite(SelectionKey key){
		
		// 写回客户端的消息号标志
		int writeCmd = (Integer) key.attachment();
		// 分发业务逻辑（写操作）
		SwitchWriteCtrl.switchCmd(writeCmd, key);
	}
	
	
	
	public static NIOServer getInstance(){
		return instance;
	}
}