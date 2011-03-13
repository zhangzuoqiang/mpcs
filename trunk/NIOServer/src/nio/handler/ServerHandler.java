package nio.handler;

import mpcs.utils.MoreUtils;
import nio.core.Request;
import nio.core.Response;
import nio.manager.EventAdapter;
import nio.util.LangUtil;

/**
 * <p>Title: 处理客户端的连接事件</p>
 * <p>Description: 服务器可设定同时只为一定数量客户端提供服务，当同时请求数超出数量时，
 * 可在响应该事件时直接抛出异常，以拒绝新的连接。</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class ServerHandler extends EventAdapter {
	
    public ServerHandler() {
    }

    public void onAccept() throws Exception {
//    	MoreUtils.trace("#onAccept()");
    }

    public void onAccepted(Request request) throws Exception {
//    	MoreUtils.trace("#onAccepted()");
    }

    public void onRead(Request request) throws Exception {
//    	MoreUtils.trace("#onRead()");
    }

    public void onWrite(Request request, Response response) throws Exception {
    	int command = request.getCommand();
    	if (!MoreUtils.isCommand(command)) {
    		MoreUtils.trace(LangUtil.get("10003") + command + LangUtil.get("10004"));
		}
//    	MoreUtils.trace("#onWrite()");
    }

    public void onClosed(Request request) throws Exception {
//    	MoreUtils.trace("#onClosed()");
    }

    public void onError(String error) {
//    	MoreUtils.trace("#onAError(): " + error);
    }
}
