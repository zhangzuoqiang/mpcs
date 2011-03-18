package mylibs.core
{
	import mylibs.interfaces.IConnection;

	/**
	 * <b>Description: </b>Connection管理类
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-18
	 **/
	public class ConnectionManager {
		
		private static var manager:ConnectionManager;
		private static var conn:Connection = null;
		
		public function ConnectionManager() {
			manager = this;
		}
		
		/**
		 *  获取socket连接
		 * @return 
		 */		
		public static function getConnection():IConnection{
			if(conn == null){
				conn = new Connection();
				conn.buildConnection();
			}
			return conn;
		}
		
		public static function getManager():ConnectionManager {
			if(manager == null)
				return new ConnectionManager();
			return manager;
		}
	}
}