package mpcs.utils;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TimeUtil {
	
	private static Timer timer;
	
	/**
	 * 定时执行
	 * @param task       定时执行的任务
	 * @param interval 定时执行的时间间隔(单位: s)
	 */
	public static Timer operateOnTime(TimerTask task, int interval) {
		// 创建一个定时器
		timer = new Timer();
		// 设置从当前时间开始执行，然后每个interval秒中执行一次
		timer.schedule(task, Calendar.getInstance ().getTime(), interval * 1000);
		return timer;
	}
}
