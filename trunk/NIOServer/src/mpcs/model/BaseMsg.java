package mpcs.model;

import mpcs.interfaces.IMsg;

/**
 * <p>Title: 客户端请求 基本数据类型</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-12
 */
public class BaseMsg implements IMsg {

	/****/
	private static final long serialVersionUID = 1L;

	@Override
	public int getHeadData(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTypeID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getBodys() {
		// TODO Auto-generated method stub
		return null;
	}

}
