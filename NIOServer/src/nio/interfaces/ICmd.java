package nio.interfaces;

import java.nio.channels.SocketChannel;

import nio.data.Packet;
import nio.data.Response;

/**
 * <p>Title: 消息处理接口</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public interface ICmd {
	/**
	 * 根据消息号，在此实现对应的业务逻辑
	 * @param channel
	 * @param packet
	 * @return
	 */
	int execute(SocketChannel channel, Packet packet);
	/**
	 * 如果需要返回消息到客户端，则实现此方法
	 */
	void write(Response response);
}
