package nio.handler;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import nio.configs.GlobalConst;
import nio.core.NIOServer;
import nio.core.Notifier;
import nio.data.Packet;
import nio.data.Response;
import nio.interfaces.ICmd;
import nio.utils.MoreUtils;

/**
 * <p>Title: 用户连接服务器请求</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-10
 */
public class ConnectHandler implements ICmd {
	private static Notifier notifier = Notifier.getNotifier();
	
	public int execute(SocketChannel channel, Packet packet) {
		int userID = packet.readInt();
		Iterator<Integer> iters = NIOServer.getInstance().ids.iterator();
		boolean sucessed = false;
		while(iters.hasNext()){
			int id = iters.next();
			if(id == userID){
				MoreUtils.trace("连接已存在！");
				sucessed = true;
				break;
			}
		}
		if(!sucessed){
			NIOServer.getInstance().ids.add(userID);
			MoreUtils.trace("命令 " + userID + " 请求服务器服务");
			try {
				channel.register(NIOServer.getInstance().selector,SelectionKey.OP_WRITE);
			} catch (ClosedChannelException e) {
				notifier.fireOnError("Error occured in ConnectHandler execute: " + e.getMessage());
			}
		}
		return GlobalConst.S_REQUEST_CONNECTION;
	}

	@Override
	public void write(Response response) {
		Packet p = new Packet(100);
		p.writeInt(GlobalConst.S_REQUEST_CONNECTION);
		try {
			response.send(p, response.getKey());
		} catch (IOException e) {
			notifier.fireOnError("Error occured in ConnectHandler write: " + e.getMessage());
		}
	}

}
