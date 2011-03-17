package mpcs.handler;

import mpcs.config.GlobalConst;
import mpcs.config.GlobalErrorConst;
import mpcs.db.ExeSQL;
import mpcs.model.BaseCmd;
import nio.core.Request;
import nio.core.Response;
import nio.manager.ListenAdapter;
import nio.util.LangUtil;

/**
 * <p>Title: 设定手机提醒服务 事件处理</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-13
 */
public class MobileTipsHandler extends ListenAdapter {
	
	public void doWrite(Request request, Response response) throws Exception {
		int command = request.getCommand();
		if (command == GlobalConst.C_OPEN_MOBILE_TIP) {
			String email = request.getPacket().readString();
			String com = request.getPacket().readString();
			if (updateTip(email, com) == 1) {
				// 修改手机提示成功
				BaseCmd cmd = new BaseCmd(GlobalConst.S_OPEN_MOBILE_TIP);
				cmd.writeString(LangUtil.get("20019"));
				response.send(cmd);
				return;
			}else if(updateTip(email, com) == 0){
				BaseCmd cmd = new BaseCmd(GlobalConst.S_OPEN_MOBILE_TIP, GlobalErrorConst.E_OPEN_MOBILE_TIP);
				cmd.writeString(LangUtil.get("20020"));
				response.send(cmd);
				return;
			}else if(updateTip(email, com) == 2){
				BaseCmd cmd = new BaseCmd(GlobalConst.S_OPEN_MOBILE_TIP, GlobalErrorConst.E_INVALIDE_MOBILE_TIP);
				cmd.writeString(LangUtil.get("20021"));
				response.send(cmd);
			}
		}
	}
	
	private int updateTip(String email, String command){
		if (!(command.equals("0") || command.equals("1"))) {
			return 2;
		} else if (!ExeSQL.updateTipByEmail(email, command)) {
			return 1;
		}else {
			return 0;
		}
	}
}
