package mpcs.model;

import mpcs.vo.UserVO;

/**
 * <p>Title: 返回客户端 用户基本信息</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-14
 */
public class BasicInfoCmd extends BaseCmd {

	/**序列化成字符串，隐藏类的字段，实现加密功能**/
	private static final long serialVersionUID = 1L;
	
	/**
	 * 返回客户端  用户联系信息 类型  构造方法
	 * @param typeID*  消息号
	 */
	public BasicInfoCmd(int typeID, UserVO vo) {
		super(typeID);
		writeBody(vo);
	}
	
	/**
	 * 发送错误消息
	 * @param typeID
	 * @param head2
	 */
	public BasicInfoCmd(int typeID, int head2){
		super(typeID, head2);
	}
	
	/**
	 * 写入BasicInfoVO消息体
	 * @param vo
	 */
	private void writeBody(UserVO vo){
		packet.writeString(vo.getEmail());
		packet.writeString(vo.getBasicInfo().getUserName());
		packet.writeString(vo.getBasicInfo().getGender());
		packet.writeString(vo.getBasicInfo().getBirthday());
		packet.writeString(vo.getBasicInfo().getBloodType());
		packet.writeString(vo.getBasicInfo().getMarriage());
		packet.writeString(vo.getBasicInfo().getCareer());
		packet.writeString(vo.getBasicInfo().getEducation());
		packet.writeString(vo.getBasicInfo().getResidence());
		packet.writeString(vo.getBasicInfo().getHome());
		packet.writeString(vo.getBasicInfo().getIdCard());
	}

}
