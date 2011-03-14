
import mpcs.utils.MoreUtils;
import nio.config.Debug;
import nio.config.ServerConfig;
import nio.core.NIOServerManager;
import nio.util.HandlerUtil;

/**
 * <p>Title: 服务器 启动类</p>
 * <p>Description: 用来启动主服务线程，以及注册业务处理器</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-8
 */
public class StartServer {

    public static void main(String[] args) {
        try {
            MoreUtils.trace("Server starting ...", Debug.printSystem);
            
        	// 注册Handler
        	HandlerUtil.AddHandlerListener();
        	
            NIOServerManager server = new NIOServerManager(ServerConfig.LISTENNING_PORT);
            Thread tServer = new Thread(server);
            tServer.start();
        } catch (Exception e) {
        	MoreUtils.trace("StartServer error: " + e.getMessage(), Debug.printException);
            System.exit(-1);
        }
    }
}
