package mpcs.db;

/**
 * <p>Title: 存放所有SQL语句</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-8
 */
public final class SQLangs {
	
	
	/**
	 * 通过email，修改密码
	 * @param email
	 * @param newpwd
	 * @return
	 */
	public static String updatePwd(String email, String newpwd){
		return "update user set password = '" + newpwd + "' where email = '" + email + "'";
	}
	/**
	 * 查询email，是否存在此用户
	 * @param email
	 * @return
	 */
	public static String selectBasicInfoByEmail(String email){
		return "select email, realname, gender, birthday, bloodtype, marstatus, job, education," +
				"residence, hometown, idcard from user where email = '" + email + "'";
	}
	
	/**
	 * 查询email，是否存在此用户
	 * @param email
	 * @return
	 */
	public static String selectUserByEmail(String email){
		return "select email , password from user where email = '" + email + "'";
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