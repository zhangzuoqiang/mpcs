package mpcs.handler;

import mpcs.config.GlobalConst;
import mpcs.config.GlobalErrorConst;
import mpcs.db2.ExeSQL;
import mpcs.model.BaseCmd;
import mpcs.model.ContactInfoCmd;
import mpcs.vo.UserVO;
import nio.core.Request;
import nio.core.Response;
import nio.manager.ListenAdapter;
import nio.util.LangUtil;

/**
 * <p>Title: 请求用户联系信息，保存用户联系信息事件处理</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-15
 */
public class ContactInfoHandler extends ListenAdapter {
	
	public void doWrite(Request request, Response response) throws Exception {
		int command = request.getCommand();
		UserVO vo = new UserVO();
        if (command == GlobalConst.C_USER_CONTACT_INFO) {// 请求用户联系信息
        	// 读取email
        	vo.setEmail(request.getPacket().readString());
        	// 根据email查询数据库，然后构建ContactInfoVO
        	vo = ExeSQL.selectContactInfoByEmail(vo.getEmail());
        	if (vo != null) {
        		ContactInfoCmd cmd = new ContactInfoCmd(GlobalConst.S_USER_CONTACT_INFO, vo);
            	response.send(cmd);
            	return;
			}
        	return;
        }else if(command == GlobalConst.C_USER_SAVE_CONTACT_INFO){// 保存用户联系信息
        	vo.setEmail(request.getPacket().readString());
			vo.getContactInfo().setQQ(request.getPacket().readString());
			vo.getContactInfo().setMsn(request.getPacket().readString());
			vo.getContactInfo().setMobile(request.getPacket().readString());
			vo.getContactInfo().setTel(request.getPacket().readString());
			vo.getContactInfo().setZip(request.getPacket().readString());
			if (saveContactInfo(vo)) {
				BaseCmd cmd = new BaseCmd(GlobalConst.S_USER_SAVE_CONTACT_INFO);
				cmd.writeString(LangUtil.get("20012"));
				response.send(cmd);
				return;
			}else {
				BaseCmd cmd = new BaseCmd(GlobalConst.S_USER_SAVE_CONTACT_INFO, GlobalErrorConst.E_SAVE_USER_CONTACT_INFO);
				cmd.writeString(LangUtil.get("20013"));
				response.send(cmd);
			}
		}
	}
	
	/**
	 * 保存用户联系信息
	 * @param vo
	 * @return
	 */
	private boolean saveContactInfo(UserVO vo){
		if (!ExeSQL.saveContactInfo(vo)) {
			return true;
		}
		return false;
	}
}
