package mpcs.handler;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import mpcs.config.GlobalConst;
import mpcs.config.GlobalErrorConst;
import mpcs.db2.ExeSQL;
import mpcs.model.BaseCmd;
import mpcs.utils.MoreUtils;
import mpcs.vo.UserVO;
import nio.config.Debug;
import nio.core.Request;
import nio.core.Response;
import nio.manager.ListenAdapter;
import nio.manager.SessionManager;
import nio.util.LangUtil;

/**
 * <p>Title: 登录事件处理</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-13
 */
public class LoginHandler extends ListenAdapter {
	
	private UserVO vo = null;
	
	public LoginHandler(){
	}
	
	public void doWrite(Request request, Response response) throws Exception {
		int command = request.getCommand();
        if (command == GlobalConst.C_USER_LOGIN) {
        	vo = new UserVO();
        	vo.setEmail(request.getPacket().readString());
        	vo.setPassword(request.getPacket().readString());
            
        	if (!isUserExist(vo.getEmail())) {
				// 不存在此用户
        		BaseCmd cmd = new BaseCmd(GlobalConst.S_USER_LOGIN, GlobalErrorConst.E_NO_THIS_USER);
        		cmd.writeString(LangUtil.get("20006"));
        		response.send(cmd);
        		return;
			}else if (isPwdWrong(vo.getPassword())) {
				// 用户密码错误
				BaseCmd cmd = new BaseCmd(GlobalConst.S_USER_LOGIN, GlobalErrorConst.E_PASSWORD_WRONG);
				cmd.writeString(LangUtil.get("20005"));
				response.send(cmd);
				return;
			}else {
				// 用户登录验证成功
				SelectionKey key = response.getKey();
				SocketChannel sc = (SocketChannel) key.channel();
				boolean online = SessionManager.getInstance().isOnLine(vo.getEmail(), sc);
				if (online) {
					MoreUtils.trace(LangUtil.get("20007"), Debug.printSystem);
				}
				BaseCmd cmd = new BaseCmd(GlobalConst.S_USER_LOGIN);
				cmd.writeString(LangUtil.get("20004") + vo.getEmail());
				response.send(cmd);
			}
        }
	}
	
	/**
	 * 验证用户密码是否正确
	 * @param email
	 * @return
	 */
	private boolean isPwdWrong(String pwd){
		if (password.equals(pwd) && pwd !="") {
			return false;
		}
		return true;
	}
	
	// 缓存密码
	private String password;
	/**
     * 检查是否存在此用户
     * @param email
     * @return
     */
    private boolean isUserExist(String email){
    	password = ExeSQL.selectByEmail(email);
    	if (!password.equals("")) {
    		return true;
		}
    	return false;
    }
}
