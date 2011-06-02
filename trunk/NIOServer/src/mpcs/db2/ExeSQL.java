package mpcs.db2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import nio.config.Debug;

import mpcs.utils.MoreUtils;
import mpcs.vo.PhoneVO;
import mpcs.vo.PositionVO;
import mpcs.vo.UserVO;


/**
 * <p>Title: 执行数据库操作的类</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-8
 */
public final class ExeSQL {

	protected static DBConnManager cm = DBConnManager.getInstance();
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	
	
	
	
	/**
	 * 根据phone，查询历史数据
	 * @param phone
	 * @return
	 */
	public static ArrayList<PositionVO> selectPositionVOs(String phone){
		ArrayList<PositionVO> list = new ArrayList<PositionVO>();
		String lngandlat = "";
		try {
			 conn = cm.getConnection();
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery(SQLangs.selectPositionsVOsByPhone(phone));
			 while (rs.next()) {
				 PositionVO position = new PositionVO();
				 position.setDate(rs.getString(1));
				 lngandlat = rs.getString(2);
				 // 将经纬度字段分解
				 String[] latlngacc = lngandlat.split(";");
				 position.setLatitude( Float.parseFloat(latlngacc[0])) ;
				 position.setLongitude( Float.parseFloat(latlngacc[1])) ;
//				 position.setAccuracy(Float.parseFloat(latlngacc[2]));
				 list.add(position);
			}
		} catch (Exception e) {
			if (Debug.printException) {
				e.printStackTrace();
			}
		}finally{
			releaseAll();
		}
		return list;
	}
	
	/**
	 * 更改手机提示的状态
	 * @param email
	 * @param command
	 * @return
	 */
	public static boolean updateTipByEmail(String email, String command){
		boolean flag = false;
		try {
			 conn = cm.getConnection();
			 stmt = conn.createStatement();
			 flag = stmt.execute(SQLangs.updateTipByEmail(email, command));
		} catch (Exception e) {
			if (Debug.printException) {
				e.printStackTrace();
			}
		}finally{
			releaseAll();
		}
		return flag;
	}
	
	/**
	 * 添加绑定手机后的账户余额更改
	 * @param email
	 * @param account
	 * @return
	 */
	public static boolean updateAccountByEmail(String email, String account){
		boolean flag = false;
		try {
			 conn = cm.getConnection();
			 stmt = conn.createStatement();
			 flag = stmt.execute(SQLangs.updateAccountByEmail(email, account));
		} catch (Exception e) {
			if (Debug.printException) {
				e.printStackTrace();
			}
		}finally{
			releaseAll();
		}
		return flag;
	}
	
	/**
	 * 根据email查询账户的可用余额
	 * @param email
	 * @return
	 */
	public static double selectAccountByEmail(String email){
		double money = 0;
		try {
			 conn = cm.getConnection();
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery(SQLangs.selectAccountByEmail(email));
			 rs.next();
			 if (rs.getRow() == 0) {
				 money = 0;
			}else {
				money = Float.parseFloat(rs.getString(1));
			}
		} catch (Exception e) {
			if (Debug.printException) {
				e.printStackTrace();
			}
		}finally{
			releaseAll();
		}
		return money;
	}
	
	/**
	 * 执行添加绑定手机
	 * @param vo
	 * @return
	 */
	public static boolean addPhoneVO(UserVO vo){
		boolean flag = false;
		try {
			 conn = cm.getConnection();
			 stmt = conn.createStatement();
			 flag = stmt.execute(SQLangs.addBindMobile(vo));
		} catch (Exception e) {
			if (Debug.printException) {
				e.printStackTrace();
			}
		}finally{
			releaseAll();
		}
		return flag;
	}
	
	/**
	 * 根据email，查询绑定手机列表
	 * @param email
	 * @return
	 */
	public static ArrayList<PhoneVO> selectPhoneVOs(String email){
		ArrayList<PhoneVO> list = new ArrayList<PhoneVO>();
		try {
			 conn = cm.getConnection();
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery(SQLangs.selectPhoneVOsByEmail(email));
			 while (rs.next()) {
				 PhoneVO phone = new PhoneVO();
				 phone.setPhoneID(rs.getString(1));
				 phone.setRelationship(rs.getString(2));
				 phone.setStatus(rs.getString(3));
				 phone.setBelongto(rs.getString(4));
				 phone.setBegintime(rs.getString(5));
				 phone.setType(rs.getString(6));
				 phone.setLongitude(rs.getString(7));
				 phone.setLatitude(rs.getString(8));
				 phone.setRadius(rs.getString(9));
				 list.add(phone);
			}
		} catch (Exception e) {
			if (Debug.printException) {
				e.printStackTrace();
			}
		}finally{
			releaseAll();
		}
		return list;
	}
	
	/**
	 * 保存用户联系信息
	 * @param vo
	 * @return
	 */
	public static boolean saveContactInfo(UserVO vo){
		boolean flag = false;
		try {
			 conn = cm.getConnection();
			 stmt = conn.createStatement();
			 flag = stmt.execute(SQLangs.saveContactInfo(vo));
		} catch (Exception e) {
			if (Debug.printException) {
				e.printStackTrace();
			}
		}finally{
			releaseAll();
		}
		return flag;
	}
	
	/**
	 * 保存用户基本信息
	 * @param vo
	 * @return
	 */
	public static boolean saveBasicInfo(UserVO vo){
		boolean flag = false;
		try {
			 conn = cm.getConnection();
			 stmt = conn.createStatement();
			 flag = stmt.execute(SQLangs.saveBasicInfo(vo));
		} catch (Exception e) {
			if (Debug.printException) {
				e.printStackTrace();
			}
		}finally{
			releaseAll();
		}
		return flag;
	}
	
