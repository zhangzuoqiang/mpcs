package mpcs.handler;

import mpcs.config.GlobalConst;
import mpcs.config.GlobalErrorConst;
import mpcs.db2.ExeSQL;
import mpcs.model.BaseCmd;
import nio.core.Request;
import nio.core.Response;
import nio.manager.ListenAdapter;
import nio.util.LangUtil;

/**
 * <p>Title: 请求修改密码事件处理</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-13
 */
public class ModifyPwdHandler extends ListenAdapter {
	
	public void doWrite(Request request, Response response) throws Exception {
		int command = request.getCommand();
		if (command == GlobalConst.C_USER_MODIFY_PWD) {
			// 读取email以及newpwd
			String email = request.getPacket().readString();
			String newpwd = request.getPacket().readString();
			if (updatePwd(email, newpwd)) {
				// 修改密码成功
				BaseCmd cmd = new BaseCmd(GlobalConst.S_USER_MODIFY_PWD);
				cmd.writeString(LangUtil.get("20008"));
				response.send(cmd);
				return;
			}else {
				// 修改密码失败
				BaseCmd cmd = new BaseCmd(GlobalConst.S_USER_MODIFY_PWD, GlobalErrorConst.E_MODIFY_PASSWORD_WRONG);
				cmd.writeString(LangUtil.get("20009"));
				response.send(cmd);
			}
		}
	}
	
	/**
	 * 修改密码
	 * @param email
	 * @param pwd
	 * @return
	 */
	private boolean updatePwd(String email, String pwd){
		if (!ExeSQL.updatePwd(email, pwd)) {
			return true;
		}
		return false;
	}
}
