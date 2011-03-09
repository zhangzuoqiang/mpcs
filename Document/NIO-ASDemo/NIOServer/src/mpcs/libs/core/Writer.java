package mpcs.libs.core;

import java.util.List;
import java.util.LinkedList;
import java.nio.channels.SocketChannel;
import java.nio.channels.SelectionKey;

import mpcs.libs.data.ByteArrayPacket;

/**
 * <p>Title: 回应线程</p>
 * <p>Description: 用于向客户端发送信息</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public final class Writer extends Thread {
	
    private static List<SelectionKey> pool = new LinkedList<SelectionKey>();
    private static Notifier notifier = Notifier.getNotifier();
    
    public Writer() {
    }
    
    /**
     * SMS发送线程主控服务方法,负责调度整个处理过程
     */
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
                // 处理写事件
                write(key);
            }
            catch (Exception e) {
                continue;
            }
        }
    }
    
    /**
     * 处理向客户发送数据
     * @param key SelectionKey
     */
    public void write(SelectionKey key) {
        try {
        	SocketChannel sc = (SocketChannel) key.channel();
            Response response = new Response(key);
            
            // 触发onWrite事件
            notifier.fireOnWrite((ByteArrayPacket) key.attachment(), response);
            
            sc.finishConnect();
			sc.socket().close();
            sc.close();
            // 触发onClosed事件
            notifier.fireOnClosed((ByteArrayPacket) key.attachment());
        }
        catch (Exception e) {
            notifier.fireOnError("Error occured in Writer: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 处理客户请求,管理用户的联结池,并唤醒队列中的线程进行处理
     */
    public static void processRequest(SelectionKey key) {
        synchronized (pool) {
            pool.add(pool.size(), key);
            pool.notifyAll();
        }
    }
}
