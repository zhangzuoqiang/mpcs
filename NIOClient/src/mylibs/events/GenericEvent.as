package mylibs.events
{
	import flash.events.Event;
	
	import mylibs.data.BaseMsg;
	import mylibs.data.SimpleMsg;
	import mylibs.interfaces.IMsg;
	
	/**
	 * <b>Description: </b>
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public class GenericEvent extends Event {
		
		private var msg:IMsg;
		
		public function GenericEvent(typeID:String, _msg:IMsg) {
			super(typeID);
			this.msg = _msg;
		}
		
		public function getMsg():IMsg {
			if(this.msg.hasBody())
				return msg as SimpleMsg;
			else
				return msg as BaseMsg;
		}
	}
}