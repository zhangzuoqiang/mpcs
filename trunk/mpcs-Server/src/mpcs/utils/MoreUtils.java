package mpcs.utils;

/**
 * 消息输出工具类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-8
 */
public final class MoreUtils {
	
	/**
	 * 打印消息
	 * @param msg
	 */
	public static void trace(String msg){
		System.out.println(msg);
	}
	
	/**
	 * 处理客户端发送空消息到服务端
	 * @param response
	 */
	public static void doEmpty(){
		System.out.println("客户端发送空消息到服务端");
	}
}
