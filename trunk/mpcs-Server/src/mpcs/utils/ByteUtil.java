package mpcs.utils;

/**
 * Byte工具类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public class ByteUtil {

	/**
	 * 将消息号常量转换为byte[]
	 * @param cst
	 * @param head1
	 * @param head2
	 * @return
	 */
	public static byte[] getByteByConst(int cst, int head1, int head2){
		return (cst + "" + head1 + head2).getBytes();
	}
}
