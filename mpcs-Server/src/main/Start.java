package main;

import mpcs.config.ServerConfig;
import mpcs.handler.LogHandler;
import mpcs.handler.UserRegisterHandler;
import nio.net.Notifier;
import nio.net.Server;

/**
 * 服务器 启动类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class Start {
	
    public static void main(String[] args) {
    	
        try {
            LogHandler loger = new LogHandler();
            UserRegisterHandler register = new UserRegisterHandler();
            Notifier notifier = Notifier.getNotifier();
            notifier.addListener(loger);
            notifier.addListener(register);
            
            System.out.println("Server starting ...");
            Server server = new Server(ServerConfig.LISTENNING_PORT);
            Thread tServer = new Thread(server);
            tServer.start();
        }
        catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
            System.exit(-1);
        }
    }
}
