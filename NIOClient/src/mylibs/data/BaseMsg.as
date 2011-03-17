package mylibs.data
{
	import flash.utils.ByteArray;
	
	import mylibs.interfaces.IMsg;
	
	/**
	 * <b>Description: </b>服务端返回的消息类型
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-17
	 **/
	public class BaseMsg extends BaseCmd implements IMsg {
		
		private var body:Packet;
		
		public function BaseMsg() {
		}
		
		public function parsePacket(packet:Packet):Boolean {
			var b:Boolean = false;
			try{
				// 设置消息头
				for(var i:int = 0; i <= 2; i++)
					this.setHeadData(i, packet.readInt());
				body = packet;
				b = true;
			}catch(e:Error){
				trace("Exception: " + e);
				b = false;
			}
			return b;
		}
		
		public function get errorID():int {
			return this.getHeadData(2);
		}
		
		public function getBody():Packet {
			return body;
		}
	}
}