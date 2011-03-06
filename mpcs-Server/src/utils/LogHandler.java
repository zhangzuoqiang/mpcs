package utils;

import java.util.Date;

import nio.net.Request;
import nio.net.event.EventAdapter;

/**
 * 日志记录 类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class LogHandler extends EventAdapter {
	
    public LogHandler() {
    }
    
    public void onClosed(Request request) throws Exception {
        String log = new Date().toString() + " from " + request.getAddress().toString();
        System.out.println(log);
    }
    
    public void onError(String error) {
        System.out.println("Error: " + error);
    }
    
}
