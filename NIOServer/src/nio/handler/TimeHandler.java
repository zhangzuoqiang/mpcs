package nio.handler;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import mpcs.model.BaseCmd;
import nio.core.Packet;
import nio.core.Request;
import nio.core.Response;
import nio.manager.EventAdapter;

/**
 * <p>Title: 客户端请求服务端时间 事件处理</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class TimeHandler extends EventAdapter {
	
    public TimeHandler() {
    }

    public void onWrite(Request request, Response response) throws Exception {
        Packet packet = request.getPacket();
        int command = packet.readInt();
        String time = null;
        Date date = new Date();
        
        if (command == 100000) {
            DateFormat cnDate = DateFormat.getDateTimeInstance(DateFormat.FULL,
                DateFormat.FULL, Locale.CHINA);
            time = cnDate.format(date);
        }else {
            DateFormat enDate = DateFormat.getDateTimeInstance(DateFormat.FULL,
                DateFormat.FULL, Locale.US);
            time = enDate.format(date);
        }
        
        BaseCmd cmd = new BaseCmd(500000);
        cmd.writeString(time);
        response.send(cmd);
    }
}
