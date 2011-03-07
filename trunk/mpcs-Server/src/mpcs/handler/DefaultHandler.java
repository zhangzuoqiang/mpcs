package mpcs.handler;

import mpcs.cmd.AbstractCmd;
import mpcs.config.GlobalConst;
import mpcs.utils.ByteUtil;
import mpcs.utils.ProtocolDecoder;
import nio.net.Request;
import nio.net.Response;
import nio.net.event.EventAdapter;

/**
 * <p>Title: 回应客户端的默认行为</p>
 * <p>Description: 如果当前客户端与服务端无主动交互，客户端不断发默认消息给服务端
 * 服务端执行此行为</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public class DefaultHandler extends EventAdapter {
	
	private AbstractCmd defaultCmd;
	
	public void onWrite(Request request, Response response) throws Exception {
		String command = new String(request.getDataInput());
		defaultCmd = ProtocolDecoder.AbstractCmdDecoder(command);
		
		// 判断查询命令为 空闲连接
        if (defaultCmd.getHead1() == GlobalConst.C_IDLE_CONNECTION) {
        	response.send(ByteUtil.getByteByConst(0, GlobalConst.S_IDLE_CONNECTION, 0));
        }
	}
}
