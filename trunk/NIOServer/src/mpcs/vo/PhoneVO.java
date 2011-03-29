package mpcs.vo;

import java.util.ArrayList;

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
	private ArrayList<PositionVO> positions; // 位置列表
	
	public PhoneVO(){
		positions = new ArrayList<PositionVO>();
	}
	
	public ArrayList<PositionVO> getPositions() {
		return positions;
	}
	public void setPositions(ArrayList<PositionVO> positions) {
		this.positions = positions;
	}
	public String getPhoneID() {
		return phoneID;
	}
	public void setPhoneID(String phoneID) {
		if (phoneID == null) {
			this.phoneID = "";
			return;
		}
		this.phoneID = phoneID.trim();
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		if (relationship == null) {
			this.relationship = "";
			return;
		}
		this.relationship = relationship.trim();
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		if (status == null) {
			this.status = "";
			return;
		}
		this.status = status.trim();
	}
	public String getBelongto() {
		return belongto;
	}
	public void setBelongto(String belongto) {
		if (belongto == null) {
			this.belongto = "";
			return;
		}
		this.belongto = belongto.trim();
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		if (begintime == null) {
			this.begintime = "";
			return;
		}
		this.begintime = begintime.trim();
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		if (type == null) {
			this.type = "";
			return;
		}
		this.type = type.trim();
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		if (longitude == null) {
			this.longitude = "";
			return;
		}
		this.longitude = longitude.trim();
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		if (latitude == null) {
			this.latitude = "";
			return;
		}
		this.latitude = latitude.trim();
	}
	public String getRadius() {
		return radius;
	}
	public void setRadius(String radius) {
		if (radius == null) {
			this.radius = "";
			return;
		}
		this.radius = radius.trim();
	}
}
