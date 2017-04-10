package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.GoodsBean;
import dao.GoodsDAO;

/**
 * Servlet implementation class GoodsShowServlet
 */
@WebServlet("/IndexGoodsShowServlet")
public class IndexGoodsShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IndexGoodsShowServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		GoodsDAO dao = new GoodsDAO();
		PrintWriter out = response.getWriter();
		List<GoodsBean> goodsList = dao.getBeanList();// 表示查询数据库中的所有数据
		if(goodsList.size()>0){
			request.setAttribute("goodsList", goodsList);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			response.sendRedirect("error.html");
		}
	}

}
