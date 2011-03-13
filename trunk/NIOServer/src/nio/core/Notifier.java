package nio.core;

import java.util.ArrayList;

import nio.interfaces.IServerListener;


/**
 * <p>Title: 事件触发器</p>
 * <p>Description: 用于在适当的时候通过触发服务器事件，通知注册的事件处理器对事件做出响应。
 * 触发器以 Singleton 模式实现，统一控制整个服务器端的事件，避免造成混乱。</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class Notifier {
	
    private static ArrayList<IServerListener> listeners = null;
    private static Notifier instance = null;

    private Notifier() {
        listeners = new ArrayList<IServerListener>();
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
        else return instance;
    }
    
    /**
     * 添加事件监听器
     * @param listener 监听器
     */
    public void addListener(IServerListener l) {
        synchronized (listeners) {
            if (!listeners.contains(l))
                listeners.add(l);
        }
    }

    /**
     * 触发Accept事件
     * @throws Exception
     */
    public void fireOnAccept() throws Exception {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ( (IServerListener) listeners.get(i)).onAccept();
    }

    /**
     * 触发Accepted事件
     * @param request
     * @throws Exception
     */
    public void fireOnAccepted(Request request) throws Exception {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ( (IServerListener) listeners.get(i)).onAccepted(request);
    }

    /**
     * 触发Read事件
     * @param request
     * @throws Exception
     */
    void fireOnRead(Request request) throws Exception {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ( (IServerListener) listeners.get(i)).onRead(request);
    }
    
    /**
     * 触发Write事件
     * @param request
     * @param response
     * @throws Exception
     */
    void fireOnWrite(Request request, Response response)  throws Exception  {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ( (IServerListener) listeners.get(i)).onWrite(request, response);
    }

    /**
     * 触发Closed事件
     * @param request
     * @throws Exception
     */
    public void fireOnClosed(Request request) throws Exception {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ( (IServerListener) listeners.get(i)).onClosed(request);
    }

    /**
     * 触发Error事件
     * @param error
     */
    public void fireOnError(String error) {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ( (IServerListener) listeners.get(i)).onError(error);
    }
}
