package mpcs.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import mpcs.config.SQLConst;
import mpcs.db.ConnectionManager;

/**
 * 数据库操作工具类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public class DBUtil {

	protected static ConnectionManager cm = ConnectionManager.getInstance();
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	
	/**
	 * 用户注册操作
	 * @param email
	 * @return
	 */
	public static boolean selectByEmail(String email){
		boolean isExist = false;
		try {
			 conn = cm.getConnection();
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery(SQLConst.SELECT_USER_BY_EMAIL + email + "'");
			 rs.next();
			 if (rs.getRow() == 0) {
				 isExist = false;
				 System.out.println("注册成功：" + email);
			}else {
				isExist = true;
				System.out.println("该邮箱已被注册：" + rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return isExist;
	}
	
	/**
	 * 关闭相关连接
	 */
	protected static void closeAll(){
		if (rs != null) {
            try {
                
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
