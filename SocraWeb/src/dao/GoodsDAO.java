package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.GoodsBean;
import util.DBUtil;

/**
 * ������Ʒ����ʾ
 * @author Suagr
 *
 */
public class GoodsDAO {
	public static List<GoodsBean> goodsList = new ArrayList<GoodsBean>();
	private static GoodsBean bean = null;
	private static String information = null;
	private static List<GoodsBean> searchResultList = new ArrayList<>();
	
	public List<GoodsBean> getBeanList() {
		Connection conn = DBUtil.getConnection();
		ResultSet rs = null;
		
		String sql = "SELECT * FROM goods order by gid"; 
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs!=null){
				goodsList.clear();
				while(rs.next()){
					bean = new GoodsBean(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getString(4));
					goodsList.add(bean);
				}
			}
			//System.out.println(goodsList.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goodsList;
	}

	public GoodsBean queryGoods(String queryGoods) {
		Connection conn = DBUtil.getConnection();
		ResultSet rs = null;
		int gid = Integer.parseInt(queryGoods);
		String sql = "SELECT * FROM goods WHERE gid=?"; 
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gid);
			rs = pstmt.executeQuery();
			if(rs.next()){
				bean = new GoodsBean(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getString(4));
				// --------- ����������ϸ��Ϣ
				String sql2 = "SELECT * FROM goodsInformation WHERE gid=?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, gid);
				rs = pstmt.executeQuery();
				if(rs.next())
					information = rs.getString(2);
			}
		} catch (SQLException e) {
			System.out.println("��ѯ��Ʒ����");
			e.printStackTrace();
		}
		return bean;
	}
	
	/**
	 * ��ȡ��Ʒ����ϸ��Ϣ
	 * @return
	 */
	public String getInformation(String gid){
		Connection conn = DBUtil.getConnection();
		ResultSet rs = null;
		int newgid = Integer.parseInt(gid);
		String sql = "SELECT * FROM goodsInformation WHERE gid=?"; 
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, newgid);
			rs = pstmt.executeQuery();
			if(rs.next()){
				information = rs.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return information;
	}

	/**
	 * �޸���Ʒ��Ϣ
	 * @param bean
	 * @return ע����Ʒ��Ų�����Ϊ0
	 */
	public boolean modifyGoods(GoodsBean bean) {
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
	}

	public boolean modifyGoodsInformation(String gid, String gname, String gprice, String gintroduce, String ginformation) {
		// ���·�װ����
		int newgid = Integer.parseInt(gid);
		float newgprice = Float.parseFloat(gprice);
		GoodsBean bean = new GoodsBean(newgid,gname,newgprice,gintroduce);
		
		Connection conn = DBUtil.getConnection();
		int rs = 0;
		PreparedStatement pstmt = null;
		String sql = "update goods set gname=?,gprice=?,gintroduce=? where gid=" + newgid;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gname);
			pstmt.setFloat(2, newgprice);
			pstmt.setString(3, gintroduce);
			rs = pstmt.executeUpdate();
			if(rs>0){
				this.bean = bean;
				String sql2 = "update goodsInformation set ginformation=? where gid=" + newgid;
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, ginformation);
				rs = pstmt.executeUpdate();
				if(rs>0){
					this.information = ginformation;
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * ����µ���Ʒ
	 * @param gid
	 * @param gname
	 * @param gprice
	 * @param gintroduce
	 * @param ginformation
	 * @return
	 */
	public boolean addGoodsInformation(String gid, String gname, String gprice, String gintroduce,
			String ginformation) {
		// ���·�װ����
		int newgid = Integer.parseInt(gid);
		float newgprice = Float.parseFloat(gprice);
		GoodsBean bean = new GoodsBean(newgid,gname,newgprice,gintroduce);
		
		Connection conn = DBUtil.getConnection();
		int rs = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into goods values(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, newgid);
			pstmt.setString(2, gname);
			pstmt.setFloat(3, newgprice);
			pstmt.setString(4, gintroduce);
			// ���� INSERT��UPDATE �� DELETE ���
			rs = pstmt.executeUpdate();
			if(rs>0){
				String sql2 = "insert into goodsInformation values(?,?)";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, newgid);
				pstmt.setString(2, ginformation);
				rs = pstmt.executeUpdate();
				if(rs>0){
					// ��bean�ӵ�beanlist��
					goodsList.add(bean);
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * ɾ����Ʒ
	 * @param gid
	 * @return
	 */
	public boolean deleteGoods(String gid) {
		int newgid = Integer.parseInt(gid);
		Connection conn = DBUtil.getConnection();
		int rs = 0;
		PreparedStatement pstmt = null;
		String sql = "delete from goods where gid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, newgid);
			// ���� INSERT��UPDATE �� DELETE ���
			rs = pstmt.executeUpdate();
			if(rs>0){
				String sql2 = "delete from goodsInformation where gid=?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, newgid);
				rs = pstmt.executeUpdate();
				if(rs>0){
					for(GoodsBean bean : goodsList){
						if(bean.getGid()==newgid){
							goodsList.remove(bean);
							break;
						}
					}
					return true;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * �����������Ʒ
	 * @param searchText
	 * @return
	 */
	public List<GoodsBean> searchGoods(String searchText) {
		Connection conn = DBUtil.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "";
		int gid = 0;
		int flag = 1;
		for(int i=0;i<searchText.length();i++){
			char c = searchText.charAt(i);
			if(c>'9' || c<'0'){
				flag = -1;
				break;
			}
		}
		if(flag>0){ // ��ʾ�ַ���ֻ��������
			gid = Integer.parseInt(searchText);
		}
		sql = "SELECT * FROM goods where gid=? OR gname=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gid);
			pstmt.setString(2, searchText);
			rs = pstmt.executeQuery();
			// ����ϴβ�ѯ�Ľ��
			searchResultList.clear();
			GoodsBean bean = null;
			if(rs.next()){
				bean = new GoodsBean(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getString(4));
				searchResultList.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return searchResultList;
	}
}
