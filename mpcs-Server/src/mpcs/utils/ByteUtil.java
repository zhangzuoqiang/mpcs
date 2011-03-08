package mpcs.utils;

/**
 * Byte工具类 服务端
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public final class ByteUtil {

	/**
	 * <p>将消息号常量转换为byte[]</p>
	 * 客户端解析：
	 * <br/>h1: 返回结果为0表示成功;9表示失败（全局错误号）
	 * <br/>h2: 对应全局消息号
	 * <br/>h3: 扩展位，默认为0
	 * @param globalError 全局错误号
	 * @param globalCst    全局消息号
	 * @param extension    默认为0
	 * @return byte[]
	 */
	public static byte[] getByteByConst(int globalError, int globalCst, int extension){
		return ("" + globalError + globalCst + extension).getBytes();
	}
}
