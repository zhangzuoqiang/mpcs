package nio.interfaces;

import java.net.InetAddress;
import java.nio.channels.SocketChannel;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-14
 */
public interface ISession {
	
	/**Session状态**/
	public static enum SessionStatus {
		NULL, READING, WRITING, IDLE, CLOSED
		}
	
	/**
	 * 获取状态
	 */
	public SessionStatus getStatus();
	
	/**
	 * 设置状态
	 * @param status
	 */
	public void setStatus(SessionStatus status);

	/**
	 * 检查session是否关闭
	 * @return
	 */
	public boolean isClosed();

	/**
	 * 关闭session
	 */
	public void close();
	
	/**
	 * 获取客户端地址
	 * @return
	 */
	public InetAddress getClientAddr();
	
	/**
	 * 是否过期
	 * @return
	 */
	public boolean isExpired();

	/**
	 * 是否空闲
	 * @return
	 */
	public boolean isIdle();
	
	/**
	 * 更新连接时间
	 */
	public void updateTime();
	
	/**
	 * 更新为最新一次连接通道SocketChannel
	 * @param socket
	 */
	public void updateSocket(SocketChannel socket);

}