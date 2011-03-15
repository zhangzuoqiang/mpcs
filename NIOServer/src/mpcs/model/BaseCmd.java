package mpcs.model;

import nio.core.Packet;
import mpcs.config.GlobalErrorConst;
import mpcs.interfaces.ICmd;
import mpcs.interfaces.IWrite;

/**
 * <p>Title: 返回客户端 基本消息类型</p>
 * <p>Description: 包含了一条消息的基本内容</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-13
 */
public class BaseCmd implements ICmd , IWrite{

	/**序列化成字符串，隐藏类的字段，实现加密功能**/
	private static final long serialVersionUID = 1L;
	
	/**
	 * 消息头数组，格式：
	 * <br/><b>0 位</b>-消息号（TypeID）
	 * <br/><b>1 位</b>-是否正确（0为正确，9为错误）
	 * <br/><b>2 位</b>-（1位若为0，则此位为0）具体错误号
	 */
	protected int head[] = { 0, 0, 0 };
	protected Packet packet = new Packet();
	
	/**
	 * 返回客户端  基本消息类型  构造方法
	 * @param typeID*  消息号
	 */
	public BaseCmd(int typeID){
		head[0] = typeID;
		writeHead(0,0);
	}
	
	/**
	 * 发送错误消息
	 * @param typeID
	 * @param head2
	 */
	public BaseCmd(int typeID, int head2){
		head[0] = typeID;
		writeHead( GlobalErrorConst.GLOBAL_ERROR,head2);
	}

	@Override
	public int getHeadData(int i) {
		if(i<0 || i>2)
			return -1;
		return head[i];
	}

	@Override
	public int getTypeID() {
		return head[0];
	}

	@Override
	public void setHeadData(int i, int value) {
		if( i>=0 && i<=2 )
			head[i] = value;
	}

	@Override
	public void setTypeID(int typeID) {
		head[0] = typeID;
	}

	@Override
	public void writeHead(int head1, int head2) {
		head[1] = head1;
		head[2] = head2;
		// 写入消息头
		packet.writeInt(head[0]);
		packet.writeInt(head[1]);
		packet.writeInt(head[2]);
	}

	@Override
	public void clear() {
		this.packet = new Packet();
	}

	@Override
	public Packet getPacket() {
		return this.packet;
	}

	@Override
	public void writeChar(char value) {
		// TODO Auto-generated method stub
		packet.writeChar(value);
	}

	@Override
	public void writeByte(byte value) {
		// TODO Auto-generated method stub
		packet.writeByte(value);
	}

	@Override
	public void writeFloat(float value) {
		// TODO Auto-generated method stub
		packet.writeFloat(value);
	}

	@Override
	public void writeLong(long value) {
		// TODO Auto-generated method stub
		packet.writeLong(value);
	}

	@Override
	public void writeDouble(double value) {
		// TODO Auto-generated method stub
		packet.writeDouble(value);
	}

	@Override
	public void writeInt(int value) {
		// TODO Auto-generated method stub
		packet.writeInt(value);
	}

	@Override
	public void writeShort(short value) {
		// TODO Auto-generated method stub
		packet.writeShort(value);
	}

	@Override
	public void writeBytes(byte[] bytes) {
		// TODO Auto-generated method stub
		packet.writeBytes(bytes);
	}

	@Override
	public void writeUTF(String str) {
		// TODO Auto-generated method stub
		packet.writeUTF(str);
	}

	@Override
	public void writeString(String str) {
		// TODO Auto-generated method stub
		packet.writeString(str);
	}

	@Override
	public void writeString(String str, String charset) {
		// TODO Auto-generated method stub
		packet.writeString(str, charset);
	}
}
