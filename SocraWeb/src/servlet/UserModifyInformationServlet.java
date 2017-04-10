package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import dao.UserDAO;

/**
 * Servlet implementation class UserInformation
 */
@WebServlet("/UserModifyInformationServlet")
public class UserModifyInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserModifyInformationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		UserBean bean = new UserBean(account,password,nickname);
		UserDAO dao = new UserDAO();
		if(dao.modifyUserInformation(bean)){
			out.print("<script type='text/javascript'>"
					+ "alert('资料修改成功');"
					+ "window.location='index.jsp';"
					+ "</script>");
			session.setAttribute("nickname", nickname);
		} else {
			out.print("<script type='text/javascript'>"
					+ "alert('资料修改失败');"
					+ "window.location='userPersonalInformation.jsp';"
					+ "</script>");
		}
		
	}

}
