package nio.net;

import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.util.Iterator;

import mpcs.config.ServerConfig;

/**
 * <p>Title: 主控服务线程</p>
 * <p>Description: 基于事件回调的 NIO 多线程服务器模型</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class Server implements Runnable {
	
    private static List<SelectionKey> wpool = new LinkedList<SelectionKey>();  // 回应池
    private static HashMap<Integer, ChannelState> idToChannelState;
    private static Selector selector;
    private ServerSocketChannel sschannel;
    private InetSocketAddress address;
    protected Notifier notifier;
    private int port;
    
    /**
     * 创建主控服务线程
     * @param port 服务端口
     * @throws Exception
     */
    public Server(int port) throws Exception {
    	
    	idToChannelState = new HashMap<Integer, ChannelState>();
    	
        this.port = port;
        // 获取事件触发器
        notifier = Notifier.getNotifier();
        
        // 创建读写线程池
        for (int i = 0; i < ServerConfig.MAX_THREADS; i++) {
            Thread reader = new Reader();
            Thread writer = new Writer();
            reader.start();
            writer.start();
        }
        
        // 创建无阻塞网络套接
        selector = Selector.open();
        sschannel = ServerSocketChannel.open();
        sschannel.configureBlocking(false);
        
        address = new InetSocketAddress(port);
        ServerSocket ss = sschannel.socket();
        ss.bind(address);
        sschannel.register(selector, SelectionKey.OP_ACCEPT);
    }
    
    public void run() {
        System.out.println("Server started ...");
        System.out.println("Listening on port: " + port);
        // 监听
        while (true) {
            try {
                int num = selector.select();
                if (num <=0) {
                	addRegister();  // 在Selector中注册新的写通道
				}else{
                	// 获得就绪信道的键迭代器
                    Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                    
                    while (it.hasNext()) {
                        SelectionKey key = (SelectionKey) it.next();
                        
                        if (idToChannelState.get(key.channel().hashCode()) != null) {
                        	ChannelState state = idToChannelState.get(key.channel().hashCode());
                            if (state != null) {
                                if (!state.isStart()) {
                                    // haven't start yet, change it and save the start time
                                    state.setStart(true);
                                    state.setStartTime(System.currentTimeMillis());
                                }
                            }
                        } else {
                            // no state for this channel now, create a state and subscribe it
                            ChannelState state = new ChannelState();
                            idToChannelState.put(key.channel().hashCode(), state);
                            state.setThreadNum(state.getThreadNum() + 1);
                            System.out.println("key.channel().hashCode(): " + key.channel().hashCode());
                        }
                        
                        // 处理IO事件
                        if ( (key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                           // Accept the new connection
                           ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                           notifier.fireOnAccept();
                           
                           SocketChannel sc = ssc.accept();
                           sc.configureBlocking(false);
                           
                           // 触发接受连接事件
                           Request request = new Request(sc);
                           notifier.fireOnAccepted(request);
                           
                           // 注册读操作,以进行下一步的读操作
                           sc.register(selector,  SelectionKey.OP_READ, request);
                       }
                       else if ( (key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ ) {
                           Reader.processRequest(key);  // 提交读服务线程读取客户端数据
                           key.cancel();//将本socket的事件在选择器中删除
                       }
                       else if ( (key.readyOps() & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE ) {
                           Writer.processRequest(key);  // 提交写服务线程向客户端发送回应数据
                           key.cancel();
                       }
                        it.remove();
                    }
                }
            }
            catch (Exception e) {
            	// TODO: 当客户端在读取数据操作执行之前断开连接会产生异常信息
            	notifier.fireOnError("Error occured in Server: " + e.getMessage());
                continue;
            }
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
                        notifier.fireOnClosed((Request)key.attachment());
                    }
                    catch (Exception e1) {}
                    notifier.fireOnError("Error occured in addRegister: " + e.getMessage());
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
    
    /**
     * 获取ChannelState
     * @return
     */
    public static HashMap<Integer, ChannelState> getChannelState(){
    	return idToChannelState;
    }
}
