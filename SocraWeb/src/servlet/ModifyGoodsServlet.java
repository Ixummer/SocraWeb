package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.GoodsBean;
import dao.GoodsDAO;

@WebServlet("/ModifyGoodsServlet")
public class ModifyGoodsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	// 构造方法一定要记得加public修饰
	public ModifyGoodsServlet(){
		super();
	}
		
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String gid = (String)request.getParameter("gid");
		String gname = (String)request.getParameter("gname");
		String gprice = (String)request.getParameter("gprice");
		String gintroduce = (String)request.getParameter("gintroduce");
		boolean flag = false;
		int id = Integer.parseInt(gid);
		if(id>0){ // 商品编号不可以为0
			float price = Float.parseFloat(gprice);
			GoodsBean bean = new GoodsBean(id,gname,price,gintroduce);
			GoodsDAO dao = new GoodsDAO();	
			if(gid!=null && gname!=null && gprice!=null && gintroduce!=null){
				flag = dao.modifyGoods(bean);
			}
		}
		if(flag){
			out.print("true");
		}else
			out.print("false");
	}
	
}
