package mpcs.libs.cmds;

import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import mpcs.libs.core.NIOServer;
import mpcs.libs.core.Notifier;
import mpcs.libs.data.ByteArrayPacket;
import mpcs.libs.interfaces.ICommand;
import mpcs.libs.utils.MoreUtil;

/**
 * 用户连接服务器 管理类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-8
 */
public class ConnectCmd implements ICommand {
	
	private Notifier notifier = Notifier.getNotifier();
	
	public int execute(NIOServer server, SocketChannel channel, ByteArrayPacket packet){
		int userID = packet.readInt();
		Iterator<Integer> iters = server.ids.iterator();
		boolean sucessed = false;
		while(iters.hasNext()){
			int id = iters.next();
			if(id == userID){
				MoreUtil.trace("此用户已连接！！！");
				sucessed = true;
				break;
			}
		}
		if(!sucessed){
			server.ids.add(userID);
			MoreUtil.trace("接收命令： " + userID);
			try {
				channel.register(server.getSelector(),SelectionKey.OP_WRITE);
			} catch (ClosedChannelException e) {
				e.printStackTrace();
				notifier.fireOnError("Error occured in ConnectCmd by ClosedChannel: " + e.getMessage());
			}
		}
		return 2000;
	}
	
	public void write(){
		
	}
}