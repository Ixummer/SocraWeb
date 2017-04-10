package filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class TurnToAdmin
 */
@WebFilter(urlPatterns="",initParams={
		@WebInitParam(name="Character",value="utf-8") })
public class TurnToAdminFilter implements Filter {
	// 用于获得初始化参数
	private FilterConfig config;
	
    /**
     * Default constructor. 
     */
    public TurnToAdminFilter() {
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
		// 获得初始化参数
		//String visitPage = config.getInitParameter("");
		//String visitServlet = config.getInitParameter("");
		// 获得会话对象
		HttpSession session = ((HttpServletRequest)request).getSession();
		// 获取请求资源路径（不包含请求参数）
		String requestPath = ((HttpServletRequest)request).getServletPath();
		// 重新获得需要转发的response
		HttpServletResponse newResponse = ((HttpServletResponse)response);
		newResponse.setContentType("text/html;charaset=utf-8");
		
		// 获得管理员的id
		String name = (String) session.getAttribute("adminnickname");
		System.out.println(name);
		if(name==null){
			// 没有管理员登陆，则转发至管理员登陆页面
			request.getRequestDispatcher("admin.html").forward(request, newResponse);
		} else{
			request.getRequestDispatcher("ManageGoodsServlet").forward(request, newResponse);
			// pass the request along the filter chain允许通过
			chain.doFilter(request, response);
		}
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = config;
	}

}
