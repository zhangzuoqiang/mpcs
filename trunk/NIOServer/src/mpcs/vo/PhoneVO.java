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
	
	@Override
	public String toString() {
		return "[Phone: " + this.phoneID + " " + this.relationship + " " + this.status + " " + this.belongto + " " + this.begintime
		 			+ " " + this.type + " " + this.longitude + " " + this.latitude  + " " + this.radius + "]";
	}

	public ArrayList<PositionVO> getPositions() {
		return positions;
	}
	public void setPositions(ArrayList<PositionVO> positions) {
		this.positions = positions;
	}
	public String getPhoneID() {
		if (this.phoneID == null) {
			this.phoneID = "";
		}
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
		if (this.relationship == null) {
			this.relationship = "";
		}
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
		if (this.status == null) {
			this.status = "";
		}
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
		if (this.belongto == null) {
			this.belongto = "";
		}
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
		if (this.begintime == null) {
			this.begintime = "";
		}
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
		if (this.type == null) {
			this.type = "";
		}
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
		if (this.longitude == null) {
			this.longitude = "";
		}
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
		if (this.latitude == null) {
			this.latitude = "";
		}
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
		if (this.radius == null) {
			this.radius = "";
		}
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
