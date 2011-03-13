package mpcs.db;

/**
 * <p>存放所有SQL语句</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public final class SQLangs {

	/**
	 * 查询email，是否存在此用户
	 * @param email
	 * @return
	 */
	public static String selectUserByEmail(String email){
		return "select email from user where email = '" + email + "'";
	}
	
	/**
	 * 根据email查询pwd，验证登录
	 * @param email
	 * @return
	 */
	public static String selectPwdByEmail(String email){
		return "select password from user where email = '" + email + "'";
	}
	
	public static String addUser(String email, String pwd){
		return "INSERT INTO user(EMAIL,PASSWORD) VALUES('" + email + "','" + pwd + "');";
	}
}
