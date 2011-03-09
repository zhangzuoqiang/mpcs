import java.io.IOException;

import nio.configs.ServerConfig;
import nio.core.ConnectCmd;
import nio.core.NIOServer;
import nio.utils.MoreUtils;

/**
 * 服务器启动管理 类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-9
 */
public class StartServer {
	
	public static void main(String[] args) {
		try {
			NIOServer server = new NIOServer(ServerConfig.LISTENNING_PORT);
			server.registerCommand(1000,new ConnectCmd());
			MoreUtils.trace("listening on " + ServerConfig.LISTENNING_PORT);
			server.listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
