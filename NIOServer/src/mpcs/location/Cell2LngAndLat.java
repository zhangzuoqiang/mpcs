package mpcs.location;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONObject;
import nio.config.Debug;
import nio.util.JsonTran;
import mpcs.location.vo.CellTowerVO;
import mpcs.location.vo.CellVO;
import mpcs.location.vo.LngAndLatVO;
import mpcs.utils.MoreUtils;

/**
 * <p>Title: 通过发送json格式的数据（cell_id相关）到Google服务器获取经纬度</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-30
 */
public class Cell2LngAndLat {
	
	private static String LOCATIONS_URL = "http://www.google.com/loc/json";
	
	/**
	 * 转换处理过的string类型为LngAndLatVO实体
	 * @param string
	 * @return vo/null
	 */
	public static synchronized LngAndLatVO format2VO(String string){
		if (string.equals("")) {
			return null;
		}
		JSONObject object = JSONObject.fromObject(string);
		String access_token = object.getString("access_token");
		JSONObject location = object.getJSONObject("location");
		double longitude = location.getDouble("longitude");
		double latitude = location.getDouble("latitude");
		double accuracy = location.getDouble("accuracy");
		JSONObject address = location.getJSONObject("address");
		String country = address.getString("country");
		String country_code = address.getString("country_code");
		String region = address.getString("region");
		String city = address.getString("city");
		String street = address.getString("street");
		LngAndLatVO vo = new LngAndLatVO(longitude, latitude, accuracy);
		vo.setAccess_token(access_token);
		vo.setCountry(country);
		vo.setCountry_code(country_code);
		vo.setRegion(region);
		vo.setCity(city);
		vo.setStreet(street);
		return vo;
	}
	
	/**
	 * 串行化，携带请求数据（json格式）发送到Google请求转换
	 * <br/>将Google返回的json实体转换为string类型，并返回
	 * @param
	 * @return jsonString
	 */
	public static synchronized String doCellPost(int mnc , int lac , int cell_id) {
		String request = "";
		String response = "";// post到服务端，服务端返回的String
		CellVO cellVO = new CellVO();
		CellTowerVO cellTowerVO = new CellTowerVO(mnc, lac, cell_id);
		cellVO.getCell_towers().add(cellTowerVO);
		request = JsonTran.bean2JSON(cellVO);
		request = JsonTran.string2JSON(request);
		MoreUtils.trace(request , true);
		byte[] postData = request.getBytes();
		URL url = null;
		InputStream in = null;
		OutputStream out = null;
		try {
			url = new URL(LOCATIONS_URL);
		} catch (MalformedURLException e) {
			if (Debug.printException) {
				e.printStackTrace();
			}
			MoreUtils.trace(e.getMessage(), Debug.printTestInfo);
			return "";
		}
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("HOST", "www.google.com");
			connection.setRequestProperty("Accept", "image/gif,image/x-xbitmap,application/json,*/*");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Content-Length", "" + postData.length);
			connection.setDoOutput(true);
			out = connection.getOutputStream();
			out.write(postData);
			in = connection.getInputStream();
			int c = 0;
			while ((c = in.read()) != -1) {
				response += (char) c ;
			}
		} catch (IOException e) {
			if (Debug.printException) {
				e.printStackTrace();
			}
			MoreUtils.trace(e.getMessage(), Debug.printTestInfo);
			return "";
		}
		return response;
	}
	
	/**
	 * 测试方法
	 * @param args
	 */
	public static void main(String[] args) {
		String response = doCellPost(0, 9932, 50184);
		
		if (!response.equals("")) {
			response = JsonTran.string2JSON(response);
			MoreUtils.trace(response, true);
		}
		
		LngAndLatVO vo = format2VO(response);
		MoreUtils.trace(vo.toString(), true);
	}
}
