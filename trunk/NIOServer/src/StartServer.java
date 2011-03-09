import handler.ConnectHandler;
import nio.configs.GlobalConst;
import nio.configs.ServerConfig;
import nio.control.CmdController;
import nio.core.NIOServer;
import nio.utils.MoreUtils;

/**
 * 服务器启动管理 类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-9
 */
public class StartServer {
	
	public static void main(String[] args) {
		MoreUtils.trace("Server starting ...");
		registerAllCmds();
		
		NIOServer server = new NIOServer(ServerConfig.LISTENNING_PORT);
		Thread tServer = new Thread(server);
        tServer.start();
	}
	
	/**
	 * 在此注册系统所有的 消息号-处理类
	 */
	private static void registerAllCmds(){
		// 客户端请求连接
		CmdController.registerCmd(GlobalConst.C_REQUEST_CONNECTION, new ConnectHandler());
		// 
		
	}
}
