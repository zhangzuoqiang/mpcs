package mpcs.location;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import nio.config.Debug;
import nio.util.JsonUtil;
import mpcs.location.vo.CellTowerVO;
import mpcs.location.vo.CellVO;
import mpcs.utils.MoreUtils;

/**
 * <p>Title: 通过发送json格式的数据（cell_id相关）到Google服务器获取经纬度</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-30
 */
public class Cell2LngAndLat {
	private static String request = "";
	private static String response = "";// post到服务端，服务端返回的Object	
	public static String LOCATIONS_URL = "http://www.google.com/loc/json";
	private static CellVO cellVO;
	private static CellTowerVO cellTowerVO;
	
	public static synchronized void doCellPost(int mnc , int lac , int cell_id) throws IOException{
		cellTowerVO = new CellTowerVO(mnc, lac, cell_id);
		cellVO = new CellVO();
		cellVO.getCell_towers().add(cellTowerVO);
		request = JsonUtil.bean2JSON(cellVO);
		request = JsonUtil.string2JSON(request);
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
		}
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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
	}
	
	public static void main(String[] args) throws IOException{
		doCellPost(0, 9932, 50184);
		MoreUtils.trace(response, true);
	}
	
}
