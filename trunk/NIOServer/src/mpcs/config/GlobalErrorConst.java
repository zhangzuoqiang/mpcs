package mpcs.config;

/**
 * <p>Title: <p>存放所有全局错误消息号</p>
 * <p>Description: 命名规则: E_错误名</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public class GlobalErrorConst {
	
	//================== 在我下方添加 =====================
	
	/**修改手机提示状态位无效**/
	public static final int E_INVALIDE_MOBILE_TIP = -20802;
	/**执行修改手机提示时出错**/
	public static final int E_OPEN_MOBILE_TIP = -20801;
	/**执行绑定手机时出错**/
	public static final int E_BIND_MOBILE = -20404;
	/**账户余额不足**/
	public static final int E_NO_ENOUGH_MONEY = -20403;
	/**绑定手机错误-该手机已存在**/
	public static final int E_BIND_MOBILE_EXIST = -20402;
	/**绑定手机错误**/
	public static final int E_BIND_MOBILE_OVER_FLOW = -20401;
	/**执行修改密码错误**/
	public static final int E_MODIFY_PASSWORD_WRONG = -20501;
	/**执行设置初始位置错误**/
	public static final int E_SET_POSITION_WRONG = -20601;
	/**保存用户联系信息错误**/
	public static final int E_SAVE_USER_CONTACT_INFO = -20401;
	/**保存用户基本信息错误**/
	public static final int E_SAVE_USER_BASIC_INFO = -20201;
	/**密码错误**/
	public static final int E_PASSWORD_WRONG = -10202;
	/**不存在此用户**/
	public static final int E_NO_THIS_USER = -10201;
	/**注册用户失败，操作数据库添加用户时发生**/
	public static final int E_ADD_USER_FAILED = -10102;	
	/**此邮箱已注册**/
	public static final int E_USER_REGISTER = -10101;	
	/**客户端发送的是空消息**/
	public static final int E_SEND_EMPTY = -99999;	
	/**全局错误标志**/
	public static final int GLOBAL_ERROR = 9;
}
