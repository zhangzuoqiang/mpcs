package mpcs.handler;

import java.util.ArrayList;

import mpcs.config.GlobalConst;
import mpcs.db2.ExeSQL;
import mpcs.model.BaseCmd;
import mpcs.vo.PhoneVO;
import mpcs.vo.PositionVO;
import nio.core.Request;
import nio.core.Response;
import nio.manager.ListenAdapter;

/**
 * <p>Title: 请求轨迹列表事件处理</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-13
 */
public class PositionListHandler extends ListenAdapter {
	
	private ArrayList<PositionVO> positionVOs = null;
	
	public void doWrite(Request request, Response response) throws Exception {
		int command = request.getCommand();
        if (command == GlobalConst.C_POSITION_LIST) {// 请求历史位置列表
        	PhoneVO vo = new PhoneVO();
        	vo.setPhoneID(request.getPacket().readString());// 消息体第一位为手机号码
        	// 消息体第二位，为以后扩展增加时间限定的条件，在此不做处理
        	System.out.println( "PositionListHandler : " + request.getPacket().readString() );
        	//当前列表为空
        	if( poSize(vo.getPhoneID()) == 0 ){
        		// 列表为空
        		BaseCmd cmd = new BaseCmd(GlobalConst.S_POSITION_LIST);
        		cmd.writeInt(0);
        		response.send(cmd);
        		return;
        	}else {
        		// 列表不为空
				BaseCmd cmd = new BaseCmd(GlobalConst.S_POSITION_LIST);
				// 写入列表大小
        		cmd.writeInt(positionVOs.size());
        		// 此处要传输的数据量比较大，所以要设定数据缓冲区 2<<45
        		for (int i = 0; i < positionVOs.size(); i++) {
					PositionVO posVO = positionVOs.get(i);
					cmd.writeString(posVO.getDate());
					cmd.writeFloat(posVO.getLongitude());
					cmd.writeFloat(posVO.getLatitude());
					cmd.writeFloat(posVO.getAccuracy());
				}
        		response.send(cmd);
			}
        }
	}
	
	/**
	 * 获取历史数据的大小
	 * @param phone
	 * @return
	 */
	private int poSize(String phone){
		positionVOs = ExeSQL.selectPositionVOs(phone);
		int size = positionVOs.size();
		return size;
	}
}