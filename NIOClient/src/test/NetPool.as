package test
{
	/**
	 * <b>Description: </b>NetClient对象池
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-13
	 **/
	public class NetPool {
		
		private static var instance:NetPool;
		private var netPools:Array;
		private var length:int = 0;
		
		public function getNetClient():NetClient{
			if(length>0){
				length --;
				return netPools.pop();
			}
			return new NetClient();
		}
		
		public function returnNetClient(net:NetClient):void{
			length ++;
			netPools[length] = net;
		}
		
		public function NetPool(){
			netPools = [];
		}
		
		public static function getInstance():NetPool{
			if(!instance)
				instance = new NetPool();
			return instance;
		}
	}
}