package mpcs.handler;

import mpcs.config.GlobalConst;
import mpcs.config.GlobalErrorConst;
import mpcs.db.ExeSQL;
import mpcs.model.BaseCmd;
import mpcs.model.BasicInfoCmd;
import mpcs.vo.UserVO;
import nio.core.Request;
import nio.core.Response;
import nio.manager.ListenAdapter;
import nio.util.LangUtil;

/**
 * <p>Title: 请求用户基本信息，保存用户基本信息事件处理</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-14
 */
public class BaseInfoHandler extends ListenAdapter {
	
	public void doWrite(Request request, Response response) throws Exception {
		int command = request.getCommand();
		UserVO vo = null;
        if (command == GlobalConst.C_USER_BASIC_INFO) {// 请求用户基本信息
        	// 读取email
        	String email = request.getPacket().readString();
        	// 根据email查询数据库，然后构建BasicInfoVO
        	vo = ExeSQL.selectBasicInfoByEmail(email);
        	if (vo != null) {
        		BasicInfoCmd cmd = new BasicInfoCmd(GlobalConst.S_USER_BASIC_INFO, vo);
            	response.send(cmd);
            	return;
			}
        	return;
        }else if(command == GlobalConst.C_USER_SAVE_BASIC_INFO){// 保存用户基本信息
        	vo = new UserVO();
        	vo.setEmail(request.getPacket().readString());
			vo.getBasicInfo().setUserName(request.getPacket().readString());
			vo.getBasicInfo().setGender(request.getPacket().readString());
			vo.getBasicInfo().setBirthday(request.getPacket().readString());
			vo.getBasicInfo().setBloodType(request.getPacket().readString());
			vo.getBasicInfo().setMarriage(request.getPacket().readString());
			vo.getBasicInfo().setCareer(request.getPacket().readString());
			vo.getBasicInfo().setEducation(request.getPacket().readString());
			vo.getBasicInfo().setResidence(request.getPacket().readString());
			vo.getBasicInfo().setHome(request.getPacket().readString());
			vo.getBasicInfo().setIdCard(request.getPacket().readString());
			if (saveBasicInfo(vo)) {
				BaseCmd cmd = new BaseCmd(GlobalConst.S_USER_SAVE_BASIC_INFO);
				cmd.writeString(LangUtil.get("20010"));
				response.send(cmd);
				return;
			}else {
				BaseCmd cmd = new BaseCmd(GlobalConst.S_USER_SAVE_BASIC_INFO, GlobalErrorConst.E_SAVE_USER_BASIC_INFO);
				cmd.writeString(LangUtil.get("20011"));
				response.send(cmd);
			}
		}
	}
	
	/**
	 * 保存用户基本信息
	 * @param vo
	 * @return
	 */
	private boolean saveBasicInfo(UserVO vo){
		if (!ExeSQL.saveBasicInfo(vo)) {
			return true;
		}
		return false;
	}
}
