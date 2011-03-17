package mpcs.test;

import java.util.Date;

import mpcs.utils.DateUtil;
import mpcs.utils.JSONUtil;
import mpcs.utils.MoreUtils;
import mpcs.utils.StringUtil;
import net.sf.json.JSONObject;
import nio.config.Debug;
import nio.util.LangUtil;

/**
 * <p>Title:  测试工具类</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-12
 */
public class MoreTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		test_LangUtil(); // 测试语言工具
		test_Encoder(); // 测试加密解密
		test_Date(); // 测试long类型time
		test_JsonUtil(); // 测试JsonUtil工具类
		test_DateUtil();// 测试时间帮助类
	}
	
	private static void test_DateUtil(){
		MoreUtils.trace(DateUtil.getCurrentDate(), true);
		MoreUtils.trace(DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"), true);
		MoreUtils.trace(DateUtil.getNowTimestamp(), true);
		MoreUtils.trace(DateUtil.getDate4String("20110317124440"), true);
	}
	
	private static void test_JsonUtil(){
		JSONObject object = JSONUtil.file2JsonObject("resources/json/vip.json");
		MoreUtils.trace(object.getJSONObject("200001").getString("name"), true);
	}
	
	private static void test_Date(){
		long time1 = new Date().getTime();
		for(int i = 0 ; i < 99999999 ; i++){}
		long time2 = new Date().getTime();
		MoreUtils.trace("Time1: " + time1 + "\nTime2: " + time2, true);
	}
	
	private static void test_LangUtil(){
		MoreUtils.trace(LangUtil.get("10001"), Debug.printTestInfo);
	}
	
	private static void test_Encoder(){
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
