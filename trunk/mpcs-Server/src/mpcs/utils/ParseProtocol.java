package mpcs.utils;

import mpcs.cmd.AbstractCmd;
import mpcs.cmd.UserCmd;

/**
 * 服务端 协议解析类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public final class ParseProtocol {
	
	/**
	 * 解析用户注册cmd
	 * @param cmd
	 * @return
	 */
	public static UserCmd parseUserCmd(String cmd){
		UserCmd rCmd = new UserCmd();
		String[] cmdArr = cmd.split("-");
		rCmd.setHead1(Integer.parseInt(cmdArr[0]));
		rCmd.setHead2(Integer.parseInt(cmdArr[1]));
		rCmd.setHead3(Integer.parseInt(cmdArr[2]));
		rCmd.setEmail(cmdArr[3]);
		rCmd.setPassword(cmdArr[4]);
		return rCmd;
	}
	
	/**
	 * 解析不含消息体的cmd
	 * @param cmd
	 * @return
	 */
	public static AbstractCmd parseAbstractCmd(String cmd){
		AbstractCmd abc = new AbstractCmd();
		String[] cmdArr = cmd.split("-");
		abc.setHead1(Integer.parseInt(cmdArr[0]));
		abc.setHead2(Integer.parseInt(cmdArr[1]));
		abc.setHead3(Integer.parseInt(cmdArr[2]));
		return abc;
	}
}
