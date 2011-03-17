package mylibs.utils
{
	import flash.utils.ByteArray;
	import flash.utils.Endian;

	/**
	 * <b>Description: </b>消息接收/发送工具类
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-17
	 **/
	public class MsgUtil {
		
		public function MsgUtil() {
		}
		
		/**
		 *  获取消息号
		 * @param ba
		 * @return 
		 */		
		public static function getTypeId(ba:ByteArray):int {
			ba.position = 0;
			return ba.readInt();	
		}
		
		/**
		 *  指定ByteArray和消息头位数 返回该位的消息头
		 * @param ba
		 * @param intindex
		 * @return 
		 */		
		public static function getHeadData(ba:ByteArray,intindex:int):int {
			ba.position = intindex * 4;
			return ba.readInt();
		}
		
		/**
		 *  创建一个ByteArray
		 * @return 
		 */		
		public static function creatByteArray():ByteArray {
			var ba:ByteArray = new ByteArray();
			ba.endian = Endian.LITTLE_ENDIAN;
			return ba;	
		}
		
	}
}