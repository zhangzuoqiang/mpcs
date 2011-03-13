package mpcs.db;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

/**
 * 数据库连接类,Singleton 模式
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-7
 */
public class DBConnect {	
	private static DBConnect instance;
	private static ComboPooledDataSource ds = null;
	private static Connection con = null;
	
	private DBConnect(){
		try {
			ds = new ComboPooledDataSource();
			
			ds.setDriverClass("com.mysql.jdbc.Driver"); 
			ds.setJdbcUrl("jdbc:mysql://localhost:3306/mpcs?userUnicode=true&amp;characterEncoding=gbk");
			ds.setUser("root");
			ds.setPassword("zzq");
			
			ds.setInitialPoolSize(30);
			ds.setMaxPoolSize(200);
			ds.setMinPoolSize(20);
			ds.setAcquireIncrement(1);
			ds.setIdleConnectionTestPeriod(60);
			ds.setMaxIdleTime(25000);
			ds.setAutoCommitOnClose(true);
			ds.setTestConnectionOnCheckin(true);
			ds.setAcquireRetryAttempts(30);
			ds.setAcquireRetryDelay(1000);
			
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * 获取连接管理类 实例
	 * @return
	 */
	public static final DBConnect getInstance(){
		if (instance == null) {
			try {
				instance = new DBConnect();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	/**
	 * 获取连接
	 * @return
	 */
    public synchronized final Connection getConnection() {
        try {
            con = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    
    /**
     * 关闭datasource
     */
    protected void finalize() throws Throwable{
		DataSources.destroy(ds);
		super.finalize();
	}
    
}