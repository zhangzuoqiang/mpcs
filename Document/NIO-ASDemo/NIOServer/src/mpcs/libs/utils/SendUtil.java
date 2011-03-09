package mpcs.libs.utils;

import mpcs.libs.configs.GlobalConst;
import mpcs.libs.configs.GlobalErrorConst;
import mpcs.libs.data.ByteArrayPacket;

/**
 * <p>Title: 构造发送到客户端的Packet</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-9
 */
public class SendUtil {
	
	private static ByteArrayPacket packet;
	/**
	 * 该邮箱已被注册
	 * @return
	 */
	public static ByteArrayPacket emailIsUsed(){
		packet = new ByteArrayPacket(1);
		packet.writeInt(9);
		packet.writeInt(GlobalErrorConst.E_USER_REGISTER);
		packet.writeInt(0);
		return packet;
	}
	
	/**
	 * 注册用户成功
	 * @return
	 */
	public static ByteArrayPacket regSuccess(){
		packet = new ByteArrayPacket(1);
		packet.writeInt(0);
		packet.writeInt(GlobalConst.S_USER_REGISTER);
		packet.writeInt(0);
		return packet;
	}
	
	/**
	 * 执行数据库操作，添加用户时失败
	 * @return
	 */
	public static ByteArrayPacket regFailed(){
		packet = new ByteArrayPacket(1);
		packet.writeInt(9);
		packet.writeInt(GlobalErrorConst.E_ADD_USER_FAILED);
		packet.writeInt(0);
		return packet;
	}
}
