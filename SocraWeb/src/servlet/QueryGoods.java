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
 * Servlet implementation class QueryGood
 */
@WebServlet("/QueryGoods")
public class QueryGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryGoods() {
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
		
		HttpSession session = request.getSession();
		String queryGoods = request.getParameter("gid");
		GoodsDAO dao = new GoodsDAO();
		GoodsBean bean = dao.queryGoods(queryGoods);
		if(bean!=null){
			// �Ѳ�ѯ�����ݷ�װ��bean��
			session.setAttribute("GoodsBean", bean);
			String information = dao.getInformation(queryGoods);
			// jsp����ʾ�����Ŀո����Ҫʹ�á�&nbsp��������ת��
			if(information!=null){
			information = information.replaceAll(" ", "&nbsp;");
			// ����Ʒ����ϸ��Ϣ������װ����
				session.setAttribute("information", information);
				request.getRequestDispatcher("showGoodsInformation.jsp").forward(request, response);
			}else{
				System.out.println("��ѯ��������Ʒ��information��ʧ����");
				out.print("<script type='text/javascript'>"
						+ "alert('��ѯ������Ʒ��Ϣʧ��,��ϸ��ϢΪnull');"
						+ "window.location='error.html';"
						+ "</script>");
			}
		} else{
			System.out.println("��ѯ��������Ʒ��bean��ʧ����");
			out.print("<script type='text/javascript'>"
					+ "alert('��ѯ������Ʒ��Ϣʧ��,beanΪnull');"
					+ "window.location='error.html';"
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
