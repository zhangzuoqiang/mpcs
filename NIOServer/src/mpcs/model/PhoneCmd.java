package mpcs.model;

import mpcs.vo.PhoneVO;

/**
 * <p>Title: 返回客户端 用户联系信息</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-16
 */
public class PhoneCmd extends BaseCmd {

	/**序列化成字符串，隐藏类的字段，实现加密功能**/
	private static final long serialVersionUID = 1L;

	public PhoneCmd(int typeID, PhoneVO vo) {
		super(typeID);
		writeBody(vo);
	}
	
	/**
	 * 发送错误消息
	 * @param typeID
	 * @param head2
	 */
	public PhoneCmd(int typeID, int head2){
		super(typeID, head2);
	}
	
	/**
	 * 写入PhoneVO消息体
	 * @param vo
	 */
	private void writeBody(PhoneVO vo){
		packet.writeString(vo.getPhoneID());
		packet.writeString(vo.getRelationship());
		packet.writeString(vo.getStatus());
		packet.writeString(vo.getBelongto());
		packet.writeString(vo.getBegintime());
		packet.writeString(vo.getType());
		packet.writeString(vo.getLongitude());
		packet.writeString(vo.getLatitude());
		packet.writeString(vo.getRadius());
	}

}
