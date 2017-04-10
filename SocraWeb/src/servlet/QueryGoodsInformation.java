package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GoodsBean;
import dao.GoodsDAO;

/**
 * Servlet implementation class QueryGoodsInformation
 */
@WebServlet("/QueryGoodsInformation")
public class QueryGoodsInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryGoodsInformation() {
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
		String queryGoods = request.getParameter("gid");
		GoodsDAO dao = new GoodsDAO();
		GoodsBean bean = dao.queryGoods(queryGoods);
		if(bean!=null){
			// �Ѳ�ѯ�����ݷ�װ��bean��
			session.setAttribute("GoodsBean", bean);
			String information = dao.getInformation(queryGoods);
			if(information!=null){
				// jsp����ʾ�����Ŀո����Ҫʹ�á�&nbsp��������ת��
				information = information.replaceAll(" ", "&nbsp;");
				// ����Ʒ����ϸ��Ϣ������װ����
				session.setAttribute("information", information);
				request.getRequestDispatcher("modifyGoodsInformation.jsp").forward(request, response);
			}else{
				out.print("<script type='text/javascript'>"
						+ "alert('��ѯʧ��,��ϸ��ϢΪnull');"
						+ "window.location='error.html';"
						+ "</script>");
			}
		} else{
			request.getRequestDispatcher("error.html").forward(request, response);
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
