package mpcs.handler;

import mpcs.cmd.UserRegisterCmd;
import mpcs.config.GlobalConst;
import mpcs.utils.ProtocolDecoder;
import nio.net.Request;
import nio.net.Response;
import nio.net.event.EventAdapter;

/**
 * 用户注册服务的事件处理器类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class UserRegisterHandler extends EventAdapter {
	
    public UserRegisterHandler() {
    }
    
    public void onWrite(Request request, Response response) throws Exception {
        String command = new String(request.getDataInput());
        
        UserRegisterCmd regCmd = ProtocolDecoder.RegisterCmdDecoder(command);
        
        // 判断查询命令
        if (regCmd.getHead1() == GlobalConst.C_USER_REGISTER) {
        	response.send((GlobalConst.S_USER_REGISTER + "").getBytes());
        	response.send("0".getBytes());
        	response.send("0".getBytes());
		}
        else {
        	response.send("-1".getBytes());
		}
    }
}