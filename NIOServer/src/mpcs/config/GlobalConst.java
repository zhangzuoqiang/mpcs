package mpcs.config;

/**
 * <p>存放所有请求/返回的消息号</p>
 * 命名规则:
 * <br/>客户端发起:C_功能名
 * <br/>服务端返回:S_功能名
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public final class GlobalConst {
	
	/**测试（时间服务器）用**/
	public static final int C_TEST = 100000;
	public static final int S_TEST = 500000;
	
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
	 * 保存用户基本信息
	 */
	public static final int C_USER_SAVE_BASIC_INFO = 100202;
	public static final int S_USER_SAVE_BASIC_INFO = 500202;
	
	/**
	 * 用户联系信息
	 */
	public static final int C_USER_CONTACT_INFO = 100203;
	public static final int S_USER_CONTACT_INFO = 500203;
	
	/**
	 * 保存用户联系信息
	 */
	public static final int C_USER_SAVE_CONTACT_INFO = 100204;
	public static final int S_USER_SAVE_CONTACT_INFO = 500204;
	
	/**
	 * 修改密码
	 */
	public static final int C_USER_MODIFY_PWD = 100205;
	public static final int S_USER_MODIFY_PWD = 500205;
	
	/**
	 * 添加绑定手机
	 */
	public static final int C_ADD_BIND_MOBILE = 100206;
	public static final int S_ADD_BIND_MOBILE = 500206;
	
	/**
	 * 请求绑定手机列表
	 */
	public static final int C_BIND_MOBILE_LIST = 100209;
	public static final int S_BIND_MOBILE_LIST = 500209;
	
	/**
	 * 设定位置
	 */
	public static final int C_SET_POSITION = 100207;
	public static final int S_SET_POSITION = 500207;
	
	/**
	 * 开启手机提醒服务
	 */
	public static final int C_OPEN_MOBILE_TIP = 100208;
	public static final int S_OPEN_MOBILE_TIP = 500208;
	
	/**
	 * 轨迹
	 */
	public static final int C_POSITION_LIST = 100301;
	public static final int S_POSITION_LIST = 500301;
	
	/**
	 * 收件箱
	 */
	public static final int C_RECEIVE_BOX = 100401;
	public static final int S_RECEIVE_BOX = 500401;
	
	
	
	
}
