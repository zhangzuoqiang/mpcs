package nio.configs;

/**
 * <p>Title: 服务器配置类</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-10
 */
public class ServerConfig {
	/**本地字符集**/
	public static final String LOCAL_CHARSET = "UTF-8";
	/**host**/
	public static final String SERVER_ADDR = "172.25.135.248";
	/**本地服务器监听的端口**/
	public static final int LISTENNING_PORT = 843;
	/**缓冲区大小**/
	public static final int BUFFER_SIZE = 1024;
	/**超时时间,单位毫秒**/
	public static final int CONNECT_TIMEOUT = 3000;
	/**每个连接允许的最大读/写线程数**/
	public static final int MAX_THREADS = 4;
	/**请求策略文件**/
	public static final String POLICY_REQUEST = "<policy-file-request/>";
	/**返回策略文件**/
	public static final String POLICY_XML = "<cross-domain-policy>"
		   + "<allow-access-from domain=\"127.0.0.1\" to-ports=\"" + LISTENNING_PORT
		   + "\"/>" + "</cross-domain-policy>\r\n\0";
}
