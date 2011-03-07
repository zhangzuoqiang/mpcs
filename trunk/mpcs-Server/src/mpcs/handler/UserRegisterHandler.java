package mpcs.handler;

import mpcs.cmd.UserRegisterCmd;
import mpcs.config.GlobalConst;
import mpcs.config.GlobalErrorConst;
import mpcs.utils.ByteUtil;
import mpcs.utils.DBUtil;
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
        
        // 判断查询命令为用户注册
        if (regCmd.getHead1() == GlobalConst.C_USER_REGISTER) {
        	// 执行数据库操作 查询用户
        	if (isExist(regCmd.getEmail())) {
        		//该邮箱已被注册
        		response.send(ByteUtil.getByteByConst(GlobalErrorConst.E_USER_REGISTER, -1, 0));
			}else {
				// 注册成功 返回
				response.send(ByteUtil.getByteByConst(GlobalConst.S_USER_REGISTER, 0, 0));
			}
		}
    }
    
    /**
     * 检查此邮箱是否可用
     * @param email
     * @return
     */
    private boolean isExist(String email){
    	if (DBUtil.selectByEmail(email)) {
    		return true;
		}
    	return false;
    }
}