	/**
	 * 修改绑定手机的初始位置
	 * @param mobile
	 * @param lat
	 * @param lng
	 * @param acc
	 * @return
	 */
	public static boolean updateInitPosition(String mobile, String lat, String lng, String acc){
		boolean flag = false;
		try {
			 conn = cm.getConnection();
			 stmt = conn.createStatement();
			 flag = stmt.execute(SQLangs.updateInitPosition(mobile, lat, lng, acc));
		} catch (Exception e) {
			if (Debug.printException) {
				e.printStackTrace();
			}
		}finally{
			releaseAll();
		}
		return flag;
	}
	
	/**
	 * 修改用户密码
	 * @param email
	 * @param newpwd
	 * @return
	 */
	public static boolean updatePwd(String email, String newpwd){
		boolean flag = false;
		try {
			 conn = cm.getConnection();
			 stmt = conn.createStatement();
			 flag = stmt.execute(SQLangs.updatePwd(email, newpwd));
		} catch (Exception e) {
			if (Debug.printException) {
				e.printStackTrace();
			}
		}finally{
			releaseAll();
		}
		return flag;
	}
	
	/**
	 * 通过email查询联系信息
	 * @param email
	 * @return
	 */
	public static UserVO selectContactInfoByEmail(String email){
		UserVO vo = new UserVO();
		try {
			 conn = cm.getConnection();
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery(SQLangs.selectContactInfoByEmail(email));
			 rs.next();
			 if (rs.getRow() == 0) {
				 vo = null;
			}else {
				vo.setEmail(email);
				vo.getContactInfo().setQQ(rs.getString(1));
				vo.getContactInfo().setMsn(rs.getString(2));
				vo.getContactInfo().setMobile(rs.getString(3));
				vo.getContactInfo().setTel(rs.getString(4));
				vo.getContactInfo().setZip(rs.getString(5));
			}
		} catch (Exception e) {
			if (Debug.printException) {
				e.printStackTrace();
			}
		}finally{
			releaseAll();
		}
		return vo;
	}
	
	/**
	 * 通过email查询基本信息
	 * @param email
	 * @return
	 */
	public static UserVO selectBasicInfoByEmail(String email){
		UserVO vo = new UserVO();
		try {
			 conn = cm.getConnection();
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery(SQLangs.selectBasicInfoByEmail(email));
			 rs.next();
			 if (rs.getRow() == 0) {
				 vo = null;
			}else {
				vo.setEmail(rs.getString(1));
				vo.getBasicInfo().setUserName(rs.getString(2));
				vo.getBasicInfo().setGender(rs.getString(3));
				vo.getBasicInfo().setBirthday(rs.getString(4));
				vo.getBasicInfo().setBloodType(rs.getString(5));
				vo.getBasicInfo().setMarriage(rs.getString(6));
				vo.getBasicInfo().setCareer(rs.getString(7));
				vo.getBasicInfo().setEducation(rs.getString(8));
				vo.getBasicInfo().setResidence(rs.getString(9));
				vo.getBasicInfo().setHome(rs.getString(10));
				vo.getBasicInfo().setIdCard(rs.getString(11));
			}
		} catch (Exception e) {
			if (Debug.printException) {
				e.printStackTrace();
			}
		}finally{
			releaseAll();
		}
		return vo;
	}
	
	/**
	 * 验证此邮箱是否已注册，如果注册过了则返回该邮箱对应的密码
	 * @param email
	 * @return
	 */
	public static String selectByEmail(String email){
		String isExist = "";
		try {
			 conn = cm.getConnection();
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery(SQLangs.selectUserByEmail(email));
			 rs.next();
			 if (rs.getRow() == 0) {
				 isExist = "";
			}else {
				isExist = rs.getString(2).trim();
			}
		} catch (Exception e) {
			if (Debug.printException) {
				e.printStackTrace();
			}
		}finally{
			releaseAll();
		}
		return isExist;
	}
	
	/**
	 * 添加用户
	 * @param email
	 * @param pwd
	 * @return 0为添加失败，1为添加成功
	 */
	public static int addUser(String email, String pwd){
		int label = 0;
		boolean succ = false;
		try {
			 conn = cm.getConnection();
			 stmt = conn.createStatement();
			 succ = stmt.execute(SQLangs.addUser(email, pwd));
		} catch (Exception e) {
			if (Debug.printException) {
				e.printStackTrace();
			}
		}finally{
			releaseAll();
		}
		if (succ) {
			label = 0;
		}else {
			label = 1;
		}
		return label;
	}
	
	/**
	 * 根据email查询pwd，验证用户登录
	 * @param email
	 * @return
	 */
	public static String selectPwdByEmail(String email){
		String pwdStr = "";
		try {
			 conn = cm.getConnection();
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery(SQLangs.selectPwdByEmail(email));
			 rs.next();
			 if (rs.getRow() > 0) {
				 pwdStr = rs.getString(1).trim();
			}
		} catch (Exception e) {
			if (Debug.printException) {
				e.printStackTrace();
			}
		}finally{
			releaseAll();
		}
		return pwdStr;
	}
	
	/**
	 * 关闭相关连接
	 */
	protected static void releaseAll(){
		if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
            	if (Debug.printException) {
    				e.printStackTrace();
    			}
            	MoreUtils.trace("releaseAll1", Debug.printException);
            }
        }
        
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
            	if (Debug.printException) {
    				e.printStackTrace();
    			}
            	MoreUtils.trace("releaseAll2", Debug.printException);
            }
        }
        
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
            	if (Debug.printException) {
    				e.printStackTrace();
    			}
            	MoreUtils.trace("releaseAll3", Debug.printException);
            }
        }
	}
}
