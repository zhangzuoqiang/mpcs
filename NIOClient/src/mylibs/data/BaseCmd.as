package mylibs.data
{
	import flash.utils.ByteArray;
	
	import mylibs.interfaces.ICmd;
	import mylibs.interfaces.ParseAdapter;
	
	/**
	 * <b>Description: </b>客户端请求数据（无消息体）
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public class BaseCmd extends ParseAdapter implements ICmd {
		
		/**
		 * 消息头数组 int
		 * <br/> head[0] 消息号
		 * <br/> head[1] 消息头扩展位
		 * <br/> head[2] 消息头扩展位
		 */
		protected var head:Array = [0,0,0];
		
		public function BaseCmd(TYPEID:int){
			this.setTypeID(TYPEID);
		}
		
		public function head2ByteArray():ByteArray{
			var ba:ByteArray = new ByteArray();
			for(var i:int = 0; i <= 2; i++)
				ba.writeInt(this.head[i]);
//			trace("Send Head: " + ba.toString() );
			return ba;
		}
		
		public function setTypeID(typeID:int):void {
			this.head[0] = typeID;
		}
		
		public function setHeadData(i:int, value:int):void {
			if(i >= 0 && i <= 2)
				head[i] = value;
		}
		
		public function hasBody():Boolean {
			return false;
		}
		
		public function getBodyBytes():ByteArray{
			return null;
		}
	}
}