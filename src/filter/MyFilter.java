package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.AppInfos;
import exceptions.DBConnctionException;
import utils.StringUtil;

/**
 * Servlet Filter implementation class MyFilter
 */

public class MyFilter implements Filter {

	public MyFilter() {

	}

	public void createSession(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession(false) == null) {
			int curVisiteNum = AppInfos.getVisteNum();
			try {
				AppInfos.setVisteNum(curVisiteNum + 1);
			} catch (DBConnctionException e) {
				request.setAttribute("info", e.getErrorInfo());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
				e.printStackTrace();
			}
			request.getSession(true);
		}
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		// 转换
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		// 设置编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 创建会话
		createSession(request, response);
		// 获取uri.
		String uri = request.getRequestURI();
		// 直接放行的资源
		if (uri.contains(".") || uri.contains("images/")
				|| uri.contains("sharedResource/")) {
			chain.doFilter(request, response);
			return;
		}
		// 获取请求资源名称
		String name = uri.substring(uri.lastIndexOf("/") + 1);
		if (name.length() == 0) {
			request.getRequestDispatcher("/main.jsp").forward(request,
					response);
			chain.doFilter(request, response);
			return;
		}
		StringUtil strTool = StringUtil
				.getStringTool(request.getServletContext());
		// 请求资源为JSP页面
		if (strTool.JspRequest(name)) {
			name += ".jsp";
			request.getRequestDispatcher(name).forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
