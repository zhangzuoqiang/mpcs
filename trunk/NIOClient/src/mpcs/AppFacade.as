package mpcs
{
	import org.puremvc.interfaces.IFacade;
	import org.puremvc.patterns.facade.Facade;
	import org.puremvc.patterns.observer.Notification;

	/**
	 * <b>Description: </b>Facade类，单例
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>2011-3-17
	 **/
	public class AppFacade extends Facade implements IFacade {
		
		public static const NAME:String = "AppFacade";
		public static const STARTUP:String = "StartUp";
		
		public function AppFacade() {
			super();
		}
		
		public static function getInstance():AppFacade {
			return (instance ? instance : new AppFacade()) as AppFacade;
		}
		
		public function startup(stage:Object):void {
			sendNotification( STARTUP, 	stage );
		}
		
		override public function sendNotification(notificationName:String, body:Object=null, type:String=null):void {
			trace( 'Sent ' + notificationName );
			notifyObservers( new Notification( notificationName, body, type ) );
		}
		
		override protected function initializeController():void {
			super.initializeController();
			
			// 在此注册系统所有的Command
			registerCommand( STARTUP, StartupCommand );
			
		}
		
	}
}