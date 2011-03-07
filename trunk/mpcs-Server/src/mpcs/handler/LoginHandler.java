package mpcs.handler;

import mpcs.cmd.UserCmd;
import mpcs.cmd.UserList;
import mpcs.config.GlobalConst;
import mpcs.config.GlobalErrorConst;
import mpcs.utils.ByteUtil;
import mpcs.utils.DBUtil;
import mpcs.utils.ProtocolDecoder;
import nio.net.Request;
import nio.net.Response;
import nio.net.event.EventAdapter;

/**
 * 用户登录服务的事件处理器类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public class LoginHandler extends EventAdapter {

	private UserCmd loginCmd;
	
	public LoginHandler(){
	}
	
	public void onWrite(Request request, Response response) throws Exception {
		String command = new String(request.getDataInput());
		loginCmd = ProtocolDecoder.UserCmdDecoder(command);
		
		// 判断查询命令为用户登录
        if (loginCmd.getHead1() == GlobalConst.C_USER_LOGIN) {
        	
        	if (!isUserExist(loginCmd.getEmail())) {
				// 不存在此用户
        		response.send(ByteUtil.getByteByConst(GlobalErrorConst.E_NO_THIS_USER, 9, 0));
        		System.out.println("不存在此用户");
        		return;
			}else if (isPwdWrong(loginCmd.getEmail())) {
				// 用户密码错误
				response.send(ByteUtil.getByteByConst(GlobalErrorConst.E_PASSWORD_WRONG, 9, 0));
				System.out.println("用户密码错误");
				return;
			}else {
				// 用户登录验证成功
				UserList.getUserList().addUser(request.getAddress().toString(), loginCmd);
				response.send(ByteUtil.getByteByConst(GlobalConst.S_USER_LOGIN, 0, 0));
				System.out.println("用户登录验证成功");
			}
		}
	}
	
	/**
	 * 验证用户密码是否正确
	 * @param email
	 * @return
	 */
	private boolean isPwdWrong(String email){
		String pwd = DBUtil.selectPwdByEmail(email);
		if (loginCmd.getPassword().equals(pwd) && pwd !="") {
			return false;
		}
		return true;
	}
	/**
     * 检查是否存在此用户
     * @param email
     * @return
     */
    private boolean isUserExist(String email){
    	if (DBUtil.selectByEmail(email)) {
    		return true;
		}
    	return false;
    }
}
