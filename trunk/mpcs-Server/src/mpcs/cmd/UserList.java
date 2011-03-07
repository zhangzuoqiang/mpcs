package mpcs.cmd;

import java.util.HashMap;


/**
 * 存储在线用户数据
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public class UserList {

	private HashMap<String,UserCmd> userMap;
	private static UserList instance = null;
	
	private UserList(){
		userMap = new HashMap<String, UserCmd>();
	}
	
	/**
	 * Singleton 模式，统一服务端在线用户数据
	 * @return
	 */
	public static synchronized UserList getUserList() {
        if (instance == null) {
            instance = new UserList();
            return instance;
        }
        else 
        	return instance;
    }
	
	/**
	 * 添加在线用户,并且标识用户
	 * @param ip
	 * @param user
	 */
	public void addUser(String ip, UserCmd user){
		if (!userMap.containsKey(user.getEmail())) {
			userMap.put(ip, user);
			return;
		}
	}
	
	/**
	 * 返回在线人数
	 * @return
	 */
	public int getUserListSize(){
		return userMap.size();
	}
	
}
