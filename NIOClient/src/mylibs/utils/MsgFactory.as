package mylibs.utils
{
	import mylibs.data.BaseMsg;
	
	import mylibs.data.Packet;

	/**
	 * <b>Description: </b>服务端返回消息工厂类
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-17
	 **/
	public class MsgFactory {
		
		public function MsgFactory() {
		}
		
		public static function getMsg(cls:Class, packet:Packet):BaseMsg {
			var baseMsg:BaseMsg = new cls() as BaseMsg;
			if(baseMsg.parsePacket(packet)){
				return baseMsg;
			}
			return null;
		}
	}
}