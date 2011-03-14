package mpcs.test;

import nio.config.Debug;
import nio.util.LangUtil;
import mpcs.utils.MoreUtils;
import mpcs.utils.StringUtil;

/**
 * <p>Title:  测试 语言工具类</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-13
 */
public class MoreTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MoreUtils.trace(LangUtil.get("10001"), Debug.printTestInfo);
		
		String md5 = "00abcdeFGH&*%";
		MoreUtils.trace("MD5加密前：" + md5, true);
		MoreUtils.trace("MD5加密后：" + StringUtil.md5Encoder(md5), true);
		
		String base64 = "00abcdeFGH&*%";
		MoreUtils.trace("BASE64加密前：" + base64, true);
		String encoder = StringUtil.base64Encoder(base64);
		MoreUtils.trace("BASE64加密后：" + encoder, true);
		MoreUtils.trace("BASE64解密后：" + StringUtil.base64Decoder(encoder), true);
		
	}

}
