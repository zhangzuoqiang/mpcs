package nio.util;

/**
 * <p>Title: 语言管理 工具类</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-12
 */
public class LangUtil {
	
	/**
	 * 根据key得到value
	 * @param key
	 * @return
	 */
	public static String get(String key){
		String value = "";
		value = LoadResUtil.getProperties().getProperty(key);
		return value;
	}
}