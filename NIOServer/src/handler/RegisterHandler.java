package handler;

import java.nio.channels.SocketChannel;

import nio.core.NIOServer;
import nio.data.Packet;
import nio.interfaces.ICmd;

/**
 * <p>Title: 处理用户注册的业务</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-10
 */
public class RegisterHandler implements ICmd {

	@Override
	public int execute(NIOServer server, SocketChannel channel, Packet packet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void write() {
		// TODO Auto-generated method stub
		
	}

}
