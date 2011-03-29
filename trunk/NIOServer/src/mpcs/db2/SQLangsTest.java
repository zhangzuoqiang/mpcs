package mpcs.db2;

import nio.config.Debug;
import mpcs.utils.MoreUtils;
import mpcs.vo.UserVO;

/**
 * <p>Title: SQL语句测试</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: Mar 28, 2011
 */
public class SQLangsTest {
	
	public static void main(String[] args) {
		
		UserVO vo = ExeSQL.selectBasicInfoByEmail("csdn.eric@gmail.com");
		MoreUtils.trace(vo.toString(), Debug.printSystem);
		MoreUtils.trace(ExeSQL.selectByEmail("csdn.eric@gmail.com"), Debug.printSystem);
//		MoreUtils.trace(ExeSQL.addUser("z.zuoqiang@gmail.com", "159"), Debug.printSystem);
		MoreUtils.trace(ExeSQL.selectPwdByEmail("csdn.eric@gmail.com"), Debug.printSystem);
	}

}
