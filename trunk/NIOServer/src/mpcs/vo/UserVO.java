package mpcs.vo;

import java.util.ArrayList;

/**
 * <p>Title: 用户实体类</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-13
 */
public class UserVO {
	
	private String email;
	private String password;
	private String isTip; // 是否开启手机提醒服务
	private BasicInfoVO basicInfo;
	private ContactInfoVO contactInfo;
	private ArrayList<PhoneVO> phones;
	
	public UserVO(){
		basicInfo = new BasicInfoVO();
		contactInfo = new ContactInfoVO();
		phones = new ArrayList<PhoneVO>();
	}
	
	@Override
	public String toString() {
		return this.getEmail() + " " + this.getPassword() + " " + this.getIsTip() 
					+ " " + this.getBasicInfo().toString() + " " + this.getContactInfo().toString();
	}

	public String getIsTip() {
		if (this.isTip == null) {
			this.isTip = "";
		}
		return isTip;
	}
	public void setIsTip(String isTip) {
		if (isTip == null) {
			this.isTip = "";
			return;
		}
		this.isTip = isTip.trim();
	}
	public ArrayList<PhoneVO> getPhones() {
		return phones;
	}
	public void setPhones(ArrayList<PhoneVO> phones) {
		this.phones = phones;
	}
	public BasicInfoVO getBasicInfo() {
		return basicInfo;
	}
	public void setBasicInfo(BasicInfoVO basicInfo) {
		this.basicInfo = basicInfo;
	}
	public ContactInfoVO getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(ContactInfoVO contactInfo) {
		this.contactInfo = contactInfo;
	}
	public String getEmail() {
		if (this.email == null) {
			this.email = "";
		}
		return email;
	}
	public void setEmail(String email) {
		if (email == null) {
			this.email = "";
			return;
		}
		this.email = email.trim();
	}
	public String getPassword() {
		if (this.password == null) {
			this.password = "";
		}
		return password;
	}
	public void setPassword(String password) {
		if (password == null) {
			this.password = "";
			return;
		}
		this.password = password.trim();
	}
}
