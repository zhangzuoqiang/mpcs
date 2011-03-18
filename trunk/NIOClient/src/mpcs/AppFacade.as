package mpcs
{
	import org.puremvc.patterns.facade.Facade;

	/**
	 * <b>Description: </b>Facade类，单例
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-17
	 **/
	public class AppFacade extends Facade {
		
		public function AppFacade() {
			super();
		}
		
		public static function getInstance():AppFacade {
			if(instance == null){
				instance = new AppFacade();
			}
			return instance as AppFacade;
		}
	}
}