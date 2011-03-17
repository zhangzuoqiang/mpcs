package mylibs.interfaces
{
	/**
	 * <b>Interface</b>: 发送给服务端 消息基接口
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-17
	 **/
	public interface ICmd extends ISerial {
		
		/**
		 * 获取消息号
		 * @return 
		 */		
		function get TypeId():int;
		
		/**
		 *  设置消息号
		 * @param id
		 */		
		function set TypeId(id:int):void
			
		/**
		 *  得到指定位的消息头
		 * @param i
		 * @return 
		 */			
		function getHeadData(i:int):int;
	}
}