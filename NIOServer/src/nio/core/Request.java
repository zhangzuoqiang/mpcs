package nio.core;

import java.net.SocketException;
import java.nio.channels.SocketChannel;

/**
 * <p>Title: 封装客户端请求的信息</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-12
 */
public class Request {
	// 请求通道
    private SocketChannel sc;
    // 封装请求数据
    private Packet packet;
    // 保存附属信息
    private Object obj;
    
    public Request(SocketChannel sc) {
        this.sc = sc;
    }
    public java.net.InetAddress getAddress() {
        return sc.socket().getInetAddress();
    }
    public int getPort() {
        return sc.socket().getPort();
    }
    public boolean isConnected() {
        return sc.isConnected();
    }
    public boolean isBlocking() {
        return sc.isBlocking();
    }
    public boolean isConnectionPending() {
        return sc.isConnectionPending();
    }
    public boolean getKeepAlive() throws SocketException {
        return sc.socket().getKeepAlive();
    }
    public int getSoTimeout() throws SocketException {
        return sc.socket().getSoTimeout();
    }
    public boolean getTcpNoDelay() throws SocketException {
        return sc.socket().getTcpNoDelay();
    }
    public boolean isClosed() {
        return sc.socket().isClosed();
    }
    public void attach(Object obj) {
        this.obj = obj;
    }
    public Object attachment() {
        return obj;
    }
	public Packet getPacket() {
		return packet;
	}
	public void setPacket(Packet packet) {
		this.packet = packet;
	}
}
