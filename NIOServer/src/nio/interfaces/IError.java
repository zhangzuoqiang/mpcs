package nio.interfaces;

/**
 * <p>Title: 接口： 监听系统所有错误信息</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-10
 */
public interface IError {
	
	/**
	 * 当客户端与服务器从连接开始到最后断开连接期间发生错误时触发该事件
	 * 通过该事件可以知道有什么错误发生，方便添加错误日志。
	 * @param error  错误信息
	 */
	void onError(String error);
	
}
