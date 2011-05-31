package mpcs.handler;

import java.util.ArrayList;

import mpcs.config.GlobalConst;
import mpcs.config.GlobalErrorConst;
import mpcs.db2.ExeSQL;
import mpcs.model.BaseCmd;
import mpcs.utils.JSONUtil;
import mpcs.vo.PhoneVO;
import mpcs.vo.UserVO;
import nio.core.Request;
import nio.core.Response;
import nio.manager.ListenAdapter;
import nio.util.LangUtil;

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
				// 写入列表大小
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
        }else if (command == GlobalConst.C_ADD_BIND_MOBILE) {
        	// 添加绑定手机
        	UserVO vo = new UserVO();
        	vo.setEmail(request.getPacket().readString());
        	ArrayList<PhoneVO> phones = new ArrayList<PhoneVO>();
			PhoneVO phone = new PhoneVO();
			phone.setPhoneID(request.getPacket().readString());
			phone.setRelationship(request.getPacket().readString());
			phone.setStatus(request.getPacket().readString());
			phone.setBelongto(request.getPacket().readString());
			phone.setBegintime(request.getPacket().readString());//////////////这里可以根据需要修改为服务端时间
			phone.setType(request.getPacket().readString());
			phone.setLongitude(request.getPacket().readString());
			phone.setLatitude(request.getPacket().readString());
			phone.setRadius(request.getPacket().readString());
			phones.add(phone);
			vo.setPhones(phones);
			
			if (addBindPhone(vo) == 0) {
				// 添加绑定成功
				BaseCmd cmd = new BaseCmd(GlobalConst.S_ADD_BIND_MOBILE);
				cmd.writeString(LangUtil.get("20017"));
				response.send(cmd);
				return;
			}else if (addBindPhone(vo) == 1){ 
				// 添加绑定失败,超出所能绑定的手机号码数目
				BaseCmd cmd = new BaseCmd(GlobalConst.S_ADD_BIND_MOBILE, GlobalErrorConst.E_BIND_MOBILE_OVER_FLOW);
				cmd.writeString(LangUtil.get("20014"));
				response.send(cmd);
				return;
			}else if (addBindPhone(vo) == 2) {
				// 添加绑定失败,该手机已存在
				BaseCmd cmd = new BaseCmd(GlobalConst.S_ADD_BIND_MOBILE, GlobalErrorConst.E_BIND_MOBILE_EXIST);
				cmd.writeString(LangUtil.get("20015"));
				response.send(cmd);
			}else if (addBindPhone(vo) == 3) {
				// 账户余额不足
				BaseCmd cmd = new BaseCmd(GlobalConst.S_ADD_BIND_MOBILE, GlobalErrorConst.E_NO_ENOUGH_MONEY);
				cmd.writeString(LangUtil.get("20016"));
				response.send(cmd);
			}else {
				// 执行添加绑定手机时出错
				BaseCmd cmd = new BaseCmd(GlobalConst.S_ADD_BIND_MOBILE, GlobalErrorConst.E_BIND_MOBILE);
				cmd.writeString(LangUtil.get("20018"));
				response.send(cmd);
			}
		}
	}
	
	private int addBindPhone(UserVO vo){
		int nums = JSONUtil.getVipItem(vo.getPhones().get(0).getType(), "phones");
		// 超出绑定手机的数目
		if (ExeSQL.selectPhoneVOs(vo.getEmail()).size() >= nums) {
			return 1;
		}else if(isExistPhone(vo, vo.getPhones().get(0).getPhoneID())) {// 该手机已经绑定
			return 2;
		}else if(isEnoughMoney(vo)) {// 账户余额不足
			return 3;
		}else if (!ExeSQL.addPhoneVO(vo)) {
			// 执行账户更改
			ExeSQL.updateAccountByEmail(vo.getEmail(), curAccount + "");
			return 0;
		}
		return 4;
	}
	
	/**
	 * 检查是否已经存在该绑定手机
	 * @param vo
	 * @param phoneID
	 * @return
	 */
	private boolean isExistPhone(UserVO vo, String phoneID){
		ArrayList<PhoneVO> phoneVOs = ExeSQL.selectPhoneVOs(vo.getEmail());
		boolean flag = false;
		for (PhoneVO phoneVO : phoneVOs) {
			if (phoneVO.getPhoneID().equals(phoneID)) {
				flag = true;
				break;
			}
		}		
		return flag;
	}
	
	private double curAccount = 0;
	/**
	 * 检查账户余额是否不足
	 * @param vo
	 * @return
	 */
	private boolean isEnoughMoney(UserVO vo){
		// 查询账户可用余额
		double account = ExeSQL.selectAccountByEmail(vo.getEmail());
		double cost = JSONUtil.getVipItem(vo.getPhones().get(0).getType(), "cost");
		curAccount = account - cost;
		if (curAccount >= 0) {
			return false;
		}
		return true;
	}
	
}
