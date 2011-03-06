package mpcs.handler;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import mpcs.config.ServerConfig;
import mpcs.utils.ProtocolDecoder;
import nio.net.Request;
import nio.net.Response;
import nio.net.event.EventAdapter;

/**
 * 用户注册服务的事件处理器类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class UserRegisterHandler extends EventAdapter {
	
    public UserRegisterHandler() {
    }
    
    public void onWrite(Request request, Response response) throws Exception {
        String command = new String(request.getDataInput());
        
        ProtocolDecoder.RegisterCmdDecoder(command);
        
        
        
        String time = null;
        Date date = new Date();
        
        // 判断查询命令
        if (command.equals(ServerConfig.POLICY_REQUEST)) {
        	response.send(ServerConfig.POLICY_XML.getBytes());
		}
        else if (command.equals("GB")) {
            // 中文格式
            DateFormat cnDate = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL, Locale.CHINA);
            time = cnDate.format(date);
            response.send(time.getBytes());
        }
        else if(command.equals("EN")) {
            // 英文格式
            DateFormat enDate = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL, Locale.US);
            time = enDate.format(date);
            response.send(time.getBytes());
        }
        else {
        	response.send("0".getBytes());
		}
    }
}