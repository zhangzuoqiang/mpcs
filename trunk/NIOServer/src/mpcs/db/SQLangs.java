package mpcs.db;

import mpcs.vo.UserVO;

/**
 * <p>Title: 存放所有SQL语句</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-8
 */
public final class SQLangs {
	
	/**
	 * 根据email，查询用户联系信息
	 * @param email
	 * @return
	 */
	public static String selectPhoneVOsByEmail(String email){
		return "select phoneID, relationship, status, belongto, begintime, type, longitude, " +
				"latitude, radius from phone where email = '" + email + "'";
	}
	
	/**
	 * 通过email，保存用户联系信息
	 * @param vo
	 * @return
	 */
	public static String saveContactInfo(UserVO vo){
		return "update user set qq = '" + vo.getContactInfo().getQQ() + "', msn = '" + vo.getContactInfo().getMsn() 
		+ "', mobile = '"+ vo.getContactInfo().getMobile() + "', tel = '" + vo.getContactInfo().getTel() + "', zip = '" +
		vo.getContactInfo().getZip() + "' where email = '" + vo.getEmail() + "'";
	}
	/**
	 * 通过email，保存用户基本信息
	 * @param vo
	 * @return
	 */
	public static String saveBasicInfo(UserVO vo){
		return "update user set realname = '" + vo.getBasicInfo().getUserName() + "', gender = '" + vo.getBasicInfo().getGender() + 
		"', birthday = '" + vo.getBasicInfo().getBirthday() + "', bloodtype = '" + vo.getBasicInfo().getBloodType() + "', marstatus = '" + vo.getBasicInfo().getMarriage()
		+ "', job = '" +vo.getBasicInfo().getCareer() + "', education = '" +vo.getBasicInfo().getEducation() + "', residence = '" +vo.getBasicInfo().getResidence() 
		+ "', hometown = '" + vo.getBasicInfo().getHome() + "', idcard = '" + vo.getBasicInfo().getIdCard() + "' where email = '" + vo.getEmail() + "'";
	}
	
	/**
	 * 通过email，修改密码
	 * @param email
	 * @param newpwd
	 * @return
	 */
	public static String updatePwd(String email, String newpwd){
		return "update user set password = '" + newpwd + "' where email = '" + email + "'";
	}
	
	/**
	 * 根据email，查询用户联系信息
	 * @param email
	 * @return
	 */
	public static String selectContactInfoByEmail(String email){
		return "select qq, msn, mobile, tel, zip from user where email = '" + email + "'";
	}
	
	/**
	 * 根据email，查询用户基本信息
	 * @param email
	 * @return
	 */
	public static String selectBasicInfoByEmail(String email){
		return "select email, realname, gender, birthday, bloodtype, marstatus, job, education," +
				"residence, hometown, idcard from user where email = '" + email + "'";
	}
	
	/**
	 * 查询email，是否存在此用户
	 * @param email
	 * @return
	 */
	public static String selectUserByEmail(String email){
		return "select email , password from user where email = '" + email + "'";
	}
	
	/**
	 * 根据email查询pwd，验证登录
	 * @param email
	 * @return
	 */
	public static String selectPwdByEmail(String email){
		return "select password from user where email = '" + email + "'";
	}
	
	public static String addUser(String email, String pwd){
		return "INSERT INTO user(EMAIL,PASSWORD) VALUES('" + email + "','" + pwd + "');";
	}
}
