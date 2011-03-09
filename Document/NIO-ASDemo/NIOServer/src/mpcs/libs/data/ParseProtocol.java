package mpcs.libs.data;

import mpcs.libs.cmds.AbstractCmd;
import mpcs.libs.cmds.UserCmd;
import mpcs.libs.configs.ServerConfig;

/**
 * 服务端 协议解析类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public final class ParseProtocol {
	
	/**
	 * 获取消息号
	 * @param packet
	 * @return
	 */
	public static int parseSwitchID(ByteArrayPacket packet){
		int command = packet.readInt();
		return command;
	}
	/**
	 * 解析用户登录/注册cmd
	 * @param cmd
	 * @return
	 */
	public static UserCmd parseUserCmd(ByteArrayPacket request){
		UserCmd rCmd = new UserCmd();
		rCmd.setHead1(request.readInt());
		rCmd.setHead2(request.readInt());
		rCmd.setHead3(request.readInt());
		rCmd.setEmail(request.readString(ServerConfig.LOCAL_CHARSET));
		rCmd.setPassword(request.readString(ServerConfig.LOCAL_CHARSET));
		return rCmd;
	}
	
	/**
	 * 解析不含消息体的cmd
	 * @param cmd
	 * @return
	 */
	public static AbstractCmd parseAbstractCmd(ByteArrayPacket request){
		AbstractCmd abc = new AbstractCmd();
		abc.setHead1(request.readInt());
		abc.setHead2(request.readInt());
		abc.setHead3(request.readInt());
		return abc;
	}
}
