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
 * Servlet implementation class SearchGoodsServlet
 */
@WebServlet("/SearchGoodsServlet")
public class SearchGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String searchText = request.getParameter("searchText");
		GoodsDAO dao = new GoodsDAO();
		// 只能查找商品编号和商品名称
		List<GoodsBean> searchResultList = dao.searchGoods(searchText);
		if(searchResultList!=null && searchResultList.size()>0){
			HttpSession session = request.getSession();
			session.setAttribute("searchResultList", searchResultList);
			out.print("<script type='text/javascript'>"
					+ "alert('商品已经找到');"
					+ "window.location='searchGoodsResult.jsp';"
					+ "</script>");
		} else{
			out.print("<script type='text/javascript'>"
					+ "alert('商品没有找到');"
					+ "window.location='searchFail.jsp';"
					+ "</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
