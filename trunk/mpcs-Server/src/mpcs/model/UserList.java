package mpcs.model;

import java.util.HashMap;

/**
 * 存储在线用户数据
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public class UserList {

	private HashMap<String,User> userMap;
	private static UserList instance = null;
	
	private UserList(){
		userMap = new HashMap<String, User>();
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
	 * 添加在线用户
	 * @param user
	 */
	public void addUser(User user){
		if (!userMap.containsKey(user.getUserName())) {
			userMap.put(user.getUserName(), user);
		}
		else {
			return;
		}
	}
	
	/**
	 * 根据用户名获取用户
	 * @param name
	 * @return
	 */
	public User getUserByName(String name){
		if (userMap.containsKey(name)) {
			return userMap.get(name);
		}
		else {
			return null;
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
