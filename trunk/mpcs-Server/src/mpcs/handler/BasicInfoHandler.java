package mpcs.handler;

import mpcs.cmd.AbstractCmd;
import mpcs.config.GlobalConst;
import mpcs.utils.ParseProtocol;
import mpcs.utils.MoreUtils;
import nio.net.Request;
import nio.net.Response;
import nio.net.event.EventAdapter;

public class BasicInfoHandler extends EventAdapter {
	
	private AbstractCmd abc;
	public void onWrite(Request request, Response response) throws Exception {
		String command = new String(request.getDataInput());
		if (command.equals("")) {
			MoreUtils.doEmpty();
			return;
		}
		
		abc = ParseProtocol.parseAbstractCmd(command);
		
		// 请求用户基本信息
		if (abc.getHead1() == GlobalConst.C_USER_BASIC_INFO) {
			
		}
	}
}
