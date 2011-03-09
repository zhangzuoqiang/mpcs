package nio.interfaces;

import java.nio.channels.SocketChannel;

import nio.core.NIOServer;
import nio.data.Packet;

/**
 * 消息处理接口
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-9
 */
public interface ICmd {
	/**
	 * <p>Title: 根据消息号，在此实现对应的业务逻辑</p>
	 * @param server
	 * @param channel
	 * @param packet
	 * @return
	 */
	int execute(NIOServer server, SocketChannel channel, Packet packet);
	/**
	 * <p>Title: 如果需要返回消息到客户端，则实现此方法</p>
	 */
	void write();
}
