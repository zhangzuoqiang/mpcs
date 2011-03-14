package mpcs.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import nio.config.Debug;

import mpcs.utils.MoreUtils;


/**
 * <p>Title: 数据库操作工具类</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public final class ExeSQL {

	protected static DBConnect cm = DBConnect.getInstance();
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	
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
