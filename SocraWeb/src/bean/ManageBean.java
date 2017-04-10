package bean;

public class ManageBean {
	String mid;
	String mpsd;
	String mname;
	
	public ManageBean(String mid, String mpsd, String mname) {
		super();
		this.mid = mid;
		this.mpsd = mpsd;
		this.mname = mname;
	}
	
	public String getMid() {
		return mid;
	}
	
	public void setMid(String mid) {
		this.mid = mid;
	}
	
	public String getMpsd() {
		return mpsd;
	}
	
	public void setMpsd(String mpsd) {
		this.mpsd = mpsd;
	}
	
	public String getMname() {
		return mname;
	}
	
	public void setMname(String mname) {
		this.mname = mname;
	}
	
}
