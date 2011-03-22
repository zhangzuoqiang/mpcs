package mpcs.command
{
	import org.puremvc.interfaces.ICommand;
	import org.puremvc.interfaces.INotification;
	import org.puremvc.patterns.command.SimpleCommand;
	
	/**
	 * <b>Description: </b>系统启动Command
	 * <br/><b>Author: </b>zhangzuoqiang
	 * <br/><b>Date: </b>Mar 20, 2011
	 **/
	public class StartupCommand extends SimpleCommand implements ICommand {
		
		override public function execute(notification:INotification):void {
			
			// 在此注册所有的Proxy
//			facade.registerProxy( new DataProxy() );
			
			// 在此注册所有的Mediator
//			facade.registerMediator( new ApplicationMediator( notification.getBody() as App ) );
		}
	}
}