package test
{
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.text.TextField;
	import flash.text.TextFieldAutoSize;
	
	import mylibs.data.Packet;
	import mylibs.data.SimpleCmd;
	import mylibs.data.SimpleMsg;
	import mylibs.events.GenericEvent;
	
	
	[SWF(width=480,height=550)]
	/**
	 * <b>Description: </b>用来测试与服务端的交互
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-10
	 **/
	public class Test extends Sprite {
		
		private static var instance:Test;
		public var client:ClientPanel;
		private var line:Sprite;
		public var server:ServerPanel;
		public var net:NetClient;
		private var btnGroup:TestButtonGroup;
		
		public function Test() {
			instance = this;
			stage.scaleMode = "noScale";
			// 初始化界面组件
			initUI();
		}
		
		private function initUI():void{
			client = new ClientPanel();
			client.x = 0;
			client.y = 5;
			
			line = new Sprite();
			line.graphics.beginFill(0x0099FF);
			line.graphics.drawRect(0, client.height + 20, 480, 2);
			
			server = new ServerPanel();
			server.x = 0;
			server.y = 200;
			
			btnGroup = new TestButtonGroup();
			btnGroup.x = 380;
			btnGroup.y = 10;
			
			addChild(client);
			addChild(line);
			addChild(server);
			addChild(btnGroup);
		}
		
		/**
		 * 构造请求信息
		 * @param head1
		 * @param head2
		 * @param head3
		 * @param body1
		 * @param body2
		 */
		public function requestConnectServer(head1:int, head2:int=0, head3:int=0, body1:String="", body2:String="", 
											 body3:String="", body4:String="", body5:String="", body6:String="", body7:String="", 
											 body8:String="", body9:String="", body10:String="", body11:String=""):void {
			var packet:Packet = new Packet();
			packet.writeInt(head1);// 写消息
			packet.writeInt(head2);
			packet.writeInt(head3);
			packet.writeString(body1);
			packet.writeString(body2);
			packet.writeString(body3);
			packet.writeString(body4);
			packet.writeString(body5);
			packet.writeString(body6);
			packet.writeString(body7);
			packet.writeString(body8);
			packet.writeString(body9);
			packet.writeString(body10);
			packet.writeString(body11);
			
			net = NetPool.getInstance().getNetClient();
			net.buildConnection();
			net.addEventListener(Event.CONNECT, onConnectServerHandler);
			net.addEventListener("500000", timeHandler);
			
			net.sendPacket(packet);
		}
		
		private function timeHandler(evt:GenericEvent):void {
			var msg:SimpleMsg = evt.getMsg() as SimpleMsg;
			var str:String = msg.readString();
			if (msg.getHeadData(1) == 0){
				trace(str);
			}
			setServerData(msg.getHeadData(0), msg.getHeadData(1), msg.getHeadData(2), str);
		}
		
		private function onConnectServerHandler(e:Event):void {
			trace("连接成功");
		}
		
		private function onReadedDataHandler(e:NetEvent):void {
			var packet:Packet=new Packet(e.bytesData);
			
			var h1:int = packet.readInt();
			var h2:int = packet.readInt();
			var h3:int = packet.readInt();
			
//			var len:int = packet.readInt();// 获取绑定手机的数目
			
			setServerData(h1, h2, h3, testPacket(packet));
		}
		
		private function testPacket(packet:Packet):String{
			if(packet.array().bytesAvailable){
				var body:String = packet.readString();
			}else{
				body = "";
			}
			return body;
		}
		
		/**
		 *  设置客户端返回框数据
		 * @param head1
		 * @param head2
		 * @param head3
		 * @param body
		 */		
		private function setServerData(head1:int, head2:int, head3:int, body:String):void{
			server.setData(head1, head2, head3, body);
		}
		
		/**获取Test单例**/
		public static function getInstance():Test {
			return instance;
		}
	}
}