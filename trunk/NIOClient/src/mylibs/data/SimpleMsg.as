package mylibs.data
{
	import flash.utils.ByteArray;

	/**
	 * <b>Description: </b>
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-17
	 **/
	public class SimpleMsg extends BaseMsg {
		
		private var body:Packet;
		
		public function SimpleMsg() {
			super();
		}
		
		public function readShort():int{
			return body.readShort();
		}
		public function readUShort():int{
			return body.readUShort();
		}
		public function readInt():int{
			return  body.readInt();
		}
		public function readUint():uint{
			return body.readUint();
		}
		public function readFloat():Number{
			return body.readFloat();
		}
		public function readDouble():Number{
			return body.readDouble();
		}
		public function readByte():int{
			return body.readByte();
		}
		public function readBytes(offset:int=0, length:int=0):ByteArray {
			return body.readBytes(offset, length);
		}
		public function readString(charset:String="UTF-8"):String{
			return body.readString(charset);
		}
	}
}