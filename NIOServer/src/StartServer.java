import java.io.IOException;

import nio.core.ConnectCmd;
import nio.core.NIOServer;

/**
 * 服务器启动管理 类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-9
 */
public class StartServer {
	
	public static void main(String[] args) {
		int port = 8088;
		try {
			NIOServer server = new NIOServer(port);
			server.registerCommand(1000,new ConnectCmd());
			System.out.println("listening on " + port);
			server.listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
