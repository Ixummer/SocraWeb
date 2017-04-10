package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ManageDAO;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet{
public static final long seriaVersionUID = 1L;
	
	public AdminLoginServlet(){
		super();
	}
	
	public void init(HttpServletRequest request,HttpServletResponse response){
		
	}

	public void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		ManageDAO dao = new ManageDAO();
		String adminnickname = dao.login(account, password);
		if(adminnickname!=null){
			// 管理员成功登陆
			HttpSession session = request.getSession();
			session.setAttribute("adminnickname", adminnickname);
			// 设置session最大存活时间为60s// 同时也会导致用户的session状态消失
			//session.setMaxInactiveInterval(5);
			request.getRequestDispatcher("ManageGoodsServlet").forward(request, response);
		} else{
			// 账号未注册，进行错误提示，这里可以生成
			out.print("<script type='text/javascript'>");
			out.print("alert('用户名或密码错误，请重新输入!');");
			out.print("window.location='admin.html';");
			out.print("</script>");
		}
	}
	
	public void destory(HttpServletRequest request,HttpServletResponse response){
		
	}
}
