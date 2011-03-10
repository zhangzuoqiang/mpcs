package nio.core;

import nio.configs.GlobalConst;
import nio.data.Response;
import nio.data.TempVO;
import nio.interfaces.ICmd;

/**
 * <p>Title: 消息号分发(写操作)控制类</p>
 * <p>Description: 根据客户端发送的请求消息号执行对应的业务</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class Write {
	
	public static void switchCmd(Response response){
		TempVO temp = (TempVO) response.getKey().attachment();
		// 写回客户端的消息号标志
		int writeCmd = temp.getReturnID();
		ICmd cmd = temp.getICmd();
		
		switch (writeCmd) {
			case GlobalConst.S_REQUEST_CONNECTION:
				cmd.write(response); // 客户端请求连接 返回
				break;
			default:
				break;
		}
	}
}
