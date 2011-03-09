package  
{
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.IOErrorEvent;
	import flash.events.MouseEvent;
	import flash.events.ProgressEvent;
	import flash.events.SecurityErrorEvent;
	import flash.text.TextField;
	import flash.text.TextFieldType;
	
	[SWF(width=400,height=500)]
	public class Main extends Sprite
	{
		private static var instance:Main;
		private var registPanel:RegistPanel;
		private var client:NetClient;
		private var tf:TextField;
		private var btn:Button;
		
		public function Main() 
		{
			instance = this;
			stage.scaleMode = "noScale";
			
			client = new NetClient();
			client.buildConnection();
			client.addEventListener(NetEvent.READED_DATA, onReadedDataHandler);
			client.addEventListener(Event.CONNECT, onConnectServerHandler);
			
			registPanel = new RegistPanel();
			addChild(registPanel);
			registPanel.addEventListener(NetEvent.NET_CONNECT, requestConnectServer);
			
			tf = new TextField();
			tf.border = true;
			tf.width = 300;
			tf.height = 400;
			tf.x=50;
			tf.y = 50;
			tf.type = TextFieldType.DYNAMIC;
			addChild(tf);
			
			btn = new Button(40, 20, "清空");
			btn.x = 310;
			btn.y = 430;
			addChild(btn);
			btn.addEventListener(MouseEvent.CLICK, onClickHandler);
		}
		
		private function onClickHandler(e:MouseEvent):void {
			tf.text = "";
		}
		
		private function requestConnectServer(e:NetEvent):void {
//			var message:Packet = new Packet();
//			message.writeInt(e.playerID);
//			message.writeInt(0);
//			message.writeInt(0);
//			message.writeString("csdn_eric@gmail.com");
//			message.writeString("123");	
//			trace("客户端输出： " + e.playerID);
//			client.sendPacket(message);
//			trace(message.size());
			var p:Packet = new Packet();
			p.writeInt(111111);
			p.writeInt(e.playerID);
			trace(e.playerID);
			client.sendPacket(p);
		}
		
		private function onConnectServerHandler(e:Event):void {
			tf.appendText("连接成功\n");
		}
		
		private function onReadedDataHandler(e:NetEvent):void {
			var p:Packet=new Packet(e.bytesData);
			var info:String = "服务器返回的数据： " + p.readInt() +"\n";
			tf.appendText(info);
		}
		
		public static function getInstance():Main
		{
			return instance;
		}
		
		public function Client():NetClient
		{
			return this.client;
		}
	}
}