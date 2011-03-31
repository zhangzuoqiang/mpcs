package mpcs.location.vo;

/**
 * <p>Title: 经过请求google接口返回的json实体</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-31
 */
public class LngAndLatVO {
	
	/*
	{
		"location":{
		"latitude":23.10215,
		"longitude":113.812333,
		"address":{
		"country":"China",
		"country_code":"CN",
		"region":"Guangdong",
		"city":"Dongwan",
		"street":"Chaoyang Rd"
		}
		,
		"accuracy":5000.0
		}
		,
		"access_token":"2:MGe02apCNrFGDgCe:_3j_V9NH8TEaMvNp"
		}
	*/
	private float longitude;
	private float latitude;
	private float accuracy;
	private String country;
	private String country_code;
	private String region;
	private String city;
	private String street;
	private String access_token;
	
	public LngAndLatVO(float lng, float lat, float acc){
		this.setLongitude(lng);
		this.setLatitude(lat);
		this.setAccuracy(acc);
	}
	
	public LngAndLatVO(){
	}
	
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getAccess_token() {
		return access_token;
	}
}
