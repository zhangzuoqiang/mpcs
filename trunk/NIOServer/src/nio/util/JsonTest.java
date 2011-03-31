package nio.util;

import mpcs.utils.MoreUtils;
import mpcs.vo.UserVO;

/**
 * <p>Title: Json工具的测试类</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-30
 */
public class JsonTest {
	
	public static void main(String[] args) {
		
		UserVO vo = new UserVO();
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
		vo.setEmail("csdn.eric@gmail.com");
		vo.getContactInfo().setQQ("200844306");
		vo.getContactInfo().setMobile("15876995100");
		vo.getContactInfo().setMsn("csdn_eric@hotmail.com");
		vo.getContactInfo().setTel("0769-");
		vo.getContactInfo().setZip("523000");
		
		String jsonStr = JsonUtil.object2JSON(vo);
		
		MoreUtils.trace(jsonStr, true);
	}

}
