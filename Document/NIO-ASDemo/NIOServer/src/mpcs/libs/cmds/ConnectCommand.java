package mpcs.libs.cmds;

import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import mpcs.libs.core.NIOServer;
import mpcs.libs.data.ByteArrayPacket;
import mpcs.libs.interfaces.ICommand;

/**
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-8
 */
public class ConnectCommand implements ICommand {
	
	public int execute(NIOServer server,SocketChannel channel,ByteArrayPacket packet){
		int playerId=packet.readInt();
		Iterator<Integer> iters=server.ids.iterator();
		boolean sucessed=false;
		while(iters.hasNext()){
			int id=iters.next();
			if(id==playerId){
				System.out.println("此用户已连接！！！");
				sucessed=true;
				break;
			}
		}
		if(!sucessed){
			server.ids.add(playerId);
			System.out.println("来自 "+playerId+" 连接");
			try {
				channel.register(server.selector,SelectionKey.OP_WRITE);
			} catch (ClosedChannelException e) {
				e.printStackTrace();
			}
		}
		return 2000;
	}
	
	public void write(){
		
	}
}