package mylibs.interfaces
{
	import flash.utils.ByteArray;
	
	import mylibs.data.Packet;

	/**
	 * <b>Interface</b>: 服务器返回消息 接口
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-17
	 **/
	public interface IMsg extends ICmd {
		
		/**
		 *  解析获得的服务器消息
		 * <br/>将消息头和消息体分离
		 * @param Packet
		 * @return 
		 */		
		function parsePacket(packet:Packet):Boolean;
		
		/**
		 *  获取服务器返回的错误消息号
		 * @return 
		 */		
		function get errorID():int;
	}
}