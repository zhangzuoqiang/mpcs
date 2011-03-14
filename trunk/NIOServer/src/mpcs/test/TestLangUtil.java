package mpcs.test;

import nio.config.Debug;
import nio.util.LangUtil;
import mpcs.utils.MoreUtils;

/**
 * <p>Title:  测试 语言工具类</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-13
 */
public class TestLangUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MoreUtils.trace(LangUtil.get("10001"), Debug.printTestInfo);
	}

}
