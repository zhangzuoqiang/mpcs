package mpcs.db2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import nio.config.Debug;

import mpcs.utils.MoreUtils;

/**
 * <p>Title: c3p0测试程序</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-8
 */
public class C3P0Test {
	
	public static void main(String[] args) {
		
        DBConnManager cm = DBConnManager.getInstance();
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        String sql = "select * from users";
        
        try {
            conn = cm.getConnection();
            System.out.println(conn);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
            	MoreUtils.trace("uid: " + rs.getString(1) + "  email: " + rs.getString(2).trim() + 
            			"  password: " + rs.getString(3),Debug.printTestInfo);
			}
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
