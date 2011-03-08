package mpcs.libs.interfaces;

import java.nio.channels.SocketChannel;

import mpcs.libs.data.ByteArrayPacket;

import NIOServer.NIOServer;

/**
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-8
 */
public interface ICommand {
	public int execute(NIOServer server, SocketChannel channel, ByteArrayPacket packet);
	public void write();
}
