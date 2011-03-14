package nio.manager;

import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import nio.interfaces.ISession.SessionStatus;

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
	 * 移除指定email的Session
	 * @param email
	 */
	public void removeSession(String email){
		synchronized (sessionMap) {
            if (sessionMap.containsKey(email))
            	sessionMap.remove(email);
        }
	}
	
	/**
	 * 检查当前是否已经登录，如果已经登录，则更新为当前登录
	 * @param email
	 * @return
	 */
	public boolean isOnLine(String email, SocketChannel socket){
		if (sessionMap.containsKey(email)) {
			// 更新session数据
			Session session = sessionMap.get(email);
			session.updateAll(SessionStatus.ONLINE, socket, new Date().getTime());
			return true;
		}
		// 添加该用户的session数据
		Session session = new Session(SessionStatus.ONLINE, socket, new Date().getTime());
		sessionMap.put(email, session);
		return false;
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
	 * 系统共用一个HashMap<String, Session>
	 * @return
	 */
	public HashMap<String, Session> map(){
		return this.sessionMap;
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
