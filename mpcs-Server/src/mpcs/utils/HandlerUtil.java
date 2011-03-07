package mpcs.utils;

import mpcs.handler.LogHandler;
import mpcs.handler.LoginHandler;
import mpcs.handler.RegisterHandler;
import nio.net.Notifier;

/**
 * Handler工具类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public final class HandlerUtil{

	/**
	 * 所有的Handler都要通过此方法注册
	 */
	public static void AddHandlerListener(){
		
		Notifier notifier = Notifier.getNotifier();
		
		//===============所有Handler写在我下面===============
		LogHandler loger = new LogHandler();
        RegisterHandler register = new RegisterHandler();
        LoginHandler login = new LoginHandler();
        
        notifier.addListener(loger);
        notifier.addListener(register);
        notifier.addListener(login);
        //===============所有Handler写在我上面===============
        
        System.out.println("Handler注册完毕~");
	}
}
