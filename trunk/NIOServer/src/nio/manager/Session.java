package nio.manager;

import java.net.InetAddress;
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
	private InetAddress addr;
	private long connTime;
	
	public Session(SessionStatus status, InetAddress addr, long time){
		this.status = status;
		this.addr = addr;
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
		return this.addr;
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
}
