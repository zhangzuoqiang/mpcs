package nio.manager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * <p>Title: Session 管理类</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-14
 */
public class SessionManager {
	
	private static SessionManager instance = null;
	// 在线用户Session HashMap
	private HashMap<String,Session> sessionMap = null;
	
	private SessionManager(){
		sessionMap = new HashMap<String, Session>();
	}
	
	/**
	 * 添加一个session
	 * @param session
	 */
	public void addSession(String email, Session session){
		synchronized (sessionMap) {
            if (!sessionMap.containsKey(email))
            	sessionMap.put(email, session);
        }
	}
	
	/**
	 * 清除过期的session
	 */
	public void checkValidity(){
		Iterator<Entry<String, Session>> it = sessionMap.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, Session> entry = it.next();
			if (entry.getValue().isExpired()) {
				sessionMap.remove(entry.getKey());
			}
		}
	}
	
	/**
	 * SessionManager单例类
	 * @return
	 */
	public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
            return instance;
        }else{
        	return instance;
        }
    }
}
