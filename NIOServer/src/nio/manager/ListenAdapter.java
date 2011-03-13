package nio.manager;

import nio.core.Request;
import nio.core.Response;
import nio.interfaces.IListener;

/**
 * <p>Title: 事件适配器</p>
 * <p>Description: 对 Ilistener 接口实现一个适配器 (ListenAdapter)，
 * 这样设计可以使最终的事件处理器只处理所关心的事件</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public abstract class ListenAdapter implements IListener {
	
    public ListenAdapter() {
    }
    
    public void doError(String error) {
    }
    
    public void doAccept() throws Exception {
    }
    
    public void doAccepted(Request request)  throws Exception {
    }
    
    public void doRead(Request request)  throws Exception {
    }
    
    public void doWrite(Request request, Response response)  throws Exception {
    }
    
    public void doClosed(Request request)  throws Exception{
    }
}
