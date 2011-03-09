package nio.core;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import nio.data.Packet;
import nio.interfaces.ICmd;
/**
 * 服务器核心类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-9
 */
public class NIOServer {
	
	private static int BLOCK = 1024;
	public Selector selector;
	protected ByteBuffer clientBuffer = ByteBuffer.allocate(BLOCK);
	protected CharsetDecoder decoder;
	public static CharsetEncoder encoder = Charset.forName("GB2312").newEncoder();
	
	public HashMap<Integer,SelectionKey> rooms=new HashMap<Integer,SelectionKey>();
	public List<Integer> ids=new ArrayList<Integer>();
	private HashMap<Integer,ICmd> commands=new HashMap<Integer,ICmd>();
	
	public final int CONNECT=1000;
	public final int SELECT_ROOM=1005;
	public final int START=1010;
	public final int RECEIVE_DATA=1015;
	
	public final int CONNECT_SUCCESS=2000;
	public final int SEND_DATA=2005;

	public NIOServer(int port) throws IOException {
		selector = this.getSelector(port);
		Charset charset = Charset.forName("GB2312");
		decoder = charset.newDecoder();
	}
	
	public void registerCommand(int commandID,ICmd command){
		if(!commands.containsKey(commandID)){
			System.out.println("registeCommand  "+commandID+"  ,  "+command);
			commands.put(commandID, command);
		}
	}

	// 获取Selector
	protected Selector getSelector(int port) throws IOException {
		ServerSocketChannel server = ServerSocketChannel.open();
		Selector selector = Selector.open();
		server.socket().bind(new InetSocketAddress(port));
		server.configureBlocking(false);
		server.register(selector, SelectionKey.OP_ACCEPT);
		return selector;
	}

	// 监听端口
	public void listen() {
		try {
			while(true){
				int num=selector.select();
				if (num > 0) {
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

	// 处理事件
	protected void process(SelectionKey key) throws IOException {
		if (key.isAcceptable()) { // 接收请求
			ServerSocketChannel server = (ServerSocketChannel) key.channel();
			SocketChannel channel = server.accept();
			// 设置非阻塞模式
			channel.configureBlocking(false);
			channel.register(selector, SelectionKey.OP_READ);
		} else if (key.isReadable()) { // 读信息
			read(key);
			//clientBuffer.clear();
		} else if (key.isWritable()) { // 写事件
			if(key.isValid()){
				//SocketChannel channel = (SocketChannel) key.channel();
				Packet p=new Packet(100);
				p.writeInt(300);
				p.writeInt(400);
				p.writeString("野草工革革", "utf-8");
				send(key,p);
				//int attachment=(Integer)key.attachment();
			}
		}
	}
	/**
	 * 读取数据
	 * @param channel
	 * @throws IOException
	 */
	public void read(SelectionKey key) throws IOException{
		SocketChannel channel = (SocketChannel) key.channel();
		int count = channel.read(clientBuffer);
		if(count>0){
			clientBuffer.flip();
			Packet packet=new Packet(clientBuffer);
			//读取包长
			int data_len=packet.readInt();
			//读取命令号
			int command=packet.readShort();
			
			if(commands.containsKey(command)){
				ICmd comm=commands.get(command);
				int result=comm.execute(this, channel, packet);
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
	public int send(SelectionKey key,Packet packet) throws IOException{
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
		ByteBuffer bts=ByteBuffer.allocate(dataLen+4);
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
	
	public static void main(String[] args) {
		int port = 8088;
		try {
			NIOServer server = new NIOServer(port);
			server.registerCommand(1000,new ConnectCmd());
			System.out.println("listening on " + port);

			server.listen();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}