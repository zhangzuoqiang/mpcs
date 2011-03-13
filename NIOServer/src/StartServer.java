
import mpcs.utils.MoreUtils;
import nio.core.NIOServer;
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
        	// 注册Handler
        	HandlerUtil.AddHandlerListener();

            MoreUtils.trace("Server starting ...");
            NIOServer server = new NIOServer(5100);
            Thread tServer = new Thread(server);
            tServer.start();
        } catch (Exception e) {
        	MoreUtils.trace("StartServer error: " + e.getMessage());
            System.exit(-1);
        }
    }
}