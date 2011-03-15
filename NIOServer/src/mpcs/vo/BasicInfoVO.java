package mpcs.vo;

/**
 * <p>Title: 用户基本信息VO</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-14
 */
public class BasicInfoVO extends UserVO {
	
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
		if (userName == null) {
			userName = "";
		}
		this.userName = userName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		if (gender == null) {
			gender = "";
		}
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		if (birthday == null) {
			birthday = "";
		}
		this.birthday = birthday;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		if (bloodType == null) {
			bloodType = "";
		}
		this.bloodType = bloodType;
	}
	public String getMarriage() {
		return Marriage;
	}
	public void setMarriage(String marriage) {
		if (marriage == null) {
			marriage = "";
		}
		Marriage = marriage;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		if (career == null) {
			career = "";
		}
		this.career = career;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		if (education == null) {
			education = "";
		}
		this.education = education;
	}
	public String getResidence() {
		return residence;
	}
	public void setResidence(String residence) {
		if (residence == null) {
			residence = "";
		}
		this.residence = residence;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		if (home == null) {
			home = "";
		}
		this.home = home;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		if (idCard == null) {
			idCard = "";
		}
		this.idCard = idCard;
	}
}
