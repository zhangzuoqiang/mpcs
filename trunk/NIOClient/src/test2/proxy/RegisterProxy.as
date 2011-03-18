package test2.proxy
{
	import mylibs.data.BaseCmd;
	import mylibs.data.SimpleCmd;
	import mylibs.data.SimpleMsg;
	import mylibs.events.GenericEvent;
	import mylibs.interfaces.IConnection;
	
	import test2.Test2;

	/**
	 * <b>Description: </b>测试用户注册类
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public class RegisterProxy {
		
		private var con:IConnection = Test2.getInstance().conn;
		
		public function RegisterProxy() {
			con.addEventListener("500101", rRegisterHandler);
		}
		
		public function sRegister():void {
			var cmd:SimpleCmd = new SimpleCmd(100101);
			cmd.writeString("nioclient@gmail.com");
			cmd.writeString("123");
			con.send(cmd);
		}
		
		private function rRegisterHandler(evt:GenericEvent):void{
			var msg:SimpleMsg = evt.getMsg() as SimpleMsg;
			trace(msg.readString());
			con.removeEventListener("500101", rRegisterHandler);
		}
		
	}
}