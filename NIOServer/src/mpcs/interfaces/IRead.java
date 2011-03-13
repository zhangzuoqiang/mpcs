package mpcs.interfaces;

/**
 * <p>Title: 读取客户端请求数据 接口</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-12
 */
public interface IRead {
	
	//读取数据
	char readChar();
	byte readByte();
	float readFloat();
	long readLong();
	double readDouble();
	int readInt();
	short readShort();
	
	/**
	 * 读字符串时，先写长度，再写值
	 */
	String readString();
	String readString(String charset);
}
