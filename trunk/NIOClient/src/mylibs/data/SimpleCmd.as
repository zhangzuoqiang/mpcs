package mpcs.mylibs.data
{
	import flash.utils.ByteArray;

	/**
	 * <b>Description: </b>客户端请求数据（有消息体）
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public class SimpleCmd extends BaseCmd {
		
		// 消息体
		private var body:Packet;
		
		public function SimpleCmd(TYPEID:int) {
			super(TYPEID);
			this.body = new Packet();
		}
		
		override public function getBodyBytes():ByteArray{
			return this.body.array();
		}
		
		override public function hasBody():Boolean {
			return true;
		}
		
		override public function writeShort(value:int):void{
			body.writeShort(value);
		}
		override public function writeUShort(value:uint):void{
			body.writeShort(value);
		}
		override public function writeInt(value:int):void{
			body.writeInt(value);
		}
		override public function writeUint(value:uint):void{
			body.writeUint(value);
		}
		override public function writeFloat(value:Number):void{
			body.writeFloat(value);
		}
		override public function writeDouble(value:Number):void{
			body.writeDouble(value);
		}
		override public function writeByte(value:int):void{
			body.writeByte(value);
		}
		override public function writeBytes(bytes:ByteArray, offset:int = 0, length:int = 0):void{
			body.writeBytes(bytes, offset, length);
		}
		override public function writeString(value:String,charset:String = "UTF-8"):void{
			body.writeString(value, charset);
		}
	}
}