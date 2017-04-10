package bean;

public class UserBean {
	String uid;
	String upsd;
	String uname;
	
	public UserBean(String uid, String upsd, String uname) {
		super();
		this.uid = uid;
		this.upsd = upsd;
		this.uname = uname;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUpsd() {
		return upsd;
	}

	public void setUpsd(String upsd) {
		this.upsd = upsd;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
}
