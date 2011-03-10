
import nio.configs.ServerConfig;
import nio.control.CmdController;
import nio.core.NIOServer;
import nio.utils.MoreUtils;

/**
 * <p>Title: 服务器启动管理 类</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-10
 */
public class StartServer {
	
	public static void main(String[] args) {
		MoreUtils.trace("Server starting ...");
		
		// 注册业务处理类
		CmdController.register();
		
		NIOServer server = new NIOServer(ServerConfig.LISTENNING_PORT);
		Thread tServer = new Thread(server);
        tServer.start();
	}
}
