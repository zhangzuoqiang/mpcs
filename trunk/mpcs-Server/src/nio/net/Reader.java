package nio.net;

import java.util.List;
import java.util.LinkedList;
import java.nio.channels.SocketChannel;
import java.nio.channels.SelectionKey;
import java.nio.ByteBuffer;
import java.io.IOException;
import java.io.InterruptedIOException;

import mpcs.config.ServerConfig;
import mpcs.utils.TraceUtil;

/**
 * <p>Title: 读线程</p>
 * <p>Description: 该线程用于读取客户端数据</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class Reader extends Thread {
	
    private static List<SelectionKey> pool = new LinkedList<SelectionKey>();
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
    public void read(SelectionKey key) {
        try {
        	// 读取客户端数据
            SocketChannel sc = (SocketChannel) key.channel();
            byte[] clientData =  readRequest(sc);
            
//            Response response = new Response(sc);
//            // 请求策略文件
//            if (clientData.equals(ServerConfig.POLICY_REQUEST.getBytes())) {
//            	response.send(ServerConfig.POLICY_XML.getBytes());
//			}
            
            Request request = (Request)key.attachment();
            request.setDataInput(clientData);
            
            // 输出客户端所有的请求内容
            TraceUtil.trace("客户端的请求内容：\n" + new String(request.getDataInput()));
            
            // calculate the processing time
            ChannelState state = Server.getChannelState().get(sc.hashCode());
            long operatedTime = 0;
            if (state != null) {
            	 // calculate the processing time
                operatedTime = System.currentTimeMillis() - state.getStartTime();
                // reset the start flag
                TraceUtil.trace("OperatedTimes: " + operatedTime + " ms");
                state.setStart(false);
            }
            
            // 触发onRead
            notifier.fireOnRead(request);

            // 提交主控线程进行写处理
            Server.processWriteRequest(key);
        }
        catch (Exception e) {
            notifier.fireOnError("Error occured in Reader: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 读取客户端发出请求数据
     * @param sc 套接通道
     */
    public static byte[] readRequest(SocketChannel sc) throws IOException {
    	
        ByteBuffer buffer = ByteBuffer.allocate(ServerConfig.BUFFER_SIZE);
        int off = 0;
        int len = 0;// buffer的长度
        byte[] data = new byte[ServerConfig.BUFFER_SIZE * 10];
        
        while ( true ) {
            buffer.clear();
            try {
            	len = sc.read(buffer);
			} catch (InterruptedIOException e) {
				notifier.fireOnError("Error occured in read(buffer): " + e.getMessage());
				e.printStackTrace();
				break;
			}
            
            if (len == -1) break;
            if ( (off + len) > data.length) {// 扩容
                data = grow(data, ServerConfig.BUFFER_SIZE * 10);
            }
            byte[] buf = buffer.array();
            System.arraycopy(buf, 0, data, off, len);// 复制数组
            off += len;
        }
        byte[] req = new byte[off];
        System.arraycopy(data, 0, req, 0, off);
        return req;
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
