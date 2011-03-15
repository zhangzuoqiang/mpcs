package mpcs.model;

import mpcs.vo.UserVO;

/**
 * <p>Title: 返回客户端 用户联系信息</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-15
 */
public class ContactInfoCmd extends BaseCmd {

	/**序列化成字符串，隐藏类的字段，实现加密功能**/
	private static final long serialVersionUID = 1L;

	public ContactInfoCmd(int typeID, UserVO vo) {
		super(typeID);
		writeBody(vo);
	}
	
	/**
	 * 发送错误消息
	 * @param typeID
	 * @param head2
	 */
	public ContactInfoCmd(int typeID, int head2){
		super(typeID, head2);
	}
	
	/**
	 * 写入ContactInfoVO消息体
	 * @param vo
	 */
	private void writeBody(UserVO vo){
		packet.writeString(vo.getContactInfo().getQQ());
		packet.writeString(vo.getContactInfo().getMsn());
		packet.writeString(vo.getContactInfo().getMobile());
		packet.writeString(vo.getContactInfo().getTel());
		packet.writeString(vo.getContactInfo().getZip());
	}

}
