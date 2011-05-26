package mpcs.mylibs.data
{
	import flash.utils.ByteArray;

	/**
	 * <b>Description: </b>
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public class SimpleMsg extends BaseMsg {
		
		// 消息体
		private var body:Packet;
		
		public function SimpleMsg() {
		}
		
		override public function parseHeadData(byte:ByteArray):void {
			this.head[0] = byte.readInt();
			this.head[1] = byte.readInt();
			this.head[2] = byte.readInt();
			if(byte.bytesAvailable > 0){
				body = new Packet(byte);
			}
		}
		
		override public function hasBody():Boolean {
			return true;
		}
		
		override public function readShort():int{
			return body.readShort();
		}
		override public function readUShort():int{
			return body.readUShort();
		}
		override public function readInt():int{
			return  body.readInt();
		}
		override public function readUint():uint{
			return body.readUint();
		}
		override public function readFloat():Number{
			return body.readFloat();
		}
		override public function readDouble():Number{
			return body.readDouble();
		}
		override public function readByte():int{
			return body.readByte();
		}
		override public function readBytes(offset:int=0,length:int=0):ByteArray{
			return body.readBytes(offset, length);
		}
		override public function readString(charset:String="UTF-8"):String{
			return body.readString(charset);
		}
	}
}