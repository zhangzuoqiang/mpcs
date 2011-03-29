package mpcs.vo;

/**
 * <p>Title: 用户基本信息VO</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-14
 */
public class BasicInfoVO {
	
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
	
	@Override
	public String toString() {
		return this.getUserName() + " " + this.getGender() + " " + this.getBirthday() 
					+ " " + this.getBloodType() + " " + this.getMarriage() + " " + this.getCareer()
					+ " " + this.getEducation() + " " + this.getResidence() + " " + this.getHome()
					+ " " + this.getIdCard();
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		if (userName == null) {
			this.userName = "";
			return;
		}
		this.userName = userName.trim();
	}
	public String getGender() {
		if (this.gender == null) {
			this.gender = "";
		}
		return gender;
	}
	public void setGender(String gender) {
		if (gender == null) {
			this.gender = "";
			return;
		}
		this.gender = gender.trim();
	}
	public String getBirthday() {
		if (this.birthday == null) {
			this.birthday = "";
		}
		return birthday;
	}
	public void setBirthday(String birthday) {
		if (birthday == null) {
			this.birthday = "";
			return;
		}
		this.birthday = birthday.trim();
	}
	public String getBloodType() {
		if (this.bloodType == null) {
			this.bloodType = "";
		}
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		if (bloodType == null) {
			this.bloodType = "";
			return;
		}
		this.bloodType = bloodType.trim();
	}
	public String getMarriage() {
		if (this.Marriage == null) {
			this.Marriage = "";
		}
		return Marriage;
	}
	public void setMarriage(String marriage) {
		if (marriage == null) {
			this.Marriage = "";
			return;
		}
		this.Marriage = marriage.trim();
	}
	public String getCareer() {
		if (this.career == null) {
			this.career = "";
		}
		return career;
	}
	public void setCareer(String career) {
		if (career == null) {
			this.career = "";
			return;
		}
		this.career = career.trim();
	}
	public String getEducation() {
		if (this.education == null) {
			this.education = "";
		}
		return education;
	}
	public void setEducation(String education) {
		if (education == null) {
			this.education = "";
			return;
		}
		this.education = education.trim();
	}
	public String getResidence() {
		if (this.residence == null) {
			this.residence = "";
		}
		return residence;
	}
	public void setResidence(String residence) {
		if (residence == null) {
			this.residence = "";
			return;
		}
		this.residence = residence.trim();
	}
	public String getHome() {
		if (this.home == null) {
			this.home = "";
		}
		return home;
	}
	public void setHome(String home) {
		if (home == null) {
			this.home = "";
			return;
		}
		this.home = home.trim();
	}
	public String getIdCard() {
		if (this.idCard == null) {
			this.idCard = "";
		}
		return idCard;
	}
	public void setIdCard(String idCard) {
		if (idCard == null) {
			this.idCard = "";
			return;
		}
		this.idCard = idCard.trim();
	}
}
