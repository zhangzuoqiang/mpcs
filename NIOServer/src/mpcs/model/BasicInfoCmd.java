package mpcs.model;

import mpcs.vo.BasicInfoVO;

public class BasicInfoCmd extends BaseCmd {

	/****/
	private static final long serialVersionUID = 1L;
	
	/**
	 * 返回客户端  用户联系信息 类型  构造方法
	 * @param typeID*  消息号
	 */
	public BasicInfoCmd(int typeID, BasicInfoVO vo) {
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
	private void writeBody(BasicInfoVO vo){
		packet.writeString(vo.getEmail());
		packet.writeString(vo.getUserName());
		packet.writeString(vo.getGender());
		packet.writeString(vo.getBirthday());
		packet.writeString(vo.getBloodType());
		packet.writeString(vo.getMarriage());
		packet.writeString(vo.getCareer());
		packet.writeString(vo.getEducation());
		packet.writeString(vo.getResidence());
		packet.writeString(vo.getHome());
		packet.writeString(vo.getIdCard());
	}

}
