package module_user_operare;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.AppInfos;
import exceptions.DBConnctionException;
import javabeans.User;
import services.UserService;
import utils.StaticDataUtil;

/**
 * Servlet implementation class Log_In
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public LogIn()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String userId = request.getParameter("userId");
		String pwd = request.getParameter("pwd");
		UserService service = null;
		try
		{
			service = new UserService();
		} catch (DBConnctionException e1)
		{
			try (OutputStream os = response.getOutputStream();)
			{
				os.write("无法连接数据库".getBytes("utf-8"));
			}
			e1.printStackTrace();
			return;
		}
		if (userId == null || pwd == null)
		{
			request.getRequestDispatcher("main").forward(request, response);
			return;
		}
		try
		{
			if (service.containUser(userId, pwd))
			{
				HttpSession session = request.getSession();
				ResultSet set = service.getUserInfo(userId);
				User user = null;
				if (set.next())
				{
					user = new User(userId, set.getString(2), set.getString(3), set.getString(4), set.getString(5),
							set.getInt(6));
				} else
				{
					user = new User(userId, null, null, null, null, StaticDataUtil.USER);
				}
				if (user.hasBasePermission())
				{
					int newNum = AppInfos.getVisteNum() - 1;
					AppInfos.setVisteNum(newNum);
				}
				session.setAttribute("user", user);
				response.getOutputStream().write("success".getBytes("utf-8"));
			} else
			{
				response.getOutputStream().write("账号或者密码错误".getBytes("utf-8"));
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (service != null)
				service.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
