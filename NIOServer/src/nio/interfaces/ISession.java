package nio.interfaces;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-14
 */
public interface ISession {
	
	public enum SessionStatus {
		NULL, READING, WRITING, IDLE, INITIALIZE, CLOSING, CLOSED
		}
	
	/**
	 * 启动session
	 */
	public void start();

	/**
	 * Check if session is closed
	 * @return
	 */
	public boolean isClosed();

	/**
	 * Close session
	 */
	public void close();

	/**
	 * Return the remote end's InetSocketAddress
	 * @return
	 */
	public InetSocketAddress getRemoteSocketAddress();

	public InetAddress getLocalAddress();
	
	/**
	 * Return true if session is expired,session is expired beacause you set the
	 * sessionTimeout,if since session's last operation form now is over this
	 * vlaue,isExpired return true,and Handler.onExpired() will be invoked.
	 * @return
	 */
	public boolean isExpired();

	/**
	 * Check if session is idle
	 * @return
	 */
	public boolean isIdle();

	public long getSessionIdleTimeout();

	public void setSessionIdleTimeout(long sessionIdleTimeout);

	public long getSessionTimeout();

	public void setSessionTimeout(long sessionTimeout);

	public Object setAttributeIfAbsent(String key, Object value);
}