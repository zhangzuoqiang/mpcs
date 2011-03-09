package mpcs.libs.handler;

import java.nio.channels.SelectionKey;

import mpcs.libs.cmds.UserCmd;
import mpcs.libs.configs.GlobalConst;
import mpcs.libs.core.Notifier;
import mpcs.libs.core.Response;
import mpcs.libs.data.ByteArrayPacket;
import mpcs.libs.data.ParseProtocol;
import mpcs.libs.events.EventAdapter;
import mpcs.libs.utils.DBUtil;
import mpcs.libs.utils.MoreUtil;
import mpcs.libs.utils.SendUtil;

public class RegisterHandler extends EventAdapter {
	
	private UserCmd regCmd;
	private int cmd;
	private static Notifier notifier = Notifier.getNotifier();
	
	@Override
	public void onAccepted(ByteArrayPacket request) throws Exception {
	}

	@Override
	public void onRead(ByteArrayPacket request) throws Exception {
	}

	@Override
	public void onWrite(ByteArrayPacket request, Response response) throws Exception {
		SelectionKey key = response.getKey();
		cmd = ParseProtocol.parseSwitchID(request);
		if (cmd == GlobalConst.C_USER_REGISTER) {
			regCmd = ParseProtocol.parseUserCmd(request);
			// 执行数据库操作 查询用户
        	if (isExist(regCmd.getEmail())) {
        		//该邮箱已被注册
        		response.send(key, SendUtil.emailIsUsed());
        		MoreUtil.trace("该邮箱已被注册");
        		return;
			}else {
				// 添加用户
				if (addUser(regCmd.getEmail(), regCmd.getPassword()) == 1) {
					// 注册成功 返回
					response.send(key, SendUtil.regSuccess());
					MoreUtil.trace("注册成功 返回");
					return;
				}else {
					// 执行数据库操作，添加用户时失败
	        		response.send(key, SendUtil.regFailed());
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

	@Override
	public void onClosed(ByteArrayPacket request) throws Exception {
	}

}
