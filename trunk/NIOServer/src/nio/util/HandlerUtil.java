package nio.util;

import mpcs.utils.MoreUtils;
import nio.core.Notifier;
import nio.handler.LogHandler;
import nio.handler.TimeHandler;

/**
 * <p>Title: Handler工具类</p>
 * <p>Description: 所有的Handler都要通过此类注册</p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public final class HandlerUtil{
	
	
	public static void AddHandlerListener(){
		
		Notifier notifier = Notifier.getNotifier();
		
		//===============所有Handler写在我下面===============
		LogHandler loger = new LogHandler();
		TimeHandler timer = new TimeHandler();
        
        notifier.addListener(loger);
        notifier.addListener(timer);
        //===============所有Handler写在我上面===============
        
        MoreUtils.trace(LangUtil.get("10001"));
	}
}
