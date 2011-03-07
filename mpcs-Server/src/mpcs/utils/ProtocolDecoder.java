package mpcs.utils;

import mpcs.cmd.UserRegisterCmd;

/**
 * 协议解析类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public class ProtocolDecoder {
	
	/**
	 * 解析用户注册cmd
	 * @param cmd
	 * @return
	 */
	public static UserRegisterCmd RegisterCmdDecoder(String cmd){
		UserRegisterCmd rCmd = new UserRegisterCmd();
		String[] cmdArr = cmd.split("-");
		rCmd.setHead1(Integer.parseInt(cmdArr[0]));
		rCmd.setHead2(Integer.parseInt(cmdArr[1]));
		rCmd.setHead3(Integer.parseInt(cmdArr[2]));
		rCmd.setEmail(cmdArr[3]);
		rCmd.setPassword(cmdArr[4]);
		return rCmd;
	}
}
