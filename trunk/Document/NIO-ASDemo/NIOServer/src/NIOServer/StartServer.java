package NIOServer;

import java.io.IOException;

import mpcs.libs.cmds.ConnectCmd;
import mpcs.libs.configs.ServerConfig;
import mpcs.libs.core.NIOServer;
import mpcs.libs.utils.MoreUtil;

/**
 * 服务 启动类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-8
 */
public class StartServer {
	
	public static void main(String[] args) throws Exception{
		try {
			NIOServer server = new NIOServer(ServerConfig.LISTENNING_PORT);
			server.registerCommand(1000,new ConnectCmd());
			MoreUtil.trace("listening on " + ServerConfig.LISTENNING_PORT);
			server.listen();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}