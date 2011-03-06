package mpcs.utils;

import mpcs.handler.LogHandler;
import mpcs.handler.UserRegisterHandler;
import nio.net.Notifier;

public class HandlerUtil{

	/**
	 * 所有的Handler都要通过此方法注册
	 */
	public static void AddHandlerListener(){
		//===============所有Handler写在我下面===============
		LogHandler loger = new LogHandler();
        UserRegisterHandler register = new UserRegisterHandler();
        Notifier notifier = Notifier.getNotifier();
        notifier.addListener(loger);
        notifier.addListener(register);
        //===============所有Handler写在我上面===============
        System.out.println("Handler注册完毕~");
	}
}
