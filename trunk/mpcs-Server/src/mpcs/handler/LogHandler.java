package mpcs.handler;

import mpcs.utils.MoreUtils;
import nio.net.Request;
import nio.net.event.EventAdapter;

/**
 * 日志记录 类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public final class LogHandler extends EventAdapter {
	
    public LogHandler() {
    }
    
    public void onClosed(Request request) throws Exception {
        String log = "From " + request.getAddress().toString().substring(1) + " closed.";
        MoreUtils.trace(log);
    }
    
    public void onError(String error) {
    	MoreUtils.trace("Error: " + error);
    }
    
}
