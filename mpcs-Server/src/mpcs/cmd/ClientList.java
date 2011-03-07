package mpcs.cmd;

import java.util.HashMap;

import nio.net.Response;

/**
 * 存储在线用户数据
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public class ClientList {

	private HashMap<String,Response> clientMap;
	private static ClientList instance = null;
	
	private ClientList(){
		clientMap = new HashMap<String, Response>();
	}
	
	/**
	 * Singleton 模式，统一服务端在线用户数据
	 * @return
	 */
	public static synchronized ClientList getClientList() {
        if (instance == null) {
            instance = new ClientList();
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
	public void addClient(String ip, Response response){
		if (!clientMap.containsKey(ip)) {
			clientMap.put(ip, response);
			return;
		}
	}
	
	/**
	 * 移除在线用户
	 * @param ip
	 */
	public void removeClient(String ip){
		if (clientMap.containsKey(ip)) {
			clientMap.remove(ip);
			return;
		}
	}
	
	/**
	 * 返回在线人数
	 * @return
	 */
	public int getClientListSize(){
		return clientMap.size();
	}
	
}
