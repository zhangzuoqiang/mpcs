package nio.interfaces;

import java.nio.channels.SocketChannel;

import nio.core.NIOServer;
import nio.data.Packet;

/**
 * 用户连接服务端接口
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-9
 */
public interface ICmd {
	int execute(NIOServer server, SocketChannel channel, Packet packet);
	void write();
}
