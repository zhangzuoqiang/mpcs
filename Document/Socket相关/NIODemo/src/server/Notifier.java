package server;

import java.util.ArrayList;

import server.event.ServerListener;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-12
 */
public class Notifier {
    private static ArrayList<ServerListener> listeners = null;
    private static Notifier instance = null;

    private Notifier() {
        listeners = new ArrayList<ServerListener>();
    }
    
    public static synchronized Notifier getNotifier() {
        if (instance == null) {
            instance = new Notifier();
            return instance;
        }
        else return instance;
    }
    
    public void addListener(ServerListener l) {
        synchronized (listeners) {
            if (!listeners.contains(l))
                listeners.add(l);
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

    void fireOnRead(Request request) throws Exception {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ( (ServerListener) listeners.get(i)).onRead(request);

    }

    void fireOnWrite(Request request, Response response)  throws Exception  {
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
