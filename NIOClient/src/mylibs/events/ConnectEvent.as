package mylibs.events
{
	/**
	 * <b>Description: </b>
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-17
	 **/
	public class ConnectEvent {
		public function ConnectEvent() {
		}
		
		public static var CONNECTTED:String  = "connected";
		public static var M_CONNECTTED:String  = "mConnected";
		public static var CONNECTION_ERROR:String ="connectError";
		public static var USER_ENTER:String = "userEnter";
	}
}