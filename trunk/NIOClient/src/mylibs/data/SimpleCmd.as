package mylibs.data
{
	import flash.utils.ByteArray;

	/**
	 * <b>Description: </b>客户端请求数据（有消息体）
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public class SimpleCmd extends BaseCmd {
		
		// 消息体
		private var bytes:ByteArray;
		
		public function SimpleCmd(TYPEID:int) {
			super(TYPEID);
			this.bytes = new ByteArray();
		}
		
		override public function hasBody():Boolean {
			return true;
		}
		
		override public function writeShort(value:int):void{
			bytes.writeShort(value);
		}
		override public function writeUShort(value:uint):void{
			bytes.writeShort(value);
		}
		override public function writeInt(value:int):void{
			bytes.writeInt(value);
		}
		override public function writeUint(value:uint):void{
			bytes.writeUnsignedInt(value);
		}
		override public function writeFloat(value:Number):void{
			bytes.writeFloat(value);
		}
		override public function writeDouble(value:Number):void{
			bytes.writeDouble(value);
		}
		override public function writeByte(value:int):void{
			bytes.writeByte(value);
		}
		override public function writeBytes(bytes:ByteArray, offset:int = 0, length:int = 0):void{
			bytes.writeBytes(bytes, offset, length);
		}
		override public function writeString(value:String,charset:String = "UTF-8"):void{
			var ba:ByteArray = new ByteArray();
			ba.writeMultiByte(value, charset);
			var len:int = ba.length;
			writeShort(len);
			bytes.writeBytes(ba);
		}
	}
}