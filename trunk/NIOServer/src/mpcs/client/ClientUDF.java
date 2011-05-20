package mpcs.client;

import java.io.FileWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

import COM.ibm.db2.app.UDF;

/**
 * <p>Title: DB2映射的用户自定义函数，用于当数据发生变化时触发事件</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-4-10
 */
public class ClientUDF extends UDF {
		
	private static SocketAddress address;
	private static SocketChannel client ;
	private static ByteBuffer buffer;
    
	public static void main(String[] args) {
		fireNewPosTip("15876995100", "22.90595;113.86926");
	}
	
	/*
	create function lenovo.fireNewPosTip(phone varchar(20),
    latlng varchar(50))
       returns int
       fenced
       variant
       no sql
       external action
       language java
       parameter style java
       external name 'ClientUDF!fireNewPosTip'
       
       updateLocationTrigger
       CREATE TRIGGER LENOVO.UPDATELOCATIONTRIGGER AFTER  INSERT  ON LENOVO.POSITIONS  REFERENCING  NEW AS newrow  FOR EACH ROW  MODE DB2SQL 
BEGIN ATOMIC
values(fireNewPosTip(newrow.phone,newrow.lngandlat,newrow.posttimestamp,newrow.currentBalance,500.10)) ;


BEGIN ATOMIC
values(fireNewPosTip(newrow.phone,newrow.lngandlat)) ;
END
	 */
	
	/**
	 * 当用户绑定的phone有新的数据时触发
	 * @param phone
	 * @param lngAndLat
	 * @param mncLacCellID
	 * @param tsp
	 * @return
	 */
	public static int fireNewPosTip(String phone, String latlng){
		int i = 1;
		String[] ss = new String[2];
		Double latitude = null;
		Double longitude = null;
		String tsp = new Date().toString();
		Double accuracy = 500.10;
		if (!latlng.equals("")) {
			ss = (latlng.split(";"));
			latitude = Double.parseDouble(ss[0]);
			longitude = Double.parseDouble(ss[1]);
		}
		
		try {
			address = new InetSocketAddress("192.168.201.1", 31337);
			client = SocketChannel.open(address);
			client.configureBlocking(false);
			buffer = ByteBuffer.allocate(128);
			// 写入消息头
			buffer.putInt(999999);
			// 写入手机号
			byte[] phone_bytes = phone.getBytes();
			short phone_len = (short)(phone_bytes.length);
			buffer.putShort(phone_len);
			buffer.put(phone_bytes);
			// 写时间戳
			byte[] time_bytes = tsp.getBytes();
			short time_len = (short)(time_bytes.length);
			buffer.putShort(time_len);
			buffer.put(time_bytes);
			// 写入纬度
			buffer.putDouble(latitude);
			// 写入经度
			buffer.putDouble(longitude);
			// 写入精确值
			buffer.putDouble(accuracy);
			
			buffer.clear();
			// 写入，发送
			client.write(buffer);
			logger("send data: " + new String(buffer.array(), "utf-8"));
			System.out.println("发送数据: "+new String(buffer.array()));
//			buffer.flip();
			
		} catch (IOException e) {
			i = -1;
			logger("Error occured in SocketChannel opening ：" + e.getMessage());
		}finally{
			try {
				if (client == null) {
					return -1;
				}
				if (client.isConnected()) {
					client.close();
				}
			} catch (IOException e) {
				logger("Error occured in close SocketChannel ：" + e.getMessage());
			}
		}
		return i;
	}
	
	private static void logger(Object message){
		FileWriter writer = null;
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//日志文件的路径建议卸载配置文件中
			writer = new FileWriter("d:/splitDataLog.txt", true);
			writer.write(format.format(new Date()) +"  " + message.toString() + "\r\n");
		} catch (Exception e) {
			 System.out.println("Writer error: "+e.getMessage());
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				System.out.println("Close write errors: "+e.getMessage());
			}
		}
	}
}



//
//import COM.ibm.db2.app.UDF;
//
///**
// * <p>Title: DB2映射的用户自定义函数，用于当数据发生变化时触发事件</p>
// * <p>Description: </p>
// * @author zhangzuoqiang
// * <br/>Date: 2011-3-31
// */
//public class ClientUDF extends UDF {
//	
//	/**
//	 * 当用户绑定的phone有新的数据时触发
//	 * @param email 用户邮箱
//	 * @param phone 绑定的手机号
//	 * @param lngAndLat 该手机对应的位置数据-经纬度，也可以是子网号
//	 * @param mncLacCellID 该手机对应的位置数据-子网号
//	 * @param tsp 时间戳String
//	 * @return
//	 */
//	public static synchronized int fireNewPosTip(String email, String phone, String lngAndLat, String mncLacCellID, String tsp){
//		int i = 0;
//		
//		return i;
//	}
//	
//	/**
//	 * 当绑定的手机超出设定的最大偏移距离时触发
//	 * @param email
//	 * @param phone
//	 * @param radius
//	 * @param lng
//	 * @param lat
//	 * @return
//	 */
//	public static synchronized int fireOutRadius(String email, String phone, double radius, double lng, double lat){
//		int i = 0;
//		
//		return i;
//	}
//}
