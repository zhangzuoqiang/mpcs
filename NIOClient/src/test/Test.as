package test
{
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.text.TextField;
	import flash.text.TextFieldAutoSize;

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
		public function requestConnectServer(head1:int, head2:int=0, head3:int=0, body1:String="", body2:String=""):void {
			var packet:Packet = new Packet();
			packet.writeInt(head1);// 写消息
			packet.writeInt(head2);
			packet.writeInt(head3);
			if(body1 !=""){
				packet.writeString(body1);
			}
			if(body2 !=""){
				packet.writeString(body1);
			}
			
			net = NetPool.getInstance().getNetClient();
			net.buildConnection();
			net.addEventListener(NetEvent.READED_DATA, onReadedDataHandler);
			net.addEventListener(Event.CONNECT, onConnectServerHandler);
			
			net.sendPacket(packet);
		}
		
		private function onConnectServerHandler(e:Event):void {
			trace("连接成功");
		}
		
		private function onReadedDataHandler(e:NetEvent):void {
			var packet:Packet=new Packet(e.bytesData);
			
			var h1:int = packet.readInt();
			var h2:int = packet.readInt();
			var h3:int = packet.readInt();
			if(packet.array().bytesAvailable){
				var body:String = packet.readString();
			}else{
				body = "";
			}
			setServerData(h1, h2, h3, body);
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