package nio.data;

import nio.interfaces.ICmd;

/**
 * <p>Title: 封装读操作中的Handler以及对应返回消息号</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-10
 */
public class TempVO {
	private int returnID;
	private ICmd iCmd;
	
	public TempVO(int returnID, ICmd iCmd){
		this.returnID = returnID;
		this.iCmd = iCmd;
	}
	
	public int getReturnID() {
		return returnID;
	}
	public void setReturnID(int returnID) {
		this.returnID = returnID;
	}
	public ICmd getICmd() {
		return iCmd;
	}
	public void setICmd(ICmd iCmd) {
		this.iCmd = iCmd;
	}
}
