package mpcs.handler;

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
        System.out.println(log);
    }
    
    public void onError(String error) {
        System.out.println("Error: " + error);
    }
    
}
