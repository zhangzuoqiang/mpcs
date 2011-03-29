package mpcs.vo;

/**
 * <p>Title: 用户联系信息实体类</p>
 * <p>Description: </p>
 * @author zhangzuoqiang
 * <br/>Date: 2011-3-15
 */
public class ContactInfoVO {
	
	private String qq;
	private String msn;
	private String mobile;
	private String tel;
	private String zip;
	
	@Override
	public String toString() {
		return this.getQQ() + " " + this.getMsn() + " " + this.getMobile() 
					+ " " + this.getTel() + " " + this.getZip();
	}
	public String getQQ() {
		if (this.qq == null) {
			this.qq = "";
		}
		return this.qq;
	}
	public void setQQ(String qq) {
		if (qq == null) {
			this.qq = "";
			return;
		}
		this.qq = qq.trim();
	}
	public String getMsn() {
		if (this.msn == null) {
			this.msn = "";
		}
		return this.msn;
	}
	public void setMsn(String msn) {
		if (msn == null) {
			this.msn = "";
			return;
		}
		this.msn = msn.trim();
	}
	public String getMobile() {
		if (this.mobile == null) {
			this.mobile = "";
		}
		return this.mobile;
	}
	public void setMobile(String mobile) {
		if (mobile == null) {
			this.mobile = "";
			return;
		}
		this.mobile = mobile.trim();
	}
	public String getTel() {
		if (this.tel == null) {
			this.tel = "";
		}
		return this.tel;
	}
	public void setTel(String tel) {
		if (tel == null) {
			this.tel = "";
			return;
		}
		this.tel = tel.trim();
	}
	public String getZip() {
		if (this.zip == null) {
			this.zip = "";
		}
		return this.zip;
	}
	public void setZip(String zip) {
		if (zip == null) {
			this.zip = "";
			return;
		}
		this.zip = zip.trim();
	}

}
