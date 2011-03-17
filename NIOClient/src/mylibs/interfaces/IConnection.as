package mylibs.interfaces
{
	import flash.events.IEventDispatcher;
	
	/**
	 * <b>Interface</b>: 连接服务器接口
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-17
	 **/
	public interface IConnection extends IEventDispatcher {
		/**
		 *  指定服务器地址和端口，建立连接
		 * @param server
		 * @param port
		 */		
		function buildConnection(server:String=null,port:int=0):void;
		
		/**
		 *  重连
		 */		
		function reconnect():void;
		
		/**
		 *  发送请求到服务端
		 * @param ism
		 */		
		function send(ism:ISerial):void;
		
		/**
		 *  关闭连接
		 */		
		function close():void;
		
		/**
		 *  判断是否连接状态
		 * @return 
		 */		
		function isConnected():Boolean;
	}
}