package mylibs.events
{
	import flash.events.Event;
	
	import mylibs.data.SimpleMsg;
	import mylibs.utils.MsgFactory;
	
	import mylibs.data.Packet;
	
	/**
	 * <b>Description: </b>由消息号触发的事件
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-17
	 **/
	public class GenericEvent extends Event {
		
		private var packet:Packet; 
		/**
		 *  服务端有数据返回时触发该事件
		 * @param type 消息号
		 * @param packet 返回的数据包
		 */		
		public function GenericEvent(type:String, packet:Packet) {
			super(type);
			this.packet = packet;
		}
		
		public function getSimpleMsg():SimpleMsg {
			return MsgFactory.getMsg(SimpleMsg, packet) as SimpleMsg;
		}
	}
}