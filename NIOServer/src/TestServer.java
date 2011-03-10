import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import nio.configs.ServerConfig;
import nio.core.Notifier;
import nio.utils.MoreUtils;


/**
 * <p>Title: 服务端 测试类</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-10
 */
public class TestServer {
	// 事件触发器
	private static Notifier notifier = Notifier.getNotifier();
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		int i = 0;
		ArrayList<Socket> cache = new ArrayList<Socket>();
		for(i = 1; i <= 100000 ; i++) {
			try {
				Socket s = new Socket(ServerConfig.SERVER_ADDR, ServerConfig.LISTENNING_PORT);
				MoreUtils.trace(i + "");
				//创建了连接,但是不关闭, 保存到列表中以维持引用,防止被GC回收掉而使连接失效
				cache.add(s); 
			} catch (Exception e) {
				notifier.fireOnError("Error occured in TestServer: " + e.getMessage());
			}
		}
	}
}