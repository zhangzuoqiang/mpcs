package nio.core;

import java.util.List;
import java.util.LinkedList;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import mpcs.utils.MoreUtils;

/**
 * <p>Title: 回应线程</p>
 * <p>Description: 将处理结果返回给客户端</p>
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
     * @param key
     */
    public void write(SelectionKey key) {
    	SocketChannel sc = (SocketChannel) key.channel();
        try {
        	if (key.isValid() && key.isWritable()) {
                Response response = new Response(key);
                // 触发onWrite事件
                notifier.fireOnWrite((Request)key.attachment(), response);
                
                notifier.fireOnClosed((Request)key.attachment());
                MoreUtils.trace(key.attachment());
                
                // 一次请求处理完毕，关闭连接（短连接）
                sc.finishConnect();
                sc.socket().close();
                sc.close();
			}
        }
        catch (Exception e) {
        	MoreUtils.trace(key.isValid() + "   " + key.isConnectable());
            notifier.fireOnError("Error occured in Writer: " + e.getMessage());
        }
    }
    
    /**
     *  处理客户请求,管理用户的联结池,并唤醒队列中的线程进行处理
     * @param key
     */
    public static void processRequest(SelectionKey key) {
        synchronized (pool) {
            pool.add(pool.size(), key);
            pool.notifyAll();
        }
        MoreUtils.trace("Write poolSize: "+ getWritePoolSize());
    }
    
    /**
     * 返回回应池的大小
     * @return
     */
    public static int getWritePoolSize(){
    	return pool.size();
    }
}