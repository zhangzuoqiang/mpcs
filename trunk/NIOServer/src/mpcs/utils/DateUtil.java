package mpcs.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Title: 时间格式转换帮助类</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-17
 */
public class DateUtil {
	
	/**
	 * 转换long时间类型为时间戳
	 * @param time
	 * @return
	 */
	public static Timestamp getTimestamp(Long time){
		return new Timestamp(time);
	}
	
	/**
	 * 得到当前时间的时间戳
	 * @return
	 */
	public static Timestamp getNowTimestamp() {
		long curTime = System.currentTimeMillis();
		return getTimestamp(curTime);
	}
	
	/**
	 * 将指定的日期字符串转化为日期对象
	 * <br/>默认日期格式为：yyyyMMddHHmmss
	 * @param dateStr
	 * @return
	 * @throws ParseException 
	 */
	public static Date getDate4String(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
		}
		return date;
	}
	
	/**
	 * 将指定的日期字符串转化为日期对象
	 * <br/>指定日期格式
	 * @param dateStr
	 * @param dateFormat
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate4String(String dateStr, String dateFormat) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = sdf.parse(dateStr);
		return date;
	}
	
	/**
	 * 得到当前的时间，时间格式yyyyMMdd HHmmss
	 * @return
	 */
	public static String getCurrentDate(){
//		DateFormat cnDate = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.CHINA);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}
	
	/**
	 * 得到当前的时间,自定义时间格式
	 * 如：yyyy-MM-dd HH:mm:ss
	 * @param dateFormat 输出显示的时间格式
	 * @return
	 */
	public static String getCurrentDate(String dateFormat){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(new Date());
	}
	
	/**
	 * 日期格式化，默认日期格式yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String getFormatDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	/**
	 * 日期格式化，自定义输出日期格式
	 * @param date
	 * @return
	 */
	public static String getFormatDate(Date date,String dateFormat){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}
}