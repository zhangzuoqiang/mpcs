package mpcs.interfaces;

/**
 * <p>Title: 客户端请求消息 接口</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-12
 */
public interface IMsg extends ISerial {
	/**
	 * 获取消息体，消息体类型为自定义封装类
	 * @return
	 */
	Object getBodys();
}
