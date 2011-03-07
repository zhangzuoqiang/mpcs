package mpcs.config;

/**
 * <p>存放所有接受/返回成功的消息号</p>
 * 命名规则:
 * <br/>客户端发起:C_功能名
 * <br/>服务端返回:S_功能名
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public final class GlobalConst {
	
	/**空闲连接**/
	public static final int IDLE_CONNECTION = 55555;
	/**
	 * 用户注册
	 */
	public static final int C_USER_REGISTER = 100101;
	public static final int S_USER_REGISTER = 500101;
	
	/**
	 * 用户登录
	 */
	public static final int C_USER_LOGIN = 100102;
	public static final int S_USER_LOGIN = 500102;
	
	/**
	 * 用户基本信息
	 */
	public static final int C_USER_BASIC_INFO = 100201;
	public static final int S_USER_BASIC_INFO = 500201;
	
	/**
	 * 用户联系信息
	 */
	public static final int C_USER_CONTACT_INFO = 100202;
	public static final int S_USER_CONTACT_INFO = 500202;
	
	/**
	 * 修改密码
	 */
	public static final int C_USER_MODIFY_PWD = 100203;
	public static final int S_USER_MODIFY_PWD = 500203;
	
	/**
	 * 添加绑定手机
	 */
	public static final int C_ADD_BIND_MOBILE = 100204;
	public static final int S_ADD_BIND_MOBILE = 500204;
	
	/**
	 * 设定位置
	 */
	public static final int C_SET_POSITION = 100205;
	public static final int S_SET_POSITION = 500205;
	
	/**
	 * 开启手机提醒服务
	 */
	public static final int C_OPEN_MOBILE_TIP = 100206;
	public static final int S_OPEN_MOBILE_TIP = 500206;
	
	/**
	 * 轨迹
	 */
	public static final int C_LOCUS = 100301;
	public static final int S_LOCUS = 500301;
	
	/**
	 * 收件箱
	 */
	public static final int C_RECEIVE_BOX = 100401;
	public static final int S_RECEIVE_BOX = 500401;
	
	
}
