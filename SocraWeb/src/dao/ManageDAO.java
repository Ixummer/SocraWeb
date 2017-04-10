package dao;
/**
 * 1.Class.forName("oracle.jdbc.driver.OracleDriver").newInstance(); 
���ݿ�����ֻ�����һ�Σ�һ���װDAO��д�ھ�̬�����档 

2.����һ��SQL�����������ݿ��Connection����Ҫ�ر��� ���Բ��رս��ŷ���һ��SQL��
һ����������ݿ���Ҫ��ʱ���ͷ���Դ������ִ�м���SQL�������⣬����дһ���������ؼ���ʹ����Ҫ�رգ�
����ִ����executeUpdate()�����߱�����ResultSet�������ʱ�����Ҫ�رա�

3.���ݿ��еĲ�ѯ�����Ȼ�ǰ��մ�С���󣬵�Ȼ�ˣ�Ҳ����order by <����> desc;������ʾ
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.GoodsBean;
import bean.ManageBean;
import util.DBUtil;

public class ManageDAO {
	private static ManageBean bean = null;
	
	/**
	 * �жϹ���Ա��½
	 * @param account
	 * @param password
	 * @return
	 */
	public String login(String account, String password) {
		String nickname = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select mname from manager where mid=? and mpsd=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next())
				nickname = rs.getString("mname");
			// ��װ��bean����
			bean = new ManageBean(account,password,nickname);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return nickname;
	}

	public ManageBean getBean() {
		return bean;
	}

	public boolean modifyInformation(ManageBean bean){
		Connection conn = DBUtil.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "update manager set mpsd=?,mname=? where mid='root@qq.com'";
		try {
			pstmt = conn.prepareStatement(sql);
			String mpsd = bean.getMpsd();
			String mname = bean.getMname();
			if(mpsd!=null && mname!=null && !mpsd.equals("") && !mname.equals("")){
				pstmt.setString(1, mpsd);
				pstmt.setString(2, mname);
				if(pstmt.executeUpdate()>0){
					// ��Ȼ�Ѿ��޸ĳɹ�����ô�޸�bean
					bean.setMid("root@qq.com");
					this.bean = bean;
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return false;
	}

	/*public boolean modifyGoods(GoodsBean bean) {
		Connection conn = DBUtil.getConnection();
		int rs = 0;
		PreparedStatement pstmt = null;
		String sql = "update goods set gname=?,gprice=?,gintroduce=? where gid=" + bean.getGid();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getGname());
			pstmt.setFloat(2, bean.getGprice());
			pstmt.setString(3, bean.getGintroduce());
			rs = pstmt.executeUpdate();
			if(rs>0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}*/
}
