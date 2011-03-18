package test2.proxy
{
	import mylibs.data.BaseCmd;
	import mylibs.data.SimpleMsg;
	import mylibs.events.GenericEvent;
	import mylibs.interfaces.IConnection;
	
	import test2.Test2;

	/**
	 * <b>Description: </b>测试请求服务端时间
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public class TimeProxy {
		
		private var con:IConnection = Test2.getInstance().conn;
		
		public function TimeProxy() {
			con.addEventListener("500000", rTimeHandler);
		}
		
		public function sServerTime():void {
			var cmd:BaseCmd = new BaseCmd(100000);
			con.send(cmd);
		}
		
		private function rTimeHandler(evt:GenericEvent):void{
			var msg:SimpleMsg = evt.getMsg() as SimpleMsg;
			trace(msg.readString());
			con.removeEventListener("500000", rTimeHandler);
		}
		
	}
}