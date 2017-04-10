package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public UserRegisterServlet(){
		super();
	}
	
	public void init(HttpServletRequest request,HttpServletResponse response){
		
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		// 设置请求和响应编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 获取请求参数
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		// 判断邮箱是否已经被注册
		UserDAO dao = new UserDAO();
		boolean flag = dao.isExistEmail(account);
		if(flag){
			// 账号已经注册，进行错误提示，这里可以生成
			
			// 暂时统一转到错误页面
			response.sendRedirect("error.html");
		} else{
			// 账号未被注册，保存注册用户信息
			dao.save(account,password,username);
			// 注册成功，重定向到登陆页面
			response.sendRedirect("login.html");
		}
	}
	
	public void destory(){
		
	}
}
