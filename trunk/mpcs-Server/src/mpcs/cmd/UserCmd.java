package mpcs.cmd;

/**
 * 用户注册 协议类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public class UserCmd extends AbstractCmd {

	/****/
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
