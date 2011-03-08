package nio.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import mpcs.config.ServerConfig;
import mpcs.utils.MoreUtils;
import nio.net.interfaces.ICommand;

/**
 * <p>Title: 主控服务线程</p>
 * <p>Description: 基于事件回调的 NIO 多线程服务器模型</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class Server implements Runnable {
	
    private static List<SelectionKey> wpool = new LinkedList<SelectionKey>();  // 回应池
    private HashMap<Integer,ICommand> commands=new HashMap<Integer,ICommand>();
    /**在线用户列表**/
	public List<Integer> ids=new ArrayList<Integer>();
    /**客户端缓存区**/
	protected ByteBuffer clientBuffer = ByteBuffer.allocate(ServerConfig.BUFFER_SIZE);
	private static Selector selector;
    private Notifier notifier;
    /**解码**/
	protected CharsetDecoder decoder;
    
    /**
     * 创建主控服务线程
     * @param port 服务端口
     * @throws Exception
     */
    public Server(int port) throws Exception {
    	selector = this.getSelector(port);
    	Charset charset = Charset.forName(ServerConfig.LOCAL_CHARSET);
		decoder = charset.newDecoder();
        // 获取事件触发器
        notifier = Notifier.getNotifier();
        
        // 创建读/写线程池
        for (int i = 0; i < ServerConfig.MAX_THREADS; i++) {
            Thread reader = new Reader();
            Thread writer = new Writer();
            reader.start();
            writer.start();
        }
    }
    
    public void run() {
    	MoreUtils.trace("Server started ...");
    	MoreUtils.trace("Listening on port: " + ServerConfig.LISTENNING_PORT);
    	
    	registerCommand(1000,new ConnectCommand());
    	try {
			listen();
		} catch (Exception e) {
			e.printStackTrace();
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
	 * 获取Selector,创建无阻塞网络套接
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
    
    protected Selector getSelector(){
    	return selector;
    }
	
	public void registerCommand(int commandID,ICommand command){
		if(!commands.containsKey(commandID)){
			MoreUtils.trace("registeCommand  "+commandID+"  ,  "+command);
			commands.put(commandID, command);
		}
	}
	
	/**
	 * 监听端口
	 * @throws Exception 
	 */
	public void listen() throws Exception {
		try {
			while(true){
				int num=selector.select();
				if (num > 0) {
					// 获得就绪信道的键迭代器
					Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
					while (iter.hasNext()) {
						SelectionKey key = iter.next();
						iter.remove();
						processIO(key);//处理I/O操作
					}
				}else {
                	addRegister();  // 在Selector中注册新的写通道
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 处理I/O事件
	 * @param key
	 * @throws Exception 
	 */
	protected void processIO(SelectionKey key) throws Exception {
		// if ( (key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) 
		if (key.isAcceptable()) {
			// 接收新的连接请求
			ServerSocketChannel server = (ServerSocketChannel) key.channel();
			notifier.fireOnAccept();
			SocketChannel channel = server.accept();
			// 设置非阻塞模式
			channel.configureBlocking(false);
			// 触发接受连接事件
            Request request = new Request(channel);
            notifier.fireOnAccepted(request);
			// 注册读操作,以进行下一步的读操作
			channel.register(selector, SelectionKey.OP_READ, request);
		} else if (key.isReadable()) {
			Reader.processRequest(key);  // 提交读服务线程读取客户端数据
            key.cancel();//将本socket的事件在选择器中删除
		} else if (key.isWritable()) {
			 Writer.processRequest(key);  // 提交写服务线程向客户端发送回应数据
             key.cancel();
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
