package mpcs.libs.handler;

import mpcs.libs.core.Response;
import mpcs.libs.data.ByteArrayPacket;
import mpcs.libs.events.EventAdapter;
import mpcs.libs.utils.MoreUtil;

/**
 * 日志记录 类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public final class LogHandler extends EventAdapter {
	
    public LogHandler() {
    }
    
    public void onClosed(ByteArrayPacket request) throws Exception {
    	
    }
    
    public void onError(String error) {
    	MoreUtil.trace("Error: " + error);
    }
    
	public void onAccepted(ByteArrayPacket request) throws Exception {
		
	}
	
	public void onRead(ByteArrayPacket request) throws Exception {
		
	}
	
	public void onWrite(ByteArrayPacket request, Response response)
			throws Exception {
		
	}
    
}
