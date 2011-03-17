package mylibs.data
{
	import flash.utils.ByteArray;
	
	import mylibs.interfaces.IMsg;
	import mylibs.interfaces.ParseAdapter;
	
	/**
	 * <b>Description: </b>
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public class BaseMsg extends ParseAdapter implements IMsg {
		
		/**
		 * 消息头数组 int
		 * <br/> head[0] 消息号
		 * <br/> head[1] 全局错误号0表示无错误，9表示有错误
		 * <br/> head[2] 如果head[1]=9，则此位表示具体的错误号，否则为0
		 */
		protected var head:Array = [0,0,0];
		
		public function BaseMsg() {
		}
		
		public function parseHeadData(byte:ByteArray):void {
			this.head[0] = byte.readInt();
			this.head[1] = byte.readInt();
			this.head[2] = byte.readInt();
			byte.clear();
		}
		
		public function getTypeID():int {
			return head[0];
		}
		
		public function getHeadData(i:int):int {
			if(i < 0 || i > 2)
				return -1;
			return head[i];
		}
		
		public function hasBody():Boolean {
			return false;
		}
		
		public function hasError():Boolean {
			if(head[1] == 0){
				return false;
			}else{
				return true;
			}
		}
		
		public function getErrorID():int {
			if(head[1] == 9)
				return head[2];
			else
				return 0;
		}
	}
}