package mpcs.handler;

import mpcs.config.GlobalConst;
import nio.core.Request;
import nio.core.Response;
import nio.manager.ListenAdapter;

/**
 * <p>Title: 请求用户基本信息，保存用户基本信息事件处理</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-13
 */
public class BaseInfoHandler extends ListenAdapter {
	
//	private UserVO vo = null;
	
	public BaseInfoHandler(){
	}
	
	public void doWrite(Request request, Response response) throws Exception {
		int command = request.getCommand();
        if (command == GlobalConst.C_USER_BASIC_INFO) {
        	
        }
	}
	
}
