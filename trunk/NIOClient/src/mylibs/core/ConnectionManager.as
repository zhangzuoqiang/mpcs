package mylibs.core
{
	import flash.utils.Dictionary;
	
	import mylibs.interfaces.IConnection;
	import mylibs.interfaces.IConnectionManager;

	/**
	 * <b>Description: </b>Connection管理类
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-17
	 **/
	public class ConnectionManager {
		
		private static var d:Dictionary = new Dictionary();
		private static var manager:IConnectionManager;
		
		public function ConnectionManager() {
		}
		
		public static function getManager():IConnectionManager {
			return manager;
		}
		
		public static function getConnection(id:String="default"):IConnection {
			var con:IConnection = d[id];
			if(con == null)
				d[id] = con = manager.getConnection();
			return con; 
		}
	}
}