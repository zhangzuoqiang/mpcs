package mylibs.interfaces
{
	import flash.utils.ByteArray;

	/**
	 * <b>Description: </b>数据读写接口的适配器
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public class ParseAdapter implements IParse {
		
		public function ParseAdapter() {
		}
		
		public function writeShort(value:int):void {
		}
		
		public function writeUShort(value:uint):void {
		}
		
		public function writeInt(value:int):void {
		}
		
		public function writeUint(value:uint):void {
		}
		
		public function writeFloat(value:Number):void {
		}
		
		public function writeDouble(value:Number):void {
		}
		
		public function writeByte(value:int):void {
		}
		
		public function writeBytes(bytes:ByteArray, offset:int=0, length:int=0):void {
		}
		
		public function writeString(value:String, charset:String="UTF-8"):void {
		}
		
		public function readShort():int {
			return 0;
		}
		
		public function readUShort():int {
			return 0;
		}
		
		public function readInt():int {
			return 0;
		}
		
		public function readUint():uint {
			return 0;
		}
		
		public function readFloat():Number {
			return 0;
		}
		
		public function readDouble():Number {
			return 0;
		}
		
		public function readByte():int {
			return 0;
		}
		
		public function readBytes(offset:int=0, length:int=0):ByteArray {
			return null;
		}
		
		public function readString(charset:String="UTF-8"):String {
			return null;
		}
	}
}