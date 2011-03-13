package nio.core;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;
import java.io.IOException;

import mpcs.model.BaseCmd;

/**
 * <p>Title: 回应器</p>
 * <p>Description: 封装向客户端发送的数据</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-12
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
	 * 直接发送BaseCmd格式的数据返回客户端
	 * @param cmd
	 * @throws IOException
	 */
	public void send(BaseCmd cmd) throws IOException{
		Packet packet = cmd.getPacket();
		send(packet);
	}
	
	/**
	 * 向客户端写数据，格式：先写数据长度Int型，再写要返回客户端的数据
	 * @param packet
	 * @param key
	 * @return 发出Bytes的总长度
	 * @throws IOException
	 */
	public int send(Packet packet) throws IOException{
		
		ByteBuffer buffer = packet.byteBuffer();
		// 获取连接客户端的通道
		SocketChannel channel = (SocketChannel) this.key.channel();
		//发送数据的实际长度
		int dataLen = buffer.limit() - buffer.remaining();
		
		if(packet.position() > 0){
			packet.flip();
		}
		
		//发送的bytes，4为数据包的长度信息，为int型，占用4个字节
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
		
		return len;
	}
}
