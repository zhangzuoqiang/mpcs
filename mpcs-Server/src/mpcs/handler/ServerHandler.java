package mpcs.handler;

import mpcs.utils.MoreUtils;
import nio.net.Request;
import nio.net.Response;
import nio.net.event.EventAdapter;

/**
 * <p>Title: 服务端事件处理器</p>
 * <p>Description: 继承事件适配器，对感兴趣的事件进行响应处理，实现业务处理</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class ServerHandler extends EventAdapter {
	
    public ServerHandler() {
    }
    
    public void onAccept() throws Exception {
        MoreUtils.trace("#onAccept()");
    }
    
    public void onAccepted(Request request) throws Exception {
    	MoreUtils.trace("#onAccepted()");
    }
    
    public void onRead(Request request) throws Exception {
        //byte[] rspData = data;
        //if (new String (data).equalsIgnoreCase("query")) {
        //    rspData = new java.util.Date().toString().getBytes();
        //}
        //request.attach(rspData);
    	MoreUtils.trace("#onRead()");
    }
    
    public void onWrite(Request request, Response response) throws Exception {
    	MoreUtils.trace("#onWrite()");
        //response.send((byte[])request.attachment());
        //response.send("OK".getBytes());
    }
    
    public void onClosed(Request request) throws Exception {
    	MoreUtils.trace("#onClosed()");
    }
    
    public void onError(String error) {
    	MoreUtils.trace("#onAError(): " + error);
    }
}
