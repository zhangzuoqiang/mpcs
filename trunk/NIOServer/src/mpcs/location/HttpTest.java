package mpcs.location;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import mpcs.utils.MoreUtils;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-30
 */
public class HttpTest {
	
	private final static String json = "{\n\"version\": \"1.1.0\",\n\"host\": \"maps.google.com\",\n"
			+ "\"access_token\": \"2:k7j3G6LaL6u_lafw:4iXOeOpTh1glSXe\",\n"
			+ "\"home_mobile_country_code\": 310,\n\"home_mobile_network_code\": 410,\n"
			+ "\"radio_type\": \"gsm\",\n" + "\"carrier\": \"Vodafone\",\n"
			+ "\"request_address\": true,\n"
			+ "\"address_language\": \"en_GB\",\n" + "\"location\": {\n"
			+ "\"latitude\": 51.0,\n" + "\"longitude\": -0.1\n" + "},\n"
			+ "\"cell_towers\": [\n" + "{\n" + "\"cell_id\": 42,\n"
			+ "\"location_area_code\": 415,\n"
			+ "\"mobile_country_code\": 310,\n"
			+ "\"mobile_network_code\": 410,\n" + "\"age\": 0,\n"
			+ "\"signal_strength\": -60,\n" + "\"timing_advance\": 5555\n"
			+ "},\n" + "{\n" + "\"cell_id\": 88,\n"
			+ "\"location_area_code\": 415,\n"
			+ "\"mobile_country_code\": 310,\n"
			+ "\"mobile_network_code\": 580,\n" + "\"age\": 0,\n"
			+ "\"signal_strength\": -70,\n" + "\"timing_advance\": 7777\n"
			+ "}\n" + "],\n" + "\"wifi_towers\": [\n" + "  {\n"
			+ "    \"mac_address\": \"01-23-45-67-89-ab\",\n"
			+ "    \"signal_strength\": 8,\n" + "   \"age\": 0\n" + " },\n"
			+ "  {\n" + "   \"mac_address\": \"01-23-45-67-89-ac\",\n"
			+ "    \"signal_strength\": 4,\n" + "   \"age\": 0\n" + "  }\n"
			+ " ]\n" + "}";
	
	public static void main(String[] args) throws IOException {
		URL url = null;
		MoreUtils.trace(json, true);
		byte[] postData = json.getBytes();
		InputStream in = null;
		OutputStream out = null;
		try {
			url = new URL("http://www.google.com/loc/json");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("HOST", "www.google.com");
		connection.setRequestProperty("Accept", "image/gif,image/x-xbitmap,"
				+ "application/json,*/*");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Content-Length", "" + postData.length);
		connection.setDoOutput(true);
		out = connection.getOutputStream();
		out.write(postData);
		in = connection.getInputStream();
		int c;
		while ((c = in.read()) != -1) {
			System.out.print((char) c);
		}

	}

}
