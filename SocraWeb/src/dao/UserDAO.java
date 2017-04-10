package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.UserBean;
import util.DBUtil;

public class UserDAO {
	private static UserBean bean = null;
	
	/**
	 * 验证账号是否已经存在
	 * @param account
	 * @return
	 */
	public boolean isExistEmail(String account) {		
		// 与特定数据库的连接（会话）。在连接上下文中执行 SQL 语句并返回结果
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;// 表示预编译的SQL语句的对象
		ResultSet rs = null;
		String sql = "select * from user where uid = ?";// 构建sql查询语句
		try{
			// 对数据路的一般操作Statement对象即可，但是针对Insert和update操作需要针对PreparedStatement对象操作
			pstmt = conn.prepareStatement(sql);
			// 设置SQL语句的参数值
			pstmt.setString(1,account);
			// 在此 PreparedStatement 对象中执行 SQL 查询，并返回该查询生成的 ResultSet 对象
			rs = pstmt.executeQuery();
			if(rs.next()) // 如果新的当前行有效，则返回 true；如果不存在下一行，则返回 false 
				return true;
		} catch(SQLException e){
			e.printStackTrace();
		} finally{
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return false;
	}

	/**
	 * 注册信息保存
	 * @param account
	 * @param password
	 */
	public void save(String account, String password, String username) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into user ("
				+ "uid,upsd,uname) values(?,?,?);";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2, password);
			pstmt.setString(3, username);
			// 将数据更新
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户登陆
	 * @param account
	 * @param password
	 * @return
	 */
	public String login(String account, String password) {
		String nickname = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select uname from user where uid=? and upsd=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery(); // 执行  查询
			if(rs.next()){
				// 以 Java 编程语言中 String 的形式获取此 ResultSet 对象的当前行中指定列的值
				nickname = rs.getString("uname");
				bean = new UserBean(account,password,nickname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return nickname;
	}
	
	/**
	 * 返回用户的bean
	 * @return
	 */
	public UserBean getBean(){
		return bean;
	}

	/**
	 * 用户修改自己的信息
	 * @param bean
	 * @return
	 */
	public boolean modifyUserInformation(UserBean bean) {
		Connection conn = DBUtil.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "update user set upsd=?,uname=? where uid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			String uid = bean.getUid();
			String upsd = bean.getUpsd();
			String uname = bean.getUname();
			if(upsd!=null && uname!=null && !upsd.equals("") && !uname.equals("")){
				pstmt.setString(1, upsd);
				pstmt.setString(2, uname);
				pstmt.setString(3, uid);
				if(pstmt.executeUpdate()>0){
					// 既然已经修改成功，那么修改bean
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
}
