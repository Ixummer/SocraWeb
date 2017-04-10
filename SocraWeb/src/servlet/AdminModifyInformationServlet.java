package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ManageBean;
import dao.ManageDAO;

/**
 * Servlet implementation class ManagerModifyServlet
 */
@WebServlet("/AdminModifyInformationServlet")
public class AdminModifyInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminModifyInformationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * ��ȡ�ύ���޸ĺ�����ݣ�Ȼ��׼������DAOд�����ݿ�
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String adminnickname = request.getParameter("adminnickname");
		
		ManageDAO dao = new ManageDAO();
		ManageBean bean = new ManageBean(account,password,adminnickname);
		
		// ����֤session�Ƿ���
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("adminnickname");
		if(name!=null && dao.modifyInformation(bean)){
			session.setAttribute("adminnickname", adminnickname);
			out.print("<script type='text/javascript'>"
					+ "alert('�����޸ĳɹ�');"
					+ "window.location='manageGoods.jsp';"
					+ "</script>");
		}else{
			if(name!=null){
			out.print("<script type='text/javascript'>"
					+ "alert('�����޸�ʧ��');"
					+ "window.location='manageGoods.jsp';"
					+ "</script>");
			}else{
				out.print("<script type='text/javascript'>"
						+ "alert('û��Ȩ�ޣ�');"
						+ "window.location='admin.html';"
						+ "</script>");
			}
		}
	}

}
