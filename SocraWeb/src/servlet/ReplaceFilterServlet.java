package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ReplaceFilterServlet
 */
@WebServlet("/ReplaceFilterServlet")
public class ReplaceFilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplaceFilterServlet() {
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
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("adminnickname");
		//System.out.println(name);
		if(name==null){
			// ��֪��Ϊʲô����ͨ��ת������ת����Ӧ��ҳ�棨��Ϊ���벻�ԣ�
			//request.getRequestDispatcher("admin.html").forward(request, response);
			out.print("<script type='text/javascript'>"
					+ "alert('�㻹û��Ȩ�ޣ����¼');"
					+ "window.location='admin.html';"
					+ "</script>");
		} else{
			request.getRequestDispatcher("manageGoods.jsp").forward(request, response);
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
