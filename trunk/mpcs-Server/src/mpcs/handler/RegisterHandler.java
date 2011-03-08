package mpcs.handler;

import mpcs.cmd.UserCmd;
import mpcs.config.GlobalConst;
import mpcs.config.GlobalErrorConst;
import mpcs.utils.ByteUtil;
import mpcs.utils.DBUtil;
import mpcs.utils.ParseProtocol;
import mpcs.utils.TraceUtil;
import nio.net.Notifier;
import nio.net.Request;
import nio.net.Response;
import nio.net.event.EventAdapter;

/**
 * 用户注册服务的事件处理器类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public final class RegisterHandler extends EventAdapter {
	
	private UserCmd regCmd;
	private static Notifier notifier = Notifier.getNotifier();
	
    public RegisterHandler() {
    }
    
    public void onWrite(Request request, Response response) throws Exception {
    	
        String command = new String(request.getDataInput());
        regCmd = ParseProtocol.parseUserCmd(command);
        
        // 判断查询命令为用户注册
        if (regCmd.getHead1() == GlobalConst.C_USER_REGISTER) {
        	// 执行数据库操作 查询用户
        	if (isExist(regCmd.getEmail())) {
        		//该邮箱已被注册
        		response.send(ByteUtil.getByteByConst(9, GlobalErrorConst.E_USER_REGISTER, 0));
        		TraceUtil.trace("该邮箱已被注册");
        		return;
			}else {
				// 添加用户
				if (addUser(regCmd.getEmail(), regCmd.getPassword()) == 1) {
					// 注册成功 返回
					response.send(ByteUtil.getByteByConst(0, GlobalConst.S_USER_REGISTER, 0));
					TraceUtil.trace("注册成功 返回");
					return;
				}else {
					// 执行数据库操作，添加用户时失败
	        		response.send(ByteUtil.getByteByConst(9, GlobalErrorConst.E_ADD_USER_FAILED, 0));
	        		notifier.fireOnError("Error occured in exe SQL to add User: 执行数据库操作，添加用户" + regCmd.getEmail() + "时失败");
				}
			}
		}
    }
    
    /**
     * 添加用户
     * @param email
     * @param pwd
     * @return 返回1则成功添加，0则添加失败
     */
    private int addUser(String email, String pwd){
    	return DBUtil.addUser(email, pwd);
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