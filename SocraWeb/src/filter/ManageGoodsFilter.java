package filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GoodsBean;
import dao.GoodsDAO;

/**
 * Servlet Filter implementation class ManageGoodsFilter
 */
//@WebFilter("/ManageGoodsFilter")
public class ManageGoodsFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ManageGoodsFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// ��ó�ʼ������
		//String visitPage = config.getInitParameter("");
		//String visitServlet = config.getInitParameter("");
		// ��ûỰ����
		HttpSession session = ((HttpServletRequest)request).getSession();
		// ��ȡ������Դ·�������������������
		String requestPath = ((HttpServletRequest)request).getServletPath();
		// ���»����Ҫת����response
		HttpServletResponse newResponse = ((HttpServletResponse)response);
		newResponse.setContentType("text/html;charaset=utf-8");
		
		// ��ù���Ա��id
		String name = (String) session.getAttribute("adminnickname");
		System.out.println(name+"hello");
		if(name==null){
			// û�й���Ա��½����ת��������Ա��½ҳ��
			request.getRequestDispatcher("admin.html").forward(request, newResponse);
		} else{
			GoodsDAO dao = new GoodsDAO();
			PrintWriter out = newResponse.getWriter();
			
			List<GoodsBean> goodsList = dao.getBeanList();// ��ʾ��ѯ���ݿ��е���������
			if(goodsList.size()>0 && session.getAttribute("adminnickname")!=null){
				request.setAttribute("goodsList", goodsList);
				chain.doFilter(request, newResponse);
			} else{
				request.getRequestDispatcher("error.html").forward(request, newResponse);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
