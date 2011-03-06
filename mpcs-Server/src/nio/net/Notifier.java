package nio.net;

import java.util.ArrayList;

import nio.net.event.ServerListener;

/**
 * <p>Title: 事件触发器</p>
 * <p>Description: 用于在适当的时候通过触发服务器事件，通知注册的事件处理器对事件做出响应。
 * 触发器以 Singleton 模式实现，统一控制整个服务器端的事件，避免造成混乱。</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class Notifier {
	
    private static ArrayList<ServerListener> listeners = null;
    private static Notifier instance = null;
    
    private Notifier() {
        listeners = new ArrayList<ServerListener>();
    }
    
    /**
     * 获取事件触发器
     * @return 返回事件触发器
     */
    public static synchronized Notifier getNotifier() {
        if (instance == null) {
            instance = new Notifier();
            return instance;
        }
        else 
        	return instance;
    }
    
    /**
     * 添加事件监听器
     * @param listener 监听器
     */
    public void addListener(ServerListener listener) {
        synchronized (listeners) {
            if (!listeners.contains(listener))
                listeners.add(listener);
        }
    }
    
    public void fireOnAccept() throws Exception {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ( (ServerListener) listeners.get(i)).onAccept();
    }
    
    public void fireOnAccepted(Request request) throws Exception {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ( (ServerListener) listeners.get(i)).onAccepted(request);
    }
    
    public void fireOnRead(Request request) throws Exception {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ( (ServerListener) listeners.get(i)).onRead(request);
    }
    
    public void fireOnWrite(Request request, Response response)  throws Exception  {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ( (ServerListener) listeners.get(i)).onWrite(request, response);
    }
    
    public void fireOnClosed(Request request) throws Exception {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ( (ServerListener) listeners.get(i)).onClosed(request);
    }
    
    public void fireOnError(String error) {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ( (ServerListener) listeners.get(i)).onError(error);
    }
}
