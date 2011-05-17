package nio.core;

import java.util.List;
import java.util.LinkedList;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.util.Iterator;

import nio.config.Debug;
import nio.config.ServerConfig;
import nio.util.LangUtil;

import mpcs.utils.MoreUtils;

/**
 * <p>Title: 主控服务线程</p>
 * <p>Description: 基于事件回调的 NIO 多线程服务器模型</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class NIOServerManager implements Runnable {
	
	/** 回应池 **/
    private static List<SelectionKey> wpool = new LinkedList<SelectionKey>();
    public static Selector selector;
    protected static Notifier notifier;
    private int port;
    
    /**
     * 创建主控服务线程
     * @param port 服务端口
     * @throws Exception
     */
    public NIOServerManager(int port) throws Exception {
    	
    	
        this.port = port;
        // 获取事件触发器
        notifier = Notifier.getNotifier();
        // 创建读/写线程池
        for (int i = 1; i <= ServerConfig.MAX_THREADS; i++) {
            Thread r = new Reader();
            Thread w = new Writer();
            r.start();
            w.start();
        }
        selector = this.getSelector(port);
    }
    
    public void run() {
    	MoreUtils.trace(LangUtil.get("10008"), Debug.printSystem);
    	MoreUtils.trace(LangUtil.get("10009") + port, Debug.printSystem);
        listen();
    }
    
    /**
	 * 获取Selector，创建无阻塞网络套接
	 * @param port
	 * @return
	 * @throws IOException
	 */
	protected Selector getSelector(int port) throws IOException {
		ServerSocketChannel server = ServerSocketChannel.open();
		Selector selector = Selector.open();
		server.socket().bind(new InetSocketAddress(port));
		server.configureBlocking(false);
		server.register(selector, SelectionKey.OP_ACCEPT);
		return selector;
	}
    
    /**
	 * 监听端口
	 */
	public void listen() {
		try {
			while (true) {
				int num = selector.select();
                if (num > 0) {
                	// 获得就绪信道的键迭代器
                	Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                    while (it.hasNext()) {
                        SelectionKey key = (SelectionKey) it.next();
                        //处理I/O操作
                        processIO(key);
                        it.remove();
                    }
                } else {
                	// 添加新的通道注册
                    addRegister();
                }
			}
		} catch (Exception e) {
			if (Debug.printException) {
				e.printStackTrace();
			}
			if (Debug.printFireEvent) {
				notifier.fireDoError(LangUtil.get("10010") + e.getMessage());
			}
		}
	}
	
	/**
	 * 处理I/O事件
	 * @param key
	 * @throws Exception
	 */
	protected static void processIO(SelectionKey key) throws Exception {
//		if ( (key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
		if(key.isAcceptable()){
			// 接收新的连接请求
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            notifier.fireDoAccept();
            SocketChannel sc = ssc.accept();
            // 设置非阻塞模式
            sc.configureBlocking(false);
            // 触发接受连接事件
            Request request = new Request(sc);
            notifier.fireDoAccepted(request);
            
            // 注册读操作,以进行下一步的读操作
            sc.register(selector,  SelectionKey.OP_READ, request);
        }
//        else if ( (key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ ) {
		else if (key.isReadable()) {
			// 提交读服务线程读取客户端数据
            Reader.processRequest(key);
            //将本socket的事件在选择器中删除
            key.cancel();
        }
//        else if ( (key.readyOps() & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE ) {
		else if (key.isWritable()) {
			// 提交写服务线程向客户端发送回应数据
            Writer.processRequest(key); 
            key.cancel();
        }
	}
    
	/**
	 * 添加新的通道注册
	 */
    private void addRegister() {
        synchronized (wpool) {
            while (!wpool.isEmpty()) {
                SelectionKey key = (SelectionKey) wpool.remove(0);
                SocketChannel schannel = (SocketChannel)key.channel();
                try {
                    schannel.register(selector,  SelectionKey.OP_WRITE, key.attachment());
                }
                catch (Exception e) {
                    try {
                        schannel.finishConnect();
                        schannel.close();
                        schannel.socket().close();
                        notifier.fireDoClosed((Request)key.attachment());
                    }
                    catch (Exception e1) {}
                    if (Debug.printException) {
                    	e.printStackTrace();
					}
                    notifier.fireDoError(LangUtil.get("10013") + e.getMessage());
                }
            }
        }
    }
    
    /**
     * 提交新的客户端写请求于主服务线程的回应池中
     * @param key
     */
    public static void processWriteRequest(SelectionKey key) {
        synchronized (wpool) {
            wpool.add(wpool.size(), key);
            wpool.notifyAll();
        }
        // 解除selector的阻塞状态，以便注册新的通道
        selector.wakeup();
    }
}
