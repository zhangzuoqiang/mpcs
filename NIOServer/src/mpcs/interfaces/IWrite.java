package mpcs.interfaces;

/**
 * <p>Title: 写数据返回客户端 接口</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-13
 */
public interface IWrite {
	
	//写入数据
	void writeChar(char value);
	void writeByte(byte value);
	void writeFloat(float value);
	void writeLong(long value);
	void writeDouble(double value);
	void writeInt(int value);
	void writeShort(short value);
	void writeBytes(byte[] bytes);
	void writeUTF(String str);
	/**
	 * 写字符串时，先写长度，再写值
	 * @param str
	 */
	void writeString(String str);
	void writeString(String str,String charset);
}
