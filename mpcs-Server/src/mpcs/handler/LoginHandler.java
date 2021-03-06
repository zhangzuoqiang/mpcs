package mpcs.handler;

import mpcs.cmd.UserCmd;
import mpcs.cmd.ClientList;
import mpcs.config.GlobalConst;
import mpcs.config.GlobalErrorConst;
import mpcs.utils.ByteUtil;
import mpcs.utils.DBUtil;
import mpcs.utils.ParseProtocol;
import mpcs.utils.MoreUtils;
import nio.net.Notifier;
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
	private static Notifier notifier = Notifier.getNotifier();
	
	public LoginHandler(){
	}
	
	public void onWrite(Request request, Response response) throws Exception {
		String command = new String(request.getDataInput());
		if (command.equals("")) {
			MoreUtils.doEmpty();
			return;
		}
		loginCmd = ParseProtocol.parseUserCmd(command);
		
		// 判断查询命令为用户登录
        if (loginCmd.getHead1() == GlobalConst.C_USER_LOGIN) {
        	
        	if (!isUserExist(loginCmd.getEmail())) {
				// 不存在此用户
        		response.send(ByteUtil.getByteByConst(9, GlobalErrorConst.E_NO_THIS_USER, 0));
        		MoreUtils.trace("不存在此用户");
        		return;
			}else if (isPwdWrong(loginCmd.getEmail())) {
				// 用户密码错误
				response.send(ByteUtil.getByteByConst(9, GlobalErrorConst.E_PASSWORD_WRONG, 0));
				notifier.fireOnError("Error occured in Login: 用户" + loginCmd.getEmail() + "密码错误");
				return;
			}else {
				// 用户登录验证成功
				ClientList.getClientList().addClient(request.getAddress().toString(), response);
				response.send(ByteUtil.getByteByConst(0, GlobalConst.S_USER_LOGIN, 0));
				MoreUtils.trace("用户登录验证成功");
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
