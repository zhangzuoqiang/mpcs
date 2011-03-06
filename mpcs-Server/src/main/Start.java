package main;

import handler.TimeHandler;
import utils.LogHandler;
import config.ServerConfig;
import nio.net.Notifier;
import nio.net.Server;
import nio.net.ServerHandler;

/**
 * 服务器 启动类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class Start {
	
    public static void main(String[] args) {
    	
        try {
            LogHandler loger = new LogHandler();
            TimeHandler timer = new TimeHandler();
            ServerHandler business = new ServerHandler();
            Notifier notifier = Notifier.getNotifier();
            notifier.addListener(loger);
            notifier.addListener(timer);
            notifier.addListener(business);
            
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
