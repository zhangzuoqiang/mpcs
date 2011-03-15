
import mpcs.utils.MoreUtils;
import nio.config.Debug;
import nio.config.ServerConfig;
import nio.core.NIOServerManager;

/**
 * <p>Title: 服务器 启动类</p>
 * <p>Description: 用来启动主服务线程，以及注册业务处理器</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public class StartServer {

    public static void main(String[] args) {
        try {
            MoreUtils.trace("Server starting ...", Debug.printSystem);
        	
            NIOServerManager server = new NIOServerManager(ServerConfig.LISTENNING_PORT);
            Thread tServer = new Thread(server);
            // 将该线程标记为守护线程
//            tServer.setDaemon(true);
            tServer.start();
        } catch (Exception e) {
        	MoreUtils.trace("StartServer error: " + e.getMessage(), Debug.printException);
            System.exit(-1);
        }
    }
}
