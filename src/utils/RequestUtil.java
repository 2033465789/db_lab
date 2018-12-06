package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javabeans.User;

public class RequestUtil
{
	private HttpServletRequest request;

	public static RequestUtil getRequestTool(HttpServletRequest request)
	{
		return new RequestUtil(request);
	}

	public RequestUtil(HttpServletRequest request)
	{
		this.request = request;
	}

	public User getUser()
	{
		HttpSession session = request.getSession(false);
		User user = null;
		if (session != null)
			user = (User) session.getAttribute("user");
		return user;
	}


	// 用户已登录
	public boolean isOnline()
	{
		return getUser() != null;
	}
}
