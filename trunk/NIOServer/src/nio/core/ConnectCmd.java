package nio.core;

import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import nio.data.Packet;
import nio.interfaces.ICmd;
import nio.utils.MoreUtils;

/**
 * 限制客户端为单一登录，完成连接验证（3次握手）
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-9
 */
public class ConnectCmd implements ICmd {

	@Override
	public int execute(NIOServer server, SocketChannel channel, Packet packet) {
		// TODO Auto-generated method stub
		int playerId = packet.readInt();
		Iterator<Integer> iters = server.ids.iterator();
		boolean sucessed = false;
		while(iters.hasNext()){
			int id = iters.next();
			if(id == playerId){
				MoreUtils.trace("此用户已登录！");
				sucessed = true;
				break;
			}
		}
		if(!sucessed){
			server.ids.add(playerId);
			MoreUtils.trace("用户请求 " + playerId + " 连接到服务器");
			try {
				channel.register(server.selector,SelectionKey.OP_WRITE);
			} catch (ClosedChannelException e) {
				e.printStackTrace();
			}
		}
		return 2000;
	}

	@Override
	public void write() {
		// TODO Auto-generated method stub

	}

}
