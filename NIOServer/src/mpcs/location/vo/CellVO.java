package mpcs.location.vo;

import java.util.ArrayList;

/**
 * <p>Title: 封装发送到Google服务器的json对象</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-30
 */
public class CellVO {
	
	private String version = "1.1.0";
	private String host = "maps.google.com";
	private int home_mobile_country_code = 460;
	private int home_mobile_network_code = 0;
//	private String address_language = "zh_CN";
	private String address_language = "en_US";//设置返回数据的语言
	private String radio_type = "gsm";
	private boolean request_address = true;
	private ArrayList<CellTowerVO> cell_towers;
//	private ArrayList<WifiTowerVO> wifi_towers;
	
	public CellVO(){
		this.cell_towers = new ArrayList<CellTowerVO>();
//		this.wifi_towers = new ArrayList<WifiTowerVO>();
	}
	
	/*public ArrayList<WifiTowerVO> getWifi_towers() {
		return wifi_towers;
	}
	public void setWifi_towers(ArrayList<WifiTowerVO> wifi_towers) {
		this.wifi_towers = wifi_towers;
	}*/
	public ArrayList<CellTowerVO> getCell_towers() {
		return cell_towers;
	}
	public void setCell_towers(ArrayList<CellTowerVO> cell_towers) {
		this.cell_towers = cell_towers;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getHome_mobile_country_code() {
		return home_mobile_country_code;
	}
	public void setHome_mobile_country_code(int home_mobile_country_code) {
		this.home_mobile_country_code = home_mobile_country_code;
	}
	public int getHome_mobile_network_code() {
		return home_mobile_network_code;
	}
	public void setHome_mobile_network_code(int home_mobile_network_code) {
		this.home_mobile_network_code = home_mobile_network_code;
	}
	public String getAddress_language() {
		return address_language;
	}
	public void setAddress_language(String address_language) {
		this.address_language = address_language;
	}
	public String getRadio_type() {
		return radio_type;
	}
	public void setRadio_type(String radio_type) {
		this.radio_type = radio_type;
	}
	public boolean isRequest_address() {
		return request_address;
	}
	public void setRequest_address(boolean request_address) {
		this.request_address = request_address;
	}
}
