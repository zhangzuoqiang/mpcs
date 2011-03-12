package server;

import java.util.List;
import java.util.LinkedList;
import java.nio.channels.SocketChannel;
import java.nio.channels.SelectionKey;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-12
 */
public final class Writer extends Thread {
    private static List<SelectionKey> pool = new LinkedList<SelectionKey>();
    private static Notifier notifier = Notifier.getNotifier();

    public Writer() {
    }
    
    public void run() {
        while (true) {
            try {
                SelectionKey key;
                synchronized (pool) {
                    while (pool.isEmpty()) {
                        pool.wait();
                    }
                    key = (SelectionKey) pool.remove(0);
                }
                
                write(key);
            }
            catch (Exception e) {
                continue;
            }
        }
    }

    /**
     * @param key
     */
    public void write(SelectionKey key) {
        try {
            SocketChannel sc = (SocketChannel) key.channel();
            Response response = new Response(sc);
            
            notifier.fireOnWrite((Request)key.attachment(), response);
            
//            sc.finishConnect();
//            sc.socket().close();
//            sc.close();
//            
//            notifier.fireOnClosed((Request)key.attachment());
//            System.out.println(key.attachment());
        }
        catch (Exception e) {
            notifier.fireOnError("Error occured in Writer: " + e.getMessage());
        }
    }
    
    public static void processRequest(SelectionKey key) {
        synchronized (pool) {
            pool.add(pool.size(), key);
            pool.notifyAll();
        }
        System.out.println("Write poolSize: "+ getWritePoolSize());
    }
    
    public static int getWritePoolSize(){
    	return pool.size();
    }
}
