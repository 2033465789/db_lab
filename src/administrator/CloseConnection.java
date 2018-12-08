package administrator;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabeans.User;
import utils.RequestUtil;

/**
 * Servlet implementation class CloseConnection
 */
@WebServlet("/admin/CloseConnection")
public class CloseConnection extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CloseConnection()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestUtil requestUtil = RequestUtil.getRequestTool(request);
		try (OutputStream os = response.getOutputStream())
		{
			// 未登录
			if (!requestUtil.isOnline())
			{
				os.write("未登录".getBytes());
				return;
			}
			RequestUtil rqTool = RequestUtil.getRequestTool(request);
			User user = rqTool.getUser();
			if (AdminService.closeConnection(user))
			{
				os.write("success".getBytes("utf-8"));
			} else
			{
				os.write("error".getBytes("utf-8"));
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
