package mpcs.model;

/**
 * <p>Title: 客户端请求 基本数据类型</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-13
 */
public class BaseMsg extends BaseCmd{

	/**序列化成字符串，隐藏类的字段，实现加密功能**/
	private static final long serialVersionUID = 1L;
	
	public BaseMsg(int head0, int head1, int head2){
		super(head0);
		head[1] = head1;
		head[2] = head2;
	}
	
	public BaseMsg(int typeID){
		super(typeID);
	}
	
	public Object getBodys() {
		return null;
	}
}
