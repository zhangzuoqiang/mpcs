package mpcs.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import mpcs.db.ConnectionManager;

/**
 * 数据库操作工具类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public final class DBUtil {

	protected static ConnectionManager cm = ConnectionManager.getInstance();
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	
	/**
	 * 验证此邮箱是否已注册
	 * @param email
	 * @return
	 */
	public static boolean selectByEmail(String email){
		boolean isExist = false;
		try {
			 conn = cm.getConnection();
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery(SQLangUtil.selectUserByEmail(email));
			 rs.next();
			 if (rs.getRow() == 0) {
				 isExist = false;
			}else {
				isExist = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
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
			 succ = stmt.execute(SQLangUtil.addUser(email, pwd));
		} catch (Exception e) {
			e.printStackTrace();
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
			 rs = stmt.executeQuery(SQLangUtil.selectPwdByEmail(email));
			 rs.next();
			 if (rs.getRow() > 0) {
				 pwdStr = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
            }
        }
        
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
            }
        }
        
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
	}
}
