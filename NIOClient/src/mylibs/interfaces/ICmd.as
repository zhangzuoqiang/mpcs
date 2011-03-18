package mylibs.interfaces
{
	import flash.utils.ByteArray;

	/**
	 * <b>Interface</b>: 发送请求到服务端的接口
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public interface ICmd {
		
		/**
		 *  设置消息号，即第1位消息头
		 * @param typeID
		 */		
		function setTypeID(typeID:int):void;
		
		/**
		 *  设置消息头（2,3位如果不设置默认为0）
		 * @param i
		 * @param value
		 */		
		function setHeadData(i:int, value:int):void;
		
		/**
		 *  是否包含消息体
		 * @return 
		 */		
		function hasBody():Boolean;
		
		/**
		 *  将消息头写入ByteArray
		 * @return 
		 */
		function head2ByteArray():ByteArray;
		
		/**
		 *  获取消息体ByteArray
		 * @return 
		 */		
		function getBodyBytes():ByteArray;
	}
}