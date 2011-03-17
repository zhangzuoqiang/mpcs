package mylibs.data
{
	import flash.utils.ByteArray;
	
	import mylibs.interfaces.ICmd;
	import mylibs.utils.MsgUtil;
	
	/**
	 * <b>Description: </b>发送给服务端的基本消息类型(不含消息体)
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-17
	 **/
	public class BaseCmd implements ICmd {
		
		/**
		 * 消息头数组 int
		 * <br/> head[0] 消息号
		 * <br/> head[1] 消息头扩展位
		 * <br/> head[2] 消息头扩展位
		 */
		public var head:Array = [0,0,0];
		public var bodys:Packet = null;
		
		/**
		 *  指定消息号的构造器
		 * @param TYPEID 消息号（即head[0]）
		 */		
		public function BaseCmd(TYPEID:int=0) {
			this.TypeId = TYPEID;
		}
		
		public function get TypeId():int {
			return head[0];	
		}
		
		public function set TypeId(id:int):void {
			head[0] = id;
		}
		
		public function getHeadData(i:int):int {
			if(i < 0 || i > 2)
				return -1;
			return head[i];
		}
		
		/**
		 *  设置消息头，需要扩展消息头时使用
		 * @param i
		 * @param value
		 */		
		public function setHeadData(i:int,value:int):void {
			if(i >= 0 && i <= 2)
				head[i] = value;
		}
		
		public function head2ByteArray():ByteArray {
			var ba:ByteArray = MsgUtil.creatByteArray();
			for(var i:int = 0; i < 3; i++)
				ba.writeInt(this.getHeadData(i));
			trace("Send Head: " + ba );
			return ba;
		}
		
		public function getCmdBodys():Packet {
			return bodys;
		}
		
		protected function resetBody():void {
			bodys.clear();
		}
		
		protected function  createBodySegment():Packet {
			var tmp:Packet = new Packet();
			bodys = tmp;
			return tmp;
		}
	}
}