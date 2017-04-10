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
			// ��Ȼ����Ա�˳��ˣ���ô����Ա���ǳ�ҲӦ�ò�������
			session.setAttribute("adminnickname", null);
			out.print("<script type='text/javascript'>"); // ???
			out.print("alert('"+adminnickname+"�ɹ��˳�!');");
			// window.location �������ڻ�õ�ǰҳ��ĵ�ַ (URL)������������ض����µ�ҳ��
			out.print("window.location='admin.html';"); // ʹ��js��������ת�����ﲻ��ʹ���ض��������ת��
			out.print("</script>");
			// �����ǻ���Ĺ����ѻ��������
			//session.invalidate();// ����session
		} else{
			out.print("<script type='text/javascript'>"); // ???
			out.print("alert('"+adminnickname+"�˳���������!');");
			out.print("window.location='error.html';"); // ʹ��js��������ת�����ﲻ��ʹ���ض��������ת��
			out.print("</script>");
		}
	}

}
