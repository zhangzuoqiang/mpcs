package nio.manager;

import nio.core.Request;
import nio.core.Response;
import nio.interfaces.IServerListener;

/**
 * <p>Title: 事件适配器</p>
 * <p>Description: 对 Serverlistener 接口实现一个适配器 (EventAdapter)，
 * 这样设计可以使最终的事件处理器只处理所关心的事件</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public abstract class EventAdapter implements IServerListener {
	
    public EventAdapter() {
    }
    
    public void onError(String error) {
    }
    
    public void onAccept() throws Exception {
    }
    
    public void onAccepted(Request request)  throws Exception {
    }
    
    public void onRead(Request request)  throws Exception {
    }
    
    public void onWrite(Request request, Response response)  throws Exception {
    }
    
    public void onClosed(Request request)  throws Exception{
    }
}
