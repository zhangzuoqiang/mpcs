package nio.data;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import nio.core.NIOServer;

/**
 * <p>Title: 封装返回客户端的数据包</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-10
 */
public class Response {
	// 写回客户端的key
	private SelectionKey key;
	
	public Response(SelectionKey key){
		this.key = key;
	}
	
	public SelectionKey getKey() {
		return key;
	}
	
	/**
	 * 发送数据
	 * @return 发出Bytes的总长度
	 * @throws IOException
	 */
	public int send(Packet packet, SelectionKey key) throws IOException{
		return send(packet.byteBuffer(), key);
	}
	
	/**
	 * 发送数据
	 * @param buffer
	 * @return 发出Bytes的总长度
	 * @throws IOException
	 */
	public int send(ByteBuffer buffer, SelectionKey key) throws IOException{
		// 获取连接客户端的通道
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
		channel.register(NIOServer.getInstance().selector, SelectionKey.OP_READ);
		//注销写事件
		key.interestOps(key.interestOps()&~SelectionKey.OP_WRITE);
		return len;
	}
}
