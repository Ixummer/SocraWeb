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
			// ����Ա�ɹ���½
			HttpSession session = request.getSession();
			session.setAttribute("adminnickname", adminnickname);
			// ����session�����ʱ��Ϊ60s// ͬʱҲ�ᵼ���û���session״̬��ʧ
			//session.setMaxInactiveInterval(5);
			request.getRequestDispatcher("ManageGoodsServlet").forward(request, response);
		} else{
			// �˺�δע�ᣬ���д�����ʾ�������������
			out.print("<script type='text/javascript'>");
			out.print("alert('�û����������������������!');");
			out.print("window.location='admin.html';");
			out.print("</script>");
		}
	}
	
	public void destory(HttpServletRequest request,HttpServletResponse response){
		
	}
}
