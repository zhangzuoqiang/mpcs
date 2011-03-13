package nio.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import mpcs.utils.MoreUtils;

/**
 * <p>Title: 加载服务器资源文件，单例模式</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-13
 */
public class LoadResUtil {
	
	private static Properties res = null;
	private static FileInputStream in = null;
	
	public static void initLangRes(){
		res = new Properties();
		try {
			in = new FileInputStream("resources/locale/zh_CN/lang.properties");
		} catch (FileNotFoundException e) {
			MoreUtils.trace(LangUtil.get("10005"));
			System.exit(0);
		}
		
		try {
			res.load(in);
		} catch (IOException e) {
			MoreUtils.trace(LangUtil.get("10006"));
		}
	}
	
	public static Properties getProperties(){
		if (res == null) {
			initLangRes();
			return res;
		}
		return res;
	}
}
