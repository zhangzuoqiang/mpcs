package mpcs.utils;

/**
 * Byte工具类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public class ByteUtil {

	/**
	 * <p>将消息号常量转换为byte[]</p>
	 * 客户端解析：
	 * <br/>h1: 返回结果为0表示成功;9表示失败（全局错误号）
	 * <br/>h2: 对应错误号，默认为0
	 * <br/>h3: 扩展位，默认为0
	 * @param cst
	 * @param head1
	 * @param head2
	 * @return
	 */
	public static byte[] getByteByConst(int cst, int head1, int head2){
		return ("" + head1 + cst + head2).getBytes();
	}
}
