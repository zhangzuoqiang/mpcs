package mpcs.libs.utils;

import mpcs.libs.core.Notifier;
import mpcs.libs.handler.LogHandler;

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
        
        notifier.addListener(loger);
        //===============所有Handler写在我上面===============
        
        MoreUtil.trace("Handler注册完毕~");
	}
}
