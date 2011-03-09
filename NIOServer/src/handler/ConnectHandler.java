package handler;

import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import nio.configs.GlobalConst;
import nio.core.NIOServer;
import nio.data.Packet;
import nio.interfaces.ICmd;
import nio.utils.MoreUtils;

/**
 * 用户连接服务器请求
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-9
 */
public class ConnectHandler implements ICmd {
	
	public int execute(NIOServer server, SocketChannel channel, Packet packet) {
		int userID = packet.readInt();
		Iterator<Integer> iters = server.ids.iterator();
		boolean sucessed = false;
		while(iters.hasNext()){
			int id = iters.next();
			if(id == userID){
				MoreUtils.trace("此用户已连接！");
				sucessed = true;
				break;
			}
		}
		if(!sucessed){
			server.ids.add(userID);
			MoreUtils.trace("命令 " + userID + " 请求服务器服务");
			try {
				channel.register(server.selector,SelectionKey.OP_WRITE);
			} catch (ClosedChannelException e) {
				e.printStackTrace();
			}
		}
		return GlobalConst.S_REQUEST_CONNECTION;
	}
	
	public void write() {
		
	}

}
