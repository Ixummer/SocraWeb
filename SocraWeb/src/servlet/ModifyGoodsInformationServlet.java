package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GoodsDAO;

/**
 * Servlet implementation class ModifyGoodsInformationServlet
 */
@WebServlet("/ModifyGoodsInformationServlet")
public class ModifyGoodsInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyGoodsInformationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String gid = (String)request.getParameter("gid");
		String gname = (String)request.getParameter("gname");
		String gprice = (String)request.getParameter("gprice");
		String gintroduce = (String)request.getParameter("gintroduce");
		String ginformation = (String)request.getParameter("area");
		GoodsDAO dao = new GoodsDAO();
		if(dao.modifyGoodsInformation(gid,gname,gprice,gintroduce,ginformation)){
			out.print("<script type='text/javascript'>"
					+ "alert('资料修改成功');"
					+ "window.location='manageGoods.jsp';"
					+ "</script>");
		} else{
			out.print("<script type='text/javascript'>"
					+ "alert('资料修改失败');"
					+ "window.location='modifyGoodsInformation.jsp';"
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
