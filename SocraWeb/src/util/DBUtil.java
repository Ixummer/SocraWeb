package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ���ݿ����ӻ�ȡ���ͷŹ�����
 * @author Suagr
 * ����jdbc��jar��ʱ����Ҫ�Ȱ�jar��������WEB-INF���lib�У���ֱ��Build-path
 */
public class DBUtil {
	static String rootname = "root";
	static String rootpassword = "123456";
	static String url = "jdbc:mysql://localhost:3306/web_manage?useSSL=false";
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, rootname, rootpassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeJDBC(ResultSet rs,Statement stmt,Connection conn){
		if(rs!=null){
			try{
				rs.close();	
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
