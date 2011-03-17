package mylibs.interfaces
{
	import flash.utils.ByteArray;

	/**
	 * <b>Interface</b>: 接受服务端返回的数据接口
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public interface IMsg {
		
		/**
		 *  填充消息头
		 */		
		function parseHeadData(byte:ByteArray):void;
		/**
		 *  获取消息号
		 * @return 
		 */		
		function getTypeID():int;
		
		/**
		 *  获取指定位的消息头
		 * @param i
		 * @return 
		 */		
		function getHeadData(i:int):int;
		
		/**
		 *  是否包含消息体
		 * @return 
		 */		
		function hasBody():Boolean;
		
		/**
		 *  返回是否包含错误信息
		 * @return 
		 */		
		function hasError():Boolean;
		
		/**
		 *  获取错误号
		 * @return 
		 */		
		function getErrorID():int;
	}
}