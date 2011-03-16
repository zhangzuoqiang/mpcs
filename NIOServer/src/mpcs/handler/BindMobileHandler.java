package mpcs.handler;

import mpcs.config.GlobalConst;
import mpcs.config.GlobalErrorConst;
import mpcs.db.ExeSQL;
import mpcs.model.BaseCmd;
import mpcs.vo.PhoneVO;
import mpcs.vo.UserVO;
import nio.core.Request;
import nio.core.Response;
import nio.manager.ListenAdapter;

/**
 * <p>Title: 请求绑定手机列表，添加绑定手机事件处理</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-13
 */
public class BindMobileHandler extends ListenAdapter {
	
	public void doWrite(Request request, Response response) throws Exception {
		int command = request.getCommand();
        if (command == GlobalConst.C_BIND_MOBILE_LIST) {// 请求绑定手机列表
        	UserVO vo = new UserVO();
        	vo.setEmail(request.getPacket().readString());
        	vo.setPhones(ExeSQL.selectPhoneVOs(vo.getEmail()));
        	if (vo.getPhones().size() == 0) {
        		// 列表为空
        		BaseCmd cmd = new BaseCmd(GlobalConst.S_BIND_MOBILE_LIST);
        		cmd.writeInt(0);
        		response.send(cmd);
        		return;
			}else {
				// 列表不为空
				BaseCmd cmd = new BaseCmd(GlobalConst.S_BIND_MOBILE_LIST);
        		cmd.writeInt(vo.getPhones().size());
        		for (int i = 0; i < vo.getPhones().size(); i++) {
					PhoneVO phoneVO = vo.getPhones().get(i);
					cmd.writeString(phoneVO.getPhoneID());
					cmd.writeString(phoneVO.getRelationship());
					cmd.writeString(phoneVO.getStatus());
					cmd.writeString(phoneVO.getBelongto());
					cmd.writeString(phoneVO.getBegintime());
					cmd.writeString(phoneVO.getType());
					cmd.writeString(phoneVO.getLongitude());
					cmd.writeString(phoneVO.getLatitude());
					cmd.writeString(phoneVO.getRadius());
				}
        		response.send(cmd);
			}
        	return;
        }else if (command == GlobalConst.C_ADD_BIND_MOBILE) {// 添加绑定手机
			PhoneVO phone = new PhoneVO();
			phone.setPhoneID(request.getPacket().readString());
			phone.setRelationship(request.getPacket().readString());
			phone.setStatus(request.getPacket().readString());
			phone.setBelongto(request.getPacket().readString());
			phone.setBegintime(request.getPacket().readString());
			phone.setType(request.getPacket().readString());
			phone.setLongitude(request.getPacket().readString());
			phone.setLatitude(request.getPacket().readString());
			phone.setRadius(request.getPacket().readString());
			
			if (addBindPhone(phone) == 0) {
				// 添加绑定成功
				BaseCmd cmd = new BaseCmd(GlobalConst.S_ADD_BIND_MOBILE);
				
				response.send(cmd);
				return;
			}else if (addBindPhone(phone) == 1){ 
				// 添加绑定失败,执行数据库操作错误
				BaseCmd cmd = new BaseCmd(GlobalConst.S_ADD_BIND_MOBILE, GlobalErrorConst.E_BIND_MOBILE_OVER_FLOW);
				
				response.send(cmd);
				return;
			}else if (addBindPhone(phone) == 2) {
				// 添加绑定失败,该手机已存在
				BaseCmd cmd = new BaseCmd(GlobalConst.S_ADD_BIND_MOBILE, GlobalErrorConst.E_BIND_MOBILE_EXIST);
				
				response.send(cmd);
			}
		}
	}
	
	private int addBindPhone(PhoneVO phone){
		
		return 0;
	}
	
}
