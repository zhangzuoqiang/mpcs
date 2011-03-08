package mpcs.libs.configs;

/**
 * <p>存放所有全局错误消息号</p>
 * 命名规则: E_错误名
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public class GlobalErrorConst {

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
}
