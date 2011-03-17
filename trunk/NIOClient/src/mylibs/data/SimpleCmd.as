package mylibs.data
{

	/**
	 * <b>Description: </b>发送到服务端的消息类型（包含消息体）
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-17
	 **/
	public class SimpleCmd extends BaseCmd {
		
		private var body:Packet;
		
		public function SimpleCmd(TYPEID:int=0) {
			super(TYPEID);
			body = createBodySegment();
		}
		
		public function writeShort(value:int):void{
			body.writeShort(value);
		}
		public function writeUShort(value:uint):void{
			body.writeShort(value);
		}
		public function writeInt(value:int):void{
			body.writeInt(value);
		}
		public function writeUint(value:uint):void{
			body.writeUint(value);
		}
		public function writeFloat(value:Number):void{
			body.writeFloat(value);
		}
		public function writeDouble(value:Number):void{
			body.writeDouble(value);
		}
		public function writeByte(value:int):void{
			body.writeByte(value);
		}
		public function writeBytes(bytes:ByteArray,offset:int=0,length:int=0):void{
			body.writeBytes(bytes, offset, length);
		}
		public function writeString(value:String,charset:String="UTF-8"):void{
			body.writeString(value, charset);
		}
	}
}