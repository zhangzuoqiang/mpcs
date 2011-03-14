package mpcs.config;

/**
 * <p>Title: <p>存放所有全局错误消息号</p>
 * <p>Description: 命名规则: E_错误名</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public class GlobalErrorConst {

	/**全局错误标志**/
	public static final int GLOBAL_ERROR= 9;
	
	/**客户端发送的是空消息**/
	public static final int E_SEND_EMPTY = -99999;	
	/**此邮箱已注册**/
	public static final int E_USER_REGISTER = -10101;	
	/**注册用户失败，操作数据库添加用户时发生**/
	public static final int E_ADD_USER_FAILED = -10102;	
	/**不存在此用户**/
	public static final int E_NO_THIS_USER = -10201;	
	/**密码错误**/
	public static final int E_PASSWORD_WRONG = -10202;
	/**执行修改密码错误**/
	public static final int E_MODIFY_PASSWORD_WRONG = -20501;
}
