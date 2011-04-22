package mpcs.client;

import COM.ibm.db2.app.UDF;

/**
 * <p>Title: DB2映射的用户自定义函数，用于当数据发生变化时触发事件</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-31
 */
public class ClientUDF extends UDF {
	
	/**
	 * 当用户绑定的phone有新的数据时触发
	 * @param email 用户邮箱
	 * @param phone 绑定的手机号
	 * @param lngAndLat 该手机对应的位置数据-经纬度，也可以是子网号
	 * @param mncLacCellID 该手机对应的位置数据-子网号
	 * @param tsp 时间戳String
	 * @return
	 */
	public static synchronized int fireNewPosTip(String email, String phone, String lngAndLat, String mncLacCellID, String tsp){
		int i = 0;
		
		return i;
	}
	
	/**
	 * 当绑定的手机超出设定的最大偏移距离时触发
	 * @param email
	 * @param phone
	 * @param radius
	 * @param lng
	 * @param lat
	 * @return
	 */
	public static synchronized int fireOutRadius(String email, String phone, double radius, double lng, double lat){
		int i = 0;
		
		return i;
	}
}
