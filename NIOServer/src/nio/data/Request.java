package nio.data;

import java.net.InetAddress;
import java.nio.channels.SocketChannel;

/**
 * <p>Title: 封装客户端的请求信息</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-10
 */
public class Request {
	
	// 客户端请求数据包
	private Packet packet;
	// 客户端连接通道
	private SocketChannel channel;
	// 附属消息attachment
	private Object object;
	
	public Request(SocketChannel channel){
		this.channel = channel;
	}
	
	public Packet getPacket() {
		return packet;
	}
	public void setPacket(Packet packet) {
		this.packet = packet;
	}
	public InetAddress getAddress() {
        return channel.socket().getInetAddress();
    }
    public int getPort() {
        return channel.socket().getPort();
    }
    public boolean isConnected() {
        return channel.isConnected();
    }
    public boolean isBlocking() {
        return channel.isBlocking();
    }
    public boolean isConnectionPending() {
        return channel.isConnectionPending();
    }
    public boolean getKeepAlive() throws java.net.SocketException {
        return channel.socket().getKeepAlive();
    }
    public int getSoTimeout() throws java.net.SocketException {
        return channel.socket().getSoTimeout();
    }
    public boolean getTcpNoDelay() throws java.net.SocketException {
        return channel.socket().getTcpNoDelay();
    }
    public boolean isClosed() {
        return channel.socket().isClosed();
    }
    public void attach(Object obj) {
        this.object = obj;
    }
    public Object attachment() {
        return object;
    }
}
