package mpcs.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import mpcs.utils.MoreUtils;

/**
 * c3p0测试程序
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public class C3P0Test {
	
	public static void main(String[] args) {
		
        ConnectionManager cm = ConnectionManager.getInstance();
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        String sql = "select email from user where email = 'csdn.eric@gmail.com'";
        
        try {
            conn = cm.getConnection();
            System.out.println(conn);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            MoreUtils.trace(rs.getString(1));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
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
}
