package mpcs.libs.cmds;

import java.io.Serializable;

/**
 * socket 协议抽象类
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-6
 */
public class AbstractCmd implements Serializable {
	
	/****/
	private static final long serialVersionUID = 1L;
	
	private int head1;
	private int head2;
	private int head3;
	
	public AbstractCmd(){
	}
	
	public AbstractCmd(short h1, short h2, short h3){
		this.head1 = h1;
		this.head2 = h2;
		this.head3 = h3;
	}

	public int getHead1() {
		return head1;
	}
	public void setHead1(int head1) {
		this.head1 = head1;
	}
	public int getHead2() {
		return head2;
	}
	public void setHead2(int head2) {
		this.head2 = head2;
	}
	public int getHead3() {
		return head3;
	}
	public void setHead3(int head3) {
		this.head3 = head3;
	}
}
