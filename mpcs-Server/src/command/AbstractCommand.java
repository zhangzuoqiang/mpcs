package command;

import java.io.Serializable;

/**
 * socket消息抽象类
 * <br/>socket消息结构:
 * <br/>消息头 h1 h2 h3各占2字节
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public abstract class AbstractCommand implements Serializable {
	
	/****/
	private static final long serialVersionUID = 1L;
	
	private short head1;
	private short head2;
	private short head3;
	
	public AbstractCommand(){
	}
	
	public AbstractCommand(short h1, short h2, short h3){
		this.head1 = h1;
		this.head2 = h2;
		this.head3 = h3;
	}
	
	public short getHead1() {
		return head1;
	}
	public void setHead1(short head1) {
		this.head1 = head1;
	}
	public short getHead2() {
		return head2;
	}
	public void setHead2(short head2) {
		this.head2 = head2;
	}
	public short getHead3() {
		return head3;
	}
	public void setHead3(short head3) {
		this.head3 = head3;
	}
}
