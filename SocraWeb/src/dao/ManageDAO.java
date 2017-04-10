package dao;
/**
 * 1.Class.forName("oracle.jdbc.driver.OracleDriver").newInstance(); 
数据库驱动只会加载一次，一般封装DAO会写在静态块里面。 

2.访问一条SQL语句后连接数据库的Connection必须要关闭吗 可以不关闭接着访问一条SQL吗
一般操作完数据库需要及时的释放资源，不是执行几条SQL语句的问题，比如写一个批处理，关键是使用完要关闭，
比如执行了executeUpdate()，或者遍历完ResultSet，在这个时候才需要关闭。

3.数据库中的查询结果自然是按照从小到大，当然了，也可以order by <列名> desc;降序显示
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
	 * 判断管理员登陆
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
			// 封装到bean里面
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
					// 既然已经修改成功，那么修改bean
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
