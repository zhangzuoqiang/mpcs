package mpcs.mylibs.interfaces
{
	import flash.utils.ByteArray;

	/**
	 * <b>Interface</b>: 数据读写接口
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public interface IParse {
		
		// 写数据
		function writeShort(value:int):void;
		function writeUShort(value:uint):void;
		function writeInt(value:int):void;
		function writeUint(value:uint):void;
		function writeFloat(value:Number):void;
		function writeDouble(value:Number):void;
		function writeByte(value:int):void;
		function writeBytes(bytes:ByteArray,offset:int=0,length:int=0):void;
		function writeString(value:String,charset:String="UTF-8"):void;
		
		// 读数据
		function readShort():int;
		function readUShort():int;
		function readInt():int;
		function readUint():uint;
		function readFloat():Number;
		function readDouble():Number;
		function readByte():int;
		function readBytes(offset:int=0,length:int=0):ByteArray;
		function readString(charset:String="UTF-8"):String;
	}
}