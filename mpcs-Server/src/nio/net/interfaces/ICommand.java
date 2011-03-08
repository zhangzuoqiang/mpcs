package nio.net.interfaces;

import java.nio.channels.SocketChannel;

import nio.net.Server;
import nio.net.data.ByteArrayPacket;

/**
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-8
 */
public interface ICommand {
	public int execute(Server server, SocketChannel channel, ByteArrayPacket packet);
	public void write();
}
