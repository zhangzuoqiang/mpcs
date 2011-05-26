package mpcs.mylibs.events
{
	import flash.events.Event;
	import flash.utils.ByteArray;
	
	/**
	 * <b>Description: </b>连接socket自定义事件
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public class ConnectEvent extends Event {
		
		public static var CONNECTED:String  = "connected";
		public static var CONNECTING:String  = "connecting";
		public static var CONNECT_ERROR:String ="connectError";
		public static const SERVER_CLOSE:String = "serverClose";
		public static const IO_ERROR:String = "ioError";
		public static const SECURITY_ERROR:String = "securityError";
		public static const SOCKET_DATA:String = "socketData";
		public static const READED_DATA:String = "readedData";
		public static const NULL_STREAM:String = "nullStream";
		public static var USER_ENTER:String = "userEnter";
		
		private var _data:ByteArray;
		
		public function ConnectEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false) {
			super(type, bubbles, cancelable);
		}
		
		public function set bytesData(data:ByteArray):void{
			_data = data;
		}
		
		public function get bytesData():ByteArray{
			return _data;
		}
	}
}