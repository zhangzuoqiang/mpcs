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
 * <p>Title: 设定手机位置请求事件处理</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-13
 */
public class SetPositionHandler extends ListenAdapter {

	public void doWrite(Request request, Response response) throws Exception {
		int command = request.getCommand();
		if (command == GlobalConst.C_SET_POSITION) {
			// 读取mobile以及lat,lng,acc
			String mobile = request.getPacket().readString();
			String lat = request.getPacket().readString();
			String lng = request.getPacket().readString();
			String acc = request.getPacket().readString();
			if (updateInitPosition(mobile, lat, lng, acc)) {
				// 修改成功
				BaseCmd cmd = new BaseCmd(GlobalConst.S_SET_POSITION);
				cmd.writeString(LangUtil.get("20022"));
				response.send(cmd);
				return;
			}else {
				// 修改失败
				BaseCmd cmd = new BaseCmd(GlobalConst.S_SET_POSITION, GlobalErrorConst.E_SET_POSITION_WRONG);
				cmd.writeString(LangUtil.get("20023"));
				response.send(cmd);
			}
		}
	}
	
	private boolean updateInitPosition(String mobile, String lat, String lng, String acc){
		if (!ExeSQL.updateInitPosition(mobile, lat, lng, acc)) {
			return true;
		}
		return false;
	}
	
}
