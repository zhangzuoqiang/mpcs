package mpcs.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-8
 */
public final class MoreUtils {
	
	/**
	 * 打印消息
	 * @param msg
	 */
	public static void trace(Object msg){
		System.out.println(msg);
	}
	
	/**客户端发送命令的合法格式**/
	protected static final String pattern = "^1([0-9]{5})$";
	/**
	 * 检查客户端发送的命令是否合法
	 * @param pattern 
	 * @return 如果匹配，返回true；否则返回false
	 */
	public static boolean isCommand(Object command){
		Pattern pat = Pattern.compile(pattern);
		Matcher matcher = pat.matcher(String.valueOf(command));
		return matcher.matches();
	}
}
