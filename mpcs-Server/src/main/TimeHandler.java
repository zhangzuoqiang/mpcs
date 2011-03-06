package main;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import nio.net.Request;
import nio.net.Response;
import nio.net.event.EventAdapter;

/**
 * 时间查询服务的事件处理器类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class TimeHandler extends EventAdapter {
	
    public TimeHandler() {
    }
    
    public void onWrite(Request request, Response response) throws Exception {
        String command = new String(request.getDataInput());
        String time = null;
        Date date = new Date();
        
        // 判断查询命令
        if (command.equals("GB")) {
            // 中文格式
            DateFormat cnDate = DateFormat.getDateTimeInstance(DateFormat.FULL,
                DateFormat.FULL, Locale.CHINA);
            time = cnDate.format(date);
        }
        else {
            // 英文格式
            DateFormat enDate = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL, Locale.US);
            time = enDate.format(date);
        }
        
        response.send(time.getBytes());
    }
}