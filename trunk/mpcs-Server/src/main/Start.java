package main;

import mpcs.config.ServerConfig;
import mpcs.utils.HandlerUtil;
import nio.net.Notifier;
import nio.net.Server;

/**
 * 服务 启动类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public final class Start {
	
	private static Notifier notifier = Notifier.getNotifier();
	
    public static void main(String[] args) {
    	
        try {
        	// 注册Handler
        	HandlerUtil.AddHandlerListener();
        	
            System.out.println("Server starting ...");
            Server server = new Server(ServerConfig.LISTENNING_PORT);
            Thread tServer = new Thread(server);
            tServer.start();
        }
        catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
            notifier.fireOnError("Error occured in Start : " + e.getMessage());
            System.exit(-1);
        }
    }
}
