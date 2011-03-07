package mpcs.cmd;

/**
 * 用户基本信息 协议类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public class BasicInfoCmd extends UserCmd {
	
	/****/
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String gender;
	private String birthday;
	private String bloodType;
	private String Marriage;
	private String career;
	private String education;
	private String residence;
	private String home;
	private String idCard;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public String getMarriage() {
		return Marriage;
	}
	public void setMarriage(String marriage) {
		Marriage = marriage;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getResidence() {
		return residence;
	}
	public void setResidence(String residence) {
		this.residence = residence;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
}
