package nio.handler;

import java.util.Date;

import mpcs.utils.MoreUtils;
import nio.core.Request;
import nio.manager.ListenAdapter;


/**
 * <p>Title: 日志记录 类</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class LogHandler extends ListenAdapter {
	
    public LogHandler() {
    }

    public void doClosed(Request request) throws Exception {
        String log = new Date().toString() + " from " + request.getAddress().toString();
        MoreUtils.trace("onClosed: " + log);
    }

    public void doError(String error) {
    	MoreUtils.trace("Error: " + error);
    }
}
