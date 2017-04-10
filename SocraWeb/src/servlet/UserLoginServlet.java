package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;

// 使用注解配置Servlet，不需要再在xml中配置
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet{
	public static final long seriaVersionUID = 1L;
	
	public UserLoginServlet(){
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		UserDAO dao = new UserDAO();
		String nickname = dao.login(account, password);
		
		if(nickname!=null){
			// 获取session并存入值
			HttpSession session = request.getSession();
			session.setAttribute("nickname", nickname);
			request.getRequestDispatcher("IndexGoodsShowServlet").forward(request, response);
		} else{
			// 只能使用JS来进行页面的转发，中文才不会乱码
			out.print("<script type='text/javascript'>");
			out.print("alert('账号密码不对!');");
			out.print("window.location='login.html';");
			out.print("</script>");
			//request.getRequestDispatcher("login.html").forward(request, response);

		}
	}
	
	public void destory(HttpServletRequest request,HttpServletResponse response){
		
	}
}
