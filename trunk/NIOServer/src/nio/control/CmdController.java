package nio.control;

import java.util.HashMap;

import nio.interfaces.ICmd;
import nio.utils.MoreUtils;

/**
 * <p>Title: 负责注册系统所有消息号</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-9
 */
public class CmdController {
	
	/**
	 * 哈希表结构：消息号-处理类
	 */
	private static HashMap<Integer,ICmd> commands = new HashMap<Integer,ICmd>();
	
	/**
	 * 注册消息号
	 * @param cmdID
	 * @param cmd
	 */
	public static void registerCmd(int cmdID,ICmd cmd){
		if(!commands.containsKey(cmdID)){
			MoreUtils.trace("registeCommand  " + cmdID + "  ,  " + cmd);
			commands.put(cmdID, cmd);
		}
	}
	
	/**
	 * 获得系统 消息号哈希表
	 * @return
	 */
	public static HashMap<Integer, ICmd> getCommands(){
		return commands;
	}
}
