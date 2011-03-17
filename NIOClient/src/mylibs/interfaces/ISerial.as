package mylibs.interfaces
{
	import flash.utils.ByteArray;
	
	import mylibs.data.Packet;

	/**
	 * <b>Interface</b>: cmd和msg的基接口
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-17
	 **/
	public interface ISerial {
		
		/**
		 *  将消息头写入ByteArray
		 * @return 
		 */		
		function head2ByteArray():ByteArray;
		
		/**
		 *  得到消息体数组
		 * @return 
		 */		
		function getCmdBodys():Packet;
	}
}