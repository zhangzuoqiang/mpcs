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
	private BasicInfoVO basicInfo;
	private ContactInfoVO contactInfo;
	private ArrayList<PhoneVO> phones;
	
	public UserVO(){
		basicInfo = new BasicInfoVO();
		contactInfo = new ContactInfoVO();
		phones = new ArrayList<PhoneVO>();
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
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
