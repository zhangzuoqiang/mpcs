package nio.manager;

import java.net.InetAddress;
import java.nio.channels.SocketChannel;
import java.util.Date;

import nio.config.ServerConfig;
import nio.interfaces.ISession;

/**
 * <p>Title: Session 实体类</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-14
 */
public class Session implements ISession {
	
	private SessionStatus status;
	private SocketChannel socket;// 保存连接
	private long connTime;
	
	public Session(SessionStatus status, SocketChannel socket, long time){
		this.status = status;
		this.socket = socket;
		this.connTime = time;
	}
	
	@Override
	public SessionStatus getStatus() {
		return this.status;
	}

	@Override
	public boolean isClosed() {
		if (this.status == SessionStatus.CLOSED) {
			return true;
		}
		return false;
	}

	@Override
	public void close() {
		this.status = SessionStatus.CLOSED;
	}

	@Override
	public InetAddress getClientAddr() {
		return this.socket.socket().getInetAddress();
	}

	@Override
	public boolean isExpired() {
		long curTime = new Date().getTime();
		if (curTime - this.connTime >= ServerConfig.SESSION_TIMEOUT ) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isIdle() {
		if (this.status == SessionStatus.IDLE) {
			return true;
		}
		return false;
	}

	@Override
	public void setStatus(SessionStatus status) {
		this.status = status;
	}

	@Override
	public void updateTime() {
		this.connTime = new Date().getTime();
	}

	@Override
	public void updateSocket(SocketChannel socket) {
		this.socket = socket;
	}
}
