package bean;

public class GoodsBean {
	int gid;
	String gname;
	float gprice;
	String gintroduce;
	
	public GoodsBean(int gid, String gname, float gprice, String gintroduce) {
		super();
		this.gid = gid;
		this.gname = gname;
		this.gprice = gprice;
		this.gintroduce = gintroduce;
	}

	public int getGid() {
		return gid;
	}
	
	public void setGid(int gid) {
		this.gid = gid;
	}
	
	public String getGname() {
		return gname;
	}
	
	public void setGname(String gname) {
		this.gname = gname;
	}
	
	public float getGprice() {
		return gprice;
	}
	
	public void setGprice(float gprice) {
		this.gprice = gprice;
	}
	
	public String getGintroduce() {
		return gintroduce;
	}
	
	public void setGintroduce(String gintroduce) {
		this.gintroduce = gintroduce;
	}
}
