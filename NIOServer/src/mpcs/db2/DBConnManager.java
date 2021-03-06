package mpcs.db2;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import nio.config.Debug;
import nio.util.LangUtil;

import mpcs.utils.MoreUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

/**
 * <p>Title: 数据库连接类,Singleton 模式</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-8
 */
public class DBConnManager {
	
	private static DBConnManager instance;
	private static ComboPooledDataSource ds = null;
	private static Connection con = null;
	
	private DBConnManager(){
		try {
			ds = new ComboPooledDataSource();
			
			// 配置连接数据库参数
			ds.setDriverClass("com.ibm.db2.jcc.DB2Driver"); 
			ds.setJdbcUrl("jdbc:db2://localhost:50000/MPCS:retrieveMessagesFromServerOnGetMessage=true;");
			ds.setUser("lenovo");
			ds.setPassword("zzq");
			
			// 初始化时获取三个连接，取值应在minPoolSize与maxPoolSize之间
			ds.setInitialPoolSize(3);
			// 连接池中保留的最大连接数
			ds.setMaxPoolSize(15);
			// 
			ds.setMinPoolSize(10);
			// 当连接池中的连接耗尽的时候c3p0一次同时获取的连接数
			ds.setAcquireIncrement(3);
			// 每60秒检查所有连接池中的空闲连接
			ds.setIdleConnectionTestPeriod(30);
			// 最大空闲时间,60秒内未使用则连接被丢弃。若为0则永不丢弃
			ds.setMaxIdleTime(30);
			// 
			ds.setMaxStatements(100);			
			// 连接关闭时默认将所有未提交的操作回滚
			ds.setAutoCommitOnClose(true);
			// 在取得连接的同时将校验连接的有效性
			ds.setTestConnectionOnCheckin(true);
			// 定义在从数据库获取新连接失败后重复尝试的次数
			ds.setAcquireRetryAttempts(30);
			// 两次连接中间隔时间，单位毫秒
			ds.setAcquireRetryDelay(1000);
			// c3p0是异步操作的，缓慢的JDBC操作通过帮助进程完成。扩展这些操作可以有效的提升性能
			// 通过多线程实现多个操作同时被执行
			ds.setNumHelperThreads(50);
			// 
			ds.setCheckoutTimeout(0);
			
		} catch (PropertyVetoException e) {
			if (Debug.printException) {
				e.printStackTrace();
			}
		}
    }
	
	/**
	 * 获取连接管理类 实例
	 * @return
	 */
	public static final DBConnManager getInstance(){
		if (instance == null) {
			try {
				instance = new DBConnManager();
			} catch (Exception e) {
				if (Debug.printException) {
					e.printStackTrace();
				}
				MoreUtils.trace(LangUtil.get("10007"), Debug.printException);
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
        	if (Debug.printException) {
				e.printStackTrace();
			}
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