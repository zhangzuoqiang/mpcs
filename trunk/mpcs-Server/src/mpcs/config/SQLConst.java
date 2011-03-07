package mpcs.config;

/**
 * <p>存放所有SQL语句</p>
 * 命名规则: 方式_功能名
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public class SQLConst {

	/**查询email，是否存在此用户**/
	public static final String SELECT_USER_BY_EMAIL = "select email from user where email = '";
	/**根据email查询pwd，验证登录**/
	public static final String SELECT_PASSWORD_BY_EMAIL = "select password from user where email = '";
}
