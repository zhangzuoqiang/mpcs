package mpcs.interfaces;

import java.io.Serializable;

/**
 * <p>Title: 客户端请求/服务端返回 消息 特征抽象</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-12
 */
public interface ISerial extends Serializable {
	
	/**
	 * 获取指定的消息头
	 * @param i
	 * @return
	 */
	int getHeadData(int i);
	
	/**
	 * 获取消息号，即head[0]
	 * @return TypeID
	 */
	int getTypeID();
}
