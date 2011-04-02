package nio.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * <p>Title: 语言管理 工具类</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-12
 */
public class LangUtil {
	
	private static Locale locale = Locale.getDefault();
	private static ResourceBundle resource = ResourceBundle.getBundle("res.resource", locale);
	
	/**
	 * 根据key得到value
	 * @param key
	 * @return
	 */
	public static String get(String key){
		return resource.getString(key);	
	}
}
