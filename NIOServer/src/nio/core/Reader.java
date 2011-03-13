package nio.core;

import java.util.List;
import java.util.LinkedList;
import java.nio.channels.SocketChannel;
import java.nio.channels.SelectionKey;
import java.nio.ByteBuffer;
import java.io.IOException;

import mpcs.utils.MoreUtils;

/**
 * <p>Title: 读线程</p>
 * <p>Description: 该线程用于读取客户端数据</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class Reader extends Thread {
	
    private static List<SelectionKey> pool = new LinkedList<SelectionKey>();
    private static int BUFFER_SIZE = 1024;
    protected static ByteBuffer buffer = null;
    private static Notifier notifier = Notifier.getNotifier();

    public Reader() {
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
                // 读取数据
                read(key);
            }
            catch (Exception e) {
                continue;
            }
        }
    }
    
    /**
     * 处理连接数据读取
     * @param key
     */
    public void read(SelectionKey key) {
        try {
            Packet clientData =  readRequest(key);
            if (clientData != null) {
            	// 输出客户端所有的请求内容
                clientData.printInfo(clientData.array());
                
                // 设置key.attachment
                Request request = (Request)key.attachment();
                request.setPacket(clientData);
                // 触发读处理
                notifier.fireOnRead(request);
                
                // 提交主控线程进行写处理
                NIOServer.processWriteRequest(key);
			}
        }
        catch (Exception e) {
            notifier.fireOnError("Error occured in Reader: " + e.getMessage());
        }
    }
    
    /**
     * 读取客户端发出请求数据
     * @param sc 套接通道
     */
    public static Packet readRequest(SelectionKey key){
    	SocketChannel sc = (SocketChannel) key.channel();
    	// buffer的长度
        int count = 0;
        Packet packet = null;
        buffer = ByteBuffer.allocate(BUFFER_SIZE);
        buffer.clear();
        // 读取客户端消息长度
		try {
			count = sc.read(buffer);
		} catch (IOException e) {
			notifier.fireOnError("Error occured in readRequest: " + e.getMessage());
		}
		if (count > 0) {
			buffer.flip();
			packet = new Packet(buffer);
		}
		buffer.clear();
		return packet;
    }
    
    /**
     * 处理客户请求,管理用户的连接池,并唤醒队列中的线程进行处理
     */
    public static void processRequest(SelectionKey key) {
        synchronized (pool) {
            pool.add(pool.size(), key);
            pool.notifyAll();
        }
        MoreUtils.trace("Read poolSize: " + getReadPoolSize());
    }
    
    /**
     * 返回读线程池大小
     * @return
     */
    public static int getReadPoolSize(){
    	return pool.size();
    }
}
