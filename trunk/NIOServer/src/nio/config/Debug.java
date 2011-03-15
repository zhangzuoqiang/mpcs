package nio.config;

/**
 * <p>Title: 调试信息配置</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-13
 */
public class Debug {
	
	/**
	 * 是否显示服务启动相关信息
	 */
	public static boolean printSystem = true;
	/**
	 * 是否输出异常信息
	 */
	public static boolean printException = true;
	/**
	 * 是否输出测试信息
	 */
	public static boolean printTestInfo = true;
	/**
	 * 是否显示请求信息
	 */
	public static boolean printInput = false;
	/**
	 * 是否显示返回信息
	 */
	public static boolean printOutput = false;
	/**
	 * 是否显示触发的事件
	 */
	public static boolean printFireEvent = false;
}
