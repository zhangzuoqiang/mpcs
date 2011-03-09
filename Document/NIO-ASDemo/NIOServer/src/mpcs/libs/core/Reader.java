package mpcs.libs.core;

import java.util.List;
import java.util.LinkedList;
import java.nio.channels.SocketChannel;
import java.nio.channels.SelectionKey;
import java.nio.ByteBuffer;

import mpcs.libs.configs.ServerConfig;
import mpcs.libs.data.ByteArrayPacket;

/**
 * <p>Title: 读线程</p>
 * <p>Description: 该线程用于读取客户端数据</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class Reader extends Thread {
	
    private static List<SelectionKey> pool = new LinkedList<SelectionKey>();
    private static Notifier notifier = Notifier.getNotifier();
    // 客户端缓存区
	protected ByteBuffer clientBuffer = ByteBuffer.allocate(ServerConfig.BUFFER_SIZE);
    
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
            	notifier.fireOnError("Error occured before Reader: " + e.getMessage());
            	e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 处理连接数据读取
     * @param key SelectionKey
     */
    public void read(SelectionKey key){
    	try {
    		SocketChannel channel = (SocketChannel) key.channel();
    		int count = channel.read(clientBuffer);
    		if(count > 0){
    			clientBuffer.flip();
    			ByteArrayPacket packet = new ByteArrayPacket(clientBuffer);
                
    			// 触发onRead
                notifier.fireOnRead(packet);

                // 提交主控线程进行写处理
                NIOServer.processWriteRequest(key);
                
    			/*// 此部分可以用来限制一个用户多次登录客户端command为客户端的编号
    			if(NIOServer.getInstance().commands.containsKey(command)){
    				ICommand comm = NIOServer.getInstance().commands.get(command);
    				int result = comm.execute(NIOServer.getInstance(), channel, packet);
    				key.attach(result);
    			}*/
    			clientBuffer.clear();
    		}
		} catch (Exception e) {
			notifier.fireOnError("Error occured in Reader: " + e.getMessage());
            e.printStackTrace();
		}
    }
    
    /**
     * 处理客户请求,管理用户的连接池,并唤醒队列中的线程进行处理
     */
    public static void processRequest(SelectionKey key) {
        synchronized (pool) {
            pool.add(pool.size(), key);
            pool.notifyAll();
        }
    }
    
    /**
     * 数组扩容
     * @param src byte[] 源数组数据
     * @param size int 扩容的增加量
     * @return byte[] 扩容后的数组
     */
    public static byte[] grow(byte[] src, int size) {
        byte[] tmp = new byte[src.length + size];
        System.arraycopy(src, 0, tmp, 0, src.length);
        return tmp;
    }
}
