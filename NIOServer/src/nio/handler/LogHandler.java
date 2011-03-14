package nio.handler;

import java.util.Date;

import mpcs.utils.MoreUtils;
import nio.config.Debug;
import nio.core.Request;
import nio.manager.ListenAdapter;
import nio.util.LangUtil;


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
        MoreUtils.trace(LangUtil.get("10019") + log, Debug.printSystem);
    }

    public void doError(String error) {
    	MoreUtils.trace(LangUtil.get("10020") + error, Debug.printSystem);
    }
}
