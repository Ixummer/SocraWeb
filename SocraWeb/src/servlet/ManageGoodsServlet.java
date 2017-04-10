package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GoodsBean;
import dao.GoodsDAO;

/**
 * Servlet implementation class ManageGoodsServlet
 */
@WebServlet("/ManageGoodsServlet")
public class ManageGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		GoodsDAO dao = new GoodsDAO();
		PrintWriter out = response.getWriter();
		
		List<GoodsBean> goodsList = dao.getBeanList();// 表示查询数据库中的所有数据
		HttpSession session = request.getSession();
		//System.out.println(goodsList.size());
		if(goodsList.size()>0 && session.getAttribute("adminnickname")!=null){
			request.setAttribute("goodsList", goodsList);
			request.getRequestDispatcher("manageGoods.jsp").forward(request, response); // 保存在session的东西可以跨转发
		} else {
			response.sendRedirect("error.html");
		}
	}

}
