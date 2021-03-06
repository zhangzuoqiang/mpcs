package nio.handler;

import mpcs.utils.MoreUtils;
import nio.config.Debug;
import nio.core.Request;
import nio.core.Response;
import nio.manager.ListenAdapter;
import nio.util.LangUtil;

/**
 * <p>Title: 处理客户端的连接事件</p>
 * <p>Description: 服务器可设定同时只为一定数量客户端提供服务，当同时请求数超出数量时，
 * 可在响应该事件时直接抛出异常，以拒绝新的连接。</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class ServerHandler extends ListenAdapter {
	
    public ServerHandler() {
    }

    public void doAccept() throws Exception {
//    	MoreUtils.trace("#onAccept()");
    }

    public void doAccepted(Request request) throws Exception {
//    	MoreUtils.trace("#onAccepted()");
    }

    public void doRead(Request request) throws Exception {
//    	MoreUtils.trace("#onRead()");
    }

    public void doWrite(Request request, Response response) throws Exception {
    	int command = request.getCommand();
    	// 无效的请求，屏蔽恶意连接
    	if (!MoreUtils.isCommand(command)) {
    		MoreUtils.trace(LangUtil.get("10003") + command + LangUtil.get("10004"), Debug.printSystem);
		}
//    	MoreUtils.trace("#onWrite()");
    }

    public void doClosed(Request request) throws Exception {
//    	MoreUtils.trace("#onClosed()");
    }

    public void doError(String error) {
//    	MoreUtils.trace("#onAError(): " + error);
    }
}
