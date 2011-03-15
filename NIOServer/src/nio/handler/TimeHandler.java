package nio.handler;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import mpcs.config.GlobalConst;
import mpcs.model.BaseCmd;
import nio.core.Request;
import nio.core.Response;
import nio.manager.ListenAdapter;

/**
 * <p>Title: 客户端请求服务端时间 事件处理</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public class TimeHandler extends ListenAdapter {
	
    public TimeHandler() {
    }

    public void doWrite(Request request, Response response) throws Exception {
        int command = request.getCommand();
        String time = null;
        Date date = new Date();
        
        if (command == GlobalConst.C_TEST) {
        	DateFormat enDate = DateFormat.getDateTimeInstance(DateFormat.FULL,
                    DateFormat.FULL, Locale.US);
                time = enDate.format(date);
                BaseCmd cmd = new BaseCmd(GlobalConst.S_TEST);
                cmd.writeString(time);
                response.send(cmd);
        }
    }
}
