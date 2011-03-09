package NIOServer;

import mpcs.libs.configs.ServerConfig;
import mpcs.libs.core.NIOServer;
import mpcs.libs.core.Notifier;
import mpcs.libs.utils.HandlerUtil;

/**
 * 服务 启动类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-8
 */
public class StartServer {
	
	private static Notifier notifier = Notifier.getNotifier();
	
	public static void main(String[] args) throws Exception{
		try {
			// 注册Handler
        	HandlerUtil.AddHandlerListener();
        	
			NIOServer server = new NIOServer(ServerConfig.LISTENNING_PORT);
			Thread tServer = new Thread(server);
	        tServer.start();
		} catch (Exception e) {
			notifier.fireOnError("Error occured in StartServer : " + e.getMessage());
            System.exit(-1);
		}
	}
}