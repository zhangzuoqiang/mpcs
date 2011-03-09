package nio.control;

import java.io.IOException;
import java.nio.channels.SelectionKey;

import nio.configs.GlobalConst;
import nio.data.Packet;
import nio.utils.MoreUtils;
import nio.utils.WriteUtil;

/**
 * <p>Title: 消息号分发(写操作)控制类</p>
 * <p>Description: 根据客户端发送的请求消息号执行对应的业务</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class SwitchWriteCtrl {

	public static void switchCmd(int writeCmd, SelectionKey key){
		switch (writeCmd) {
			case GlobalConst.S_REQUEST_CONNECTION:
				retReqConnect(key);
				break;
			default:
				break;
		}
	}
	
	/**
	 * 客户端请求连接 返回
	 */
	private static void retReqConnect(SelectionKey key){
		Packet p = new Packet(100);
		p.writeInt(GlobalConst.S_REQUEST_CONNECTION);
		try {
			WriteUtil.send(key, p);
		} catch (IOException e) {
			e.printStackTrace();
			MoreUtils.trace("Error occured in retReqConnect: " + e.getMessage());
		}
	}
}
