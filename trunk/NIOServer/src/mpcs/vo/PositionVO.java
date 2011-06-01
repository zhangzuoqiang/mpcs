package mpcs.vo;

/**
 * <p>Title: 手机对应经纬度 实体类</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-17
 */
public class PositionVO {
	
	private String date;
	private float longitude;
	private float latitude;
	private float accuracy;
	
	@Override
	public String toString() {
		return "[Position: " + String.valueOf(this.longitude) + " " + String.valueOf(this.latitude) 
					+ " " + this.date + "]";
	}
	
	public float getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
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
	public String getDate() {
		if (this.date == null) {
			this.date = "";
		}
		return date;
	}
	public void setDate(String date) {
		if (date == null) {
			this.date = "";
			return;
		}
		this.date = date;
	}
}
