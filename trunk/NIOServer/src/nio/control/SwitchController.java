package nio.control;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import nio.configs.GlobalConst;
import nio.core.NIOServer;
import nio.data.Packet;
import nio.interfaces.ICmd;

/**
 * <p>Title: 消息号分发控制类</p>
 * <p>Description: 根据客户端发送的请求消息号执行对应的业务</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class SwitchController {
	
	/**
	 * 根据消息号分发业务逻辑
	 * @param server
	 * @param channel
	 * @param packet
	 * @param command
	 * @param key
	 */
	public static void switchCmd(int command, NIOServer server, SocketChannel channel, Packet packet, SelectionKey key){
		if(CmdController.getCommands().containsKey(command)){
			ICmd comm = CmdController.getCommands().get(command);
			switch (command) {
				case GlobalConst.C_REQUEST_CONNECTION:
					doReqConnect(comm, server, channel, packet, key);
					break;
				default:
					break;
			}
			
			
		}
	}
	
	/**
	 * 处理客户端请求连接服务器
	 * @param cmd
	 * @param server
	 * @param channel
	 * @param packet
	 * @param key
	 */
	private static void doReqConnect(ICmd cmd, NIOServer server, SocketChannel channel, Packet packet, SelectionKey key){
		int result = cmd.execute(server, channel, packet);
		key.attach(result);
	}
}
