package mpcs.handler;

import mpcs.config.GlobalConst;
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
	
	private UserVO vo = null;
	
	public void doWrite(Request request, Response response) throws Exception {
		int command = request.getCommand();
        if (command == GlobalConst.C_BIND_MOBILE_LIST) {// 请求绑定手机列表
        	vo = new UserVO();
        	vo.setEmail(request.getPacket().readString());
        	vo.setPhones(ExeSQL.selectPhoneVOs(vo.getEmail()));
        	if (vo.getPhones().size() == 0) {
        		// 列表为空
        		BaseCmd cmd = new BaseCmd(GlobalConst.S_BIND_MOBILE_LIST);
        		cmd.writeInt(0);
        		response.send(cmd);
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
        }else if (command == GlobalConst.C_ADD_BIND_MOBILE) {// 请求绑定手机
			
		}
	}
	
}
