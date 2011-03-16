package mpcs.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import mpcs.config.Configs;
import net.sf.json.JSONObject;
import nio.config.Debug;

/**
 * <p>Title: Json工具类</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-16
 */
public class JSONUtil {
	
	
	/**
	 * 根据vid和label，获取vip中的项值
	 * @param vid
	 * @param label
	 * @return
	 */
	public static int getVipItem(String vid, String label){
		JSONObject object = getVipJSONObjectByID(vid);
		String value = object.getString(label);
		return Integer.parseInt(value);
	}
	/**
	 * 根据vid , 获取单个的Vip JSONObject
	 * @param vid
	 * @return
	 */
	public static JSONObject getVipJSONObjectByID(String vid){
		JSONObject object = file2JsonObject(Configs.JSON_PREFIX + "vip.json");
		return object.getJSONObject(vid);
	}
	
	/**
	 * 将json文件转换为JsonObject
	 * @param path
	 * @return
	 */
	public static JSONObject file2JsonObject(String path){
		//获得json文件的内容
		String sets = ReadFile(path);
		//格式化成json对象
		JSONObject object = JSONObject.fromObject(sets);
		return object;
	}
	
	/**
	 * 读取json文件为string
	 * @param path
	 * @return
	 */
	public static String ReadFile(String path){
		File file = new File(path);
		BufferedReader reader = null;
		String laststr = "";
	    try {
	    	//System.out.println("以行为单位读取文件内容，一次读一整行：");
	    	reader = new BufferedReader(new FileReader(file));
	    	String temp = null; 
	    	//一次读入一行，直到读入null为文件结束
	    	while ((temp = reader.readLine()) != null) {
	    		laststr += temp;
	    	}
	    	reader.close();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } finally {
	    	if (reader != null) {
	    		try {
	    			reader.close();
	    		} catch (IOException e1) {}
	    	}
	    }
	    MoreUtils.trace(laststr, Debug.printOutput);
	    return laststr;
	}
}
