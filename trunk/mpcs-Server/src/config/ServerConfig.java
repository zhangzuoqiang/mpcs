package config;

/**
 * 服务器配置类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class ServerConfig {
	/**本地字符集**/
	public static final String LOCAL_CHARSET = "UTF-8";
	/**host**/
	public static final String SERVER_ADDR = "localhost";
	/**本地服务器监听的端口**/
	public static final int LISTENNING_PORT = 843;
	/**缓冲区大小**/
	public static final int BUFFER_SIZE = 1024;
	/**超时时间,单位毫秒**/
	public static final int CONNECT_TIMEOUT = 1000;
	/**允许的最大线程数**/
	public static int MAX_THREADS = 4;
	/**策略文件**/
	public static final String POLICY_XML = "<cross-domain-policy> "
		+"<allow-access-from domain=\"*\" to-ports=\"1025-9999\"/>"
		+"</cross-domain-policy> ";
}
