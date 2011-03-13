package mpcs.interfaces;

import nio.core.Packet;

/**
 * <p>Title: 返回给客户端信息 接口</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-12
 */
public interface ICmd extends ISerial{
	/**
	 * 设置指定位的消息头，若不指定，默认为0
	 * @param i
	 */
	void setHeadData(int i, int value);
	
	/**
	 * 设置消息号，head[0]
	 * @param typeID
	 */
	void setTypeID(int typeID);
	
	/**
	 * 获取将要发送的数据对象
	 * @return
	 */
	Packet getPacket();
	
	/**
	 * 将消息头写入packet
	 * @param head1
	 * @param head2
	 */
	void writeHead(int head1, int head2);
	
	/**
	 * 重置packet
	 */
	void clear();
}
