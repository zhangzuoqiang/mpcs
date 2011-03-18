package mylibs.core
{
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IOErrorEvent;
	import flash.events.ProgressEvent;
	import flash.events.SecurityErrorEvent;
	import flash.net.Socket;
	import flash.utils.ByteArray;
	
	import mylibs.data.SimpleMsg;
	import mylibs.events.ConnectEvent;
	import mylibs.events.GenericEvent;
	import mylibs.interfaces.ICmd;
	import mylibs.interfaces.IConnection;
	
	/**
	 * <b>Description: </b>连接socket服务器类
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public class Connection extends EventDispatcher implements IConnection {
		
		private var socket:Socket;
		public var server:String;
		public var port:int;
		
		private var byteBuffer:ByteArray;	//数据缓存
		private var dataLength:int;             //接收到的数据长度
		private var headLen:int;                  //数据包首部长度
		
		public function Connection() {
			byteBuffer = new ByteArray();
		}
		
		/**
		 *  设置服务器地址和端口
		 * @param server
		 * @param port
		 */		
		private function setServerInfo(server:String = null, port:int = 0):void {
			if(server)
				this.server = server;
			if(port > 0)
				this.port = port;
		}
		
		public function buildConnection(server:String = "172.25.135.248", port:int = 843):void {
			this.setServerInfo(server,port);
			if(!socket) {
				socket = new Socket(server, port);
				trace("socket connecting.....................");
			}
			this.addSocketListeners();
		}
		
		public function reconnect():void {
			if(!socket || socket.connected)
				return;
			socket.connect(this.server, this.port);
			trace("socket reconnect.......................");
		}
		
		public function send(cmd:ICmd):void {
			if(!this.isConnected()) {
				this.dispatchEvent(new Event(ConnectEvent.CONNECT_ERROR));
				trace("Send Error");
				return;
			}
			var ba:ByteArray = toEncodeSocketByteArray(cmd);
			ba.position = 0;
			this.socket.writeBytes(ba,0,ba.length);
			this.socket.flush();
		}
		
		/**
		 *  组成数据准备发送（如果数据加密，可以在此添加加密字段）
		 * @param cmd
		 * @return 
		 */
		protected function toEncodeSocketByteArray(cmd:ICmd):ByteArray{
			var des:ByteArray = new ByteArray();
			var tmp:ByteArray  = cmd.head2ByteArray();
			des.writeBytes(tmp, 0, tmp.length);	// 写入消息头
			tmp.clear();
			// 如果包含消息体
			if(cmd.hasBody()){
				tmp = cmd.getBodyBytes();
				des.writeBytes(tmp,0,tmp.length); // 写入消息体
			}
			return des;
		}
		
		public function close():void {
			if(socket.connected){
				socket.removeEventListener(Event.CLOSE, closeHandler);
				socket.removeEventListener(IOErrorEvent.IO_ERROR, ioErrorHandler);
				socket.removeEventListener(SecurityErrorEvent.SECURITY_ERROR, securityErrorHandler);
				socket.removeEventListener(Event.CONNECT, connectHandler);
				socket.removeEventListener(ProgressEvent.SOCKET_DATA, receivedHandler);
				socket.close();
			}
		}
		
		public function isConnected():Boolean {
			return (socket && socket.connected);
		}
		
		/**
		 *  添加Socket事件监听
		 */		
		private function addSocketListeners():void {
			socket.addEventListener(Event.CLOSE, closeHandler);
			socket.addEventListener(IOErrorEvent.IO_ERROR, ioErrorHandler);
			socket.addEventListener(SecurityErrorEvent.SECURITY_ERROR, securityErrorHandler);
			socket.addEventListener(Event.CONNECT, connectHandler);
			socket.addEventListener(ProgressEvent.SOCKET_DATA, receivedHandler);
		}
		
		private function closeHandler(e:Event):void {
			dispatchEvent(e);
			this.close();
			trace("Connection serverClose");
		}
		
		private function ioErrorHandler(e:IOErrorEvent):void {
			dispatchEvent(e);
			trace("Connection ioError");
		}
		
		private function securityErrorHandler(e:SecurityErrorEvent):void {
			dispatchEvent(e);
			trace("Connection securityError");
		}
		
		private function connectHandler(e:Event):void {
			dispatchEvent(e);
			trace("Connection connected");
		}
		
		private function receivedHandler(e:ProgressEvent):void {
			dispatchEvent(e);
			//开始读取数据的标记
			var readFlag:Boolean = false;
			//每读取一条数据bytesAvailable值将随之减少
			while (socket.bytesAvailable) {
				if (!readFlag && socket.bytesAvailable >= headLen) {
					//读取数据长度
					dataLength = socket.readInt();					
					readFlag = true;
				}
				//如果为0,表示收到异常消息，避免无限循环地等待
				if(dataLength==0){
					dispatchEvent(new ConnectEvent(ConnectEvent.NULL_STREAM));
					return;
				}
				//数据流里的数据满足条件，开始读数据
				if (readFlag && socket.bytesAvailable >= dataLength){
					//指针回归
					byteBuffer.position = 0;
					//取出指定长度的字节
					socket.readBytes(byteBuffer, 0, dataLength);
					
					//读取完一条消息后发送消息内容
					var msg:SimpleMsg = new SimpleMsg();
					msg.parseHeadData(byteBuffer);
					var tid:int = msg.getHeadData(0);
					this.dispatchEvent(new GenericEvent(tid.toString(),msg));
					
					dataLength = 0;
					readFlag = false;
				}
			}
		}
	}
}