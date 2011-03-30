package mpcs.db2;

import java.util.ArrayList;

import nio.config.Debug;
import mpcs.utils.MoreUtils;
import mpcs.vo.PhoneVO;
import mpcs.vo.UserVO;

/**
 * <p>Title: SQL语句测试</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: Mar 28, 2011
 */
public class SQLangsTest {
	
	private static UserVO vo = null;
	
	public static void main(String[] args) {
		
		
		
		MoreUtils.trace(ExeSQL.updateTipByEmail("csdn.eric@gmail.com", "0"), Debug.printSystem);
		
		MoreUtils.trace(ExeSQL.updateAccountByEmail("csdn.eric@gmail.com", "400.00"), Debug.printSystem);
		
		MoreUtils.trace(ExeSQL.selectAccountByEmail("csdn.eric@gmail.com"), Debug.printSystem);
		
		/*vo = new UserVO();
		PhoneVO ph = new PhoneVO();
		ph.setPhoneID("13808088888");
		ph.setRelationship("家人");
		ph.setStatus("0");
		ph.setType("200000");
		ph.setBelongto("广东");
		ph.setBegintime("2011-04-01");
		ph.setRadius("500.00");
		ph.setLongitude("110.23");
		ph.setLatitude("23.15");
		ArrayList<PhoneVO> phones = new ArrayList<PhoneVO>();
		phones.add(ph);
		vo.setEmail("csdn.eric@gmail.com");
		vo.setPhones(phones);
		MoreUtils.trace(ExeSQL.addPhoneVO(vo), Debug.printSystem);*/
		
		ArrayList<PhoneVO> phoneVOs = ExeSQL.selectPhoneVOs("csdn.eric@gmail.com");
		for (PhoneVO phone : phoneVOs) {
			MoreUtils.trace(phone.toString(), Debug.printSystem);
		}
		
		vo = new UserVO();
		vo.setEmail("csdn.eric@gmail.com");
		vo.getContactInfo().setQQ("200844306");
		vo.getContactInfo().setMobile("15876995100");
		vo.getContactInfo().setMsn("csdn_eric@hotmail.com");
		vo.getContactInfo().setTel("0769-");
		vo.getContactInfo().setZip("523000");
		MoreUtils.trace(ExeSQL.saveContactInfo(vo), Debug.printSystem);
		
		vo = new UserVO();
		vo.setEmail("csdn.eric@gmail.com");
		vo.getBasicInfo().setUserName("张作强");
		vo.getBasicInfo().setGender("男");
		vo.getBasicInfo().setBirthday("1988-03-14");
		vo.getBasicInfo().setBloodType("2");
		vo.getBasicInfo().setMarriage("1");
		vo.getBasicInfo().setCareer("软件工程师");
		vo.getBasicInfo().setEducation("3");
		vo.getBasicInfo().setResidence("广东省东莞市松山湖理工学院");
		vo.getBasicInfo().setHome("山东省-诸城市");
		vo.getBasicInfo().setIdCard("370782198803142495");
		MoreUtils.trace(ExeSQL.saveBasicInfo(vo), Debug.printSystem);
		
		MoreUtils.trace(ExeSQL.updatePwd("csdn.eric@gmail.com", "123"), Debug.printSystem);
		
		vo = ExeSQL.selectContactInfoByEmail("csdn.eric@gmail.com");
		MoreUtils.trace(vo.toString(), Debug.printSystem);
		
		vo = ExeSQL.selectBasicInfoByEmail("csdn.eric@gmail.com");
		MoreUtils.trace(vo.toString(), Debug.printSystem);
		
		MoreUtils.trace(ExeSQL.selectByEmail("csdn.eric@gmail.com"), Debug.printSystem);
		
//		MoreUtils.trace(ExeSQL.addUser("z.zuoqiang@gmail.com", "159"), Debug.printSystem);
		
		MoreUtils.trace(ExeSQL.selectPwdByEmail("csdn.eric@gmail.com"), Debug.printSystem);
	}

}
