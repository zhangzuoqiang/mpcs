package mpcs.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import nio.config.Debug;

import mpcs.utils.MoreUtils;
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
			closeAll();
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
			closeAll();
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
			closeAll();
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
			closeAll();
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
			closeAll();
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
				isExist = rs.getString(2);
			}
		} catch (Exception e) {
			if (Debug.printException) {
				e.printStackTrace();
			}
		}finally{
			closeAll();
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
			closeAll();
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
				 pwdStr = rs.getString(1);
			}
		} catch (Exception e) {
			if (Debug.printException) {
				e.printStackTrace();
			}
		}finally{
			closeAll();
		}
		return pwdStr;
	}
	
	/**
	 * 关闭相关连接
	 */
	protected static void closeAll(){
		if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
            	if (Debug.printException) {
    				e.printStackTrace();
    			}
            	MoreUtils.trace("closeAll1", Debug.printException);
            }
        }
        
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
            	if (Debug.printException) {
    				e.printStackTrace();
    			}
            	MoreUtils.trace("closeAll2", Debug.printException);
            }
        }
        
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
            	if (Debug.printException) {
    				e.printStackTrace();
    			}
            	MoreUtils.trace("closeAll3", Debug.printException);
            }
        }
	}
}
