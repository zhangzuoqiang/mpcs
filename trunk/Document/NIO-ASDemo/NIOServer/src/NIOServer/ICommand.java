package NIOServer;

import java.nio.channels.SocketChannel;

/**
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-8
 */
public interface ICommand {
	public int execute(NIOServer server,SocketChannel channel,Packet packet);
	public void write();
}
