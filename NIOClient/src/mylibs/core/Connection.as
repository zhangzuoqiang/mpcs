package mylibs.core
{
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	import flash.events.IOErrorEvent;
	import flash.events.ProgressEvent;
	import flash.events.SecurityErrorEvent;
	import flash.events.TimerEvent;
	import flash.net.Socket;
	import flash.system.Security;
	import flash.utils.ByteArray;
	import flash.utils.Timer;
	
	import mylibs.data.BaseCmd;
	import mylibs.data.Packet;
	import mylibs.events.ConnectEvent;
	import mylibs.events.GenericEvent;
	import mylibs.interfaces.IConnection;
	import mylibs.interfaces.ISerial;
	import mylibs.utils.MsgUtil;
	
	/**
	 * <b>Description: </b>连接类
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-17
	 **/
	public class Connection extends EventDispatcher implements IConnection {
		
		public var server:String;
		public var port:int;
		
		private var socket:Socket = null;
		
		public function Connection() {
		}
		
		public function buildConnection(server:String=null, port:int=0):void {
			this.setServerInfo(server,port);
			if(!socket) {
				socket = new Socket();
			}
			this.addSocketListeners();
			
			var policyFile:String="xmlsocket://"+this.server+":"+this.port;
			//Security.loadPolicyFile(policyFile);
			Security.loadPolicyFile("http://"+server+"/crossdomain.xml");
			Security.allowDomain( "*" );
			Security.allowInsecureDomain("*");
			
			socket.connect(this.server, this.port);
			trace("socket connecting.....................");
		}
		
		/**
		 *  设置服务器地址和端口
		 * @param server
		 * @param port
		 */		
		private function setServerInfo(server:String=null,port:int=0):void {
			if(server)
				this.server = server;
			if(port > 0)
				this.port = port;
		}
		
		public function reconnect():void {
			if(!socket || socket.connected)
				return;
			socket.connect(this.server,this.port);
			trace("socket reconnect.......................");
		}
		
		public function send(ism:ISerial):void {
			if(!this.isConnected()) {
				this.dispatchEvent(new Event(ConnectEvent.CONNECTION_ERROR));
				trace("Send Error");
				return;
			}
			
			var ba:ByteArray = toEncodeSocketByteArray(ism);
			ba.position = 0;
			this.socket.writeBytes(ba,0,ba.length);
			this.socket.flush();
		}
		
		/**
		 *  组成数据准备发送（如果数据加密，可以在此添加加密字段）
		 * @param ism
		 * @return 
		 */		
		protected function toEncodeSocketByteArray(ism:ISerial):ByteArray{
			var des:ByteArray = MsgUtil.creatByteArray();
			var tmp:ByteArray  = ism.head2ByteArray();
			des.writeBytes(tmp, 0, tmp.length);	// 写入消息头
			var bodys:Packet = ism.getCmdBodys();// 写入消息体
			tmp = bodys.array();
			des.writeBytes(tmp,0,tmp.length);
			return des;
		}
		
		public function close():void {
			if(socket.connected){
				socket.removeEventListener(IOErrorEvent.IO_ERROR,Net_Error);
				socket.removeEventListener(Event.CLOSE, Net_Error);
				socket.removeEventListener(Event.CONNECT,Net_Connect);
				socket.removeEventListener(SecurityErrorEvent.SECURITY_ERROR,Net_Error);
				socket.removeEventListener(ProgressEvent.SOCKET_DATA,Net_Data);
				socket.close();
			}
		}
		
		public function isConnected():Boolean {
			return (socket && socket.connected);
		}
		
		/**
		 *  添加Ssocket事件监听
		 */		
		private function addSocketListeners():void {
			socket.addEventListener(IOErrorEvent.IO_ERROR,Net_Error);
			socket.addEventListener(Event.CLOSE, Net_Error);
			socket.addEventListener(Event.CONNECT,Net_Connect);
			socket.addEventListener(SecurityErrorEvent.SECURITY_ERROR,Net_Error);
			socket.addEventListener(ProgressEvent.SOCKET_DATA,Net_Data);
		}
		
		protected function Net_Error(evt:Event):void {
			this.dispatchEvent(new Event(ConnectEvent.CONNECTION_ERROR));
			trace("Connection Error");
		}
		
		private function Net_Connect(evt:Event):void {
			this.dispatchEvent(new Event(ConnectEvent.CONNECTTED));
			trace("Connectioned!!!!!!!!!!!!!!");
		}
		
		private function Net_Data(evt:ProgressEvent):void {
			var ba:ByteArray = MsgUtil.creatByteArray();
			socket.readBytes(ba,0,evt.bytesTotal);// 获取服务端返回的数据
			// 根据消息号分发业务逻辑
			var packet:Packet = new Packet(ba);
			var tid:int = packet.readInt();// 读取消息号
			this.dispatchEvent(new GenericEvent(tid.toString(), packet));
		}
	}
}