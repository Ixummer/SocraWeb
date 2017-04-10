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
 * Servlet implementation class ManageLogoutServlet
 */
@WebServlet("/AdminLogoutServlet")
public class AdminLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogoutServlet() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		String adminnickname = (String)request.getSession().getAttribute("adminnickname");
		
		HttpSession session = request.getSession();		
		if(adminnickname!=null && !adminnickname.equals("")){
			// 既然管理员退出了，那么管理员的昵称也应该不存在了
			session.setAttribute("adminnickname", null);
			out.print("<script type='text/javascript'>"); // ???
			out.print("alert('"+adminnickname+"成功退出!');");
			// window.location 对象用于获得当前页面的地址 (URL)，并把浏览器重定向到新的页面
			out.print("window.location='admin.html';"); // 使用js来进行跳转，这里不能使用重定向或者是转发
			out.print("</script>");
			// 或者是缓存的锅？把缓存清除？
			//session.invalidate();// 销毁session
		} else{
			out.print("<script type='text/javascript'>"); // ???
			out.print("alert('"+adminnickname+"退出出问题了!');");
			out.print("window.location='error.html';"); // 使用js来进行跳转，这里不能使用重定向或者是转发
			out.print("</script>");
		}
	}

}
