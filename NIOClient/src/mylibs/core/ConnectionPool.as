package mpcs.mylibs.core
{
	/**
	 * <b>Description: </b>Socket 连接对象池
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public class ConnectionPool {
		
		private static var instance:ConnectionPool;
		private var connPools:Array;
		private var length:int = 0;
		private var num:int = 0;// 新建NetClient的个数
		
		public function ConnectionPool() {
			connPools = [];
		}
		
		public function getConnection():Connection{
			trace("connPools中Connection的个数Num = " + num);
			if(length > 0){
				length --;
				return connPools.pop();
			}
			num++;
			return new Connection();
		}
		
		public function return2Pools(conn:Connection):void{
			length ++;
			connPools.push(conn);
		}
		
		public static function getInstance():ConnectionPool{
			if(!instance)
				instance = new ConnectionPool();
			return instance;
		}
		
	}
}