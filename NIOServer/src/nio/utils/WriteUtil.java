package nio.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import nio.core.NIOServer;
import nio.data.Packet;

/**
 * 写回客户端的工具类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-9
 */
public class WriteUtil {
	
	/**
	 * 发送数据
	 * @param channel
	 * @param packet
	 * @throws IOException
	 */
	public static int send(SelectionKey key, Packet packet) throws IOException{
		return send(key, packet.byteBuffer());
	}
	
	/**
	 * 发送数据
	 * @param key
	 * @param buffer
	 * @return 发出Bytes的总长度
	 * @throws IOException
	 */
	public static int send(SelectionKey key, ByteBuffer buffer) throws IOException{
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
