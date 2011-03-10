package nio.control;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import nio.configs.GlobalConst;
import nio.core.NIOServer;
import nio.data.Packet;
import nio.data.Request;
import nio.data.TempVO;
import nio.interfaces.ICmd;

/**
 * <p>Title: 消息号分发(读操作)控制类</p>
 * <p>Description: 根据客户端发送的请求消息号执行对应的业务</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class Read {
	
	/**
	 * 根据消息号分发业务逻辑
	 * @param server
	 * @param channel
	 * @param packet
	 * @param command
	 * @param key
	 */
	public static void switchCmd(int command, NIOServer server, Request request){
		
		SelectionKey key = request.getKey();
		SocketChannel channel = (SocketChannel) request.getKey().channel();
		Packet packet = request.getPacket();
		
		// 检查此消息号是否已经在CmdController中注册
		if(CmdController.getCommands().containsKey(command)){
			ICmd comm = CmdController.getCommands().get(command);
			switch (command) {
				case GlobalConst.C_REQUEST_CONNECTION:
					doReqConnect(comm, server, channel, packet, key);
					break;
				case GlobalConst.C_USER_REGISTER:
					doRegister();
				default:
					break;
			}
			
		}
	}
	
	private static void doRegister(){
		
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
		TempVO temp = new TempVO(result, cmd);
		// 将返回值附加到此键
		key.attach(temp);
	}
}
