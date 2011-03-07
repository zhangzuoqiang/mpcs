package mpcs.model;

public class User {
	
	private String userName;
	private String password;
	
	/**得到用户名**/
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**得到密码**/
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
