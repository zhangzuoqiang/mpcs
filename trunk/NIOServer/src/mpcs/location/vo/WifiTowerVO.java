package mpcs.location.vo;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-30
 */
public class WifiTowerVO extends TowerVO {
	/*
	"wifi_towers": [{
								"mac_address": "01-23-45-67-89-ab",
								"signal_strength": 8,
								"age": 0
							}]
	*/
	private String mac_address;
	private int age = 0;
	
	public String getMac_address() {
		return mac_address;
	}
	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
