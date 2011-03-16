package mpcs.vo;

/**
 * <p>Title: 绑定手机 相关信息实体</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-16
 */
public class PhoneVO {
	private String phoneID; // 手机号码
	private String relationship; // 关系
	private String status; // 服务状态
	private String belongto; // 归属地
	private String begintime; // 服务开始时间
	private String type; // 类型
	private String longitude; // 原点经度
	private String latitude; // 原点纬度
	private String radius; // 最大偏移距离
	
	public String getPhoneID() {
		return phoneID;
	}
	public void setPhoneID(String phoneID) {
		this.phoneID = phoneID;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBelongto() {
		return belongto;
	}
	public void setBelongto(String belongto) {
		this.belongto = belongto;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getRadius() {
		return radius;
	}
	public void setRadius(String radius) {
		this.radius = radius;
	}
}
