package mpcs.location.vo;

/**
 * <p>Title: </p>
 * <p>Request Format</p>
{
  "version": "1.1.0",
  "host": "maps.google.com",
  "home_mobile_country_code": 310,
  "home_mobile_network_code": 410,
  "radio_type": "gsm",
  "carrier": "Vodafone",
  "request_address": true,
  "address_language": "en_GB",
  "location": {
    "latitude": 51.0,
    "longitude": -0.1
  },
  "cell_towers": [
    {
      "cell_id": "42",
      "location_area_code": 415,
      "mobile_country_code": 310,
      "mobile_network_code": 410,
      "age": 0,
      "signal_strength": -60,
      "timing_advance": 5555
    },
    {
      "cell_id": "88",
      "location_area_code": 415,
      "mobile_country_code": 310,
      "mobile_network_code": 580,
      "age": 0,
      "signal_strength": -70,
      "timing_advance": 7777
    }
  ],
  "wifi_towers": [
    {
      "mac_address": "01-23-45-67-89-ab",
      "signal_strength": 8,
      "age": 0
    },
    {
      "mac_address": "01-23-45-67-89-ac",
      "signal_strength": 4,
      "age": 0
    }
  ]
}
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-30
 */
public abstract class TowerVO {
	private int signal_strength = 4;

	public int getSignal_strength() {
		return signal_strength;
	}
	public void setSignal_strength(int signal_strength) {
		this.signal_strength = signal_strength;
	}
}
