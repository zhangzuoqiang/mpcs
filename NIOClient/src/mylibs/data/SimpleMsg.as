package mylibs.data
{
	import flash.utils.ByteArray;

	/**
	 * <b>Description: </b>
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public class SimpleMsg extends BaseMsg {
		
		// 消息体
		private var bytes:ByteArray;
		
		public function SimpleMsg() {
		}
		
		override public function parseHeadData(byte:ByteArray):void {
			this.head[0] = byte.readInt();
			this.head[1] = byte.readInt();
			this.head[2] = byte.readInt();
			if(byte.bytesAvailable > 0){
				bytes = byte;
			}
		}
		
		override public function hasBody():Boolean {
			return true;
		}
		
		override public function readShort():int{
			return bytes.readShort();
		}
		override public function readUShort():int{
			return bytes.readUnsignedShort();
		}
		override public function readInt():int{
			return  bytes.readInt();
		}
		override public function readUint():uint{
			return bytes.readUnsignedInt();
		}
		override public function readFloat():Number{
			return bytes.readFloat();
		}
		override public function readDouble():Number{
			return bytes.readDouble();
		}
		override public function readByte():int{
			return bytes.readByte();
		}
		override public function readBytes(offset:int=0,length:int=0):ByteArray{
			var _bytes:ByteArray=new ByteArray();
			bytes.readBytes(_bytes, offset, length);
			return _bytes;
		}
		override public function readString(charset:String="UTF-8"):String{
			var str_len:int=readShort();
			return bytes.readMultiByte(str_len, charset);
		}
	}
}