package module_user_operare;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.DBConnctionException;
import exceptions.MyException;
import javabeans.DormitoryInfo;
import javabeans.User;
import services.UserService;
import utils.InputCheckUtil;

/**
 * Servlet implementation class AlterAccount
 */
@WebServlet("/AlterDormitoryInfo")
public class AlterDormitoryInfo extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AlterDormitoryInfo()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		User user = (User) request.getSession(false).getAttribute("user");
		if (user == null)
			response.sendRedirect("login.html");
		String userId = user.getUserId();
		String dormAddr = request.getParameter("dormAddr");
		String dormWhich = request.getParameter("dormWhich");
		String dormTag = request.getParameter("dormTag");
		UserService service = null;
		try (OutputStream os = response.getOutputStream())
		{
			if (!InputCheckUtil.isOK(userId) || !InputCheckUtil.isOK(dormAddr) || !InputCheckUtil.isOK(dormTag)
					|| !InputCheckUtil.isOK(dormWhich))
			{
				request.getRequestDispatcher("dormitoryinfo_alter.jsp").forward(request, response);
				return;
			}
			
			service = new UserService();
			if (service.AlterDormtoryInfo(userId, new DormitoryInfo(dormAddr, dormWhich, dormTag)))
			{
				os.write("success".getBytes("utf-8"));
			} else
			{
				os.write("数据库写入失败".getBytes("utf-8"));
			}
		} catch (DBConnctionException e)
		{
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
			e.printStackTrace();
		} catch (MyException e)
		{
			response.getWriter().append(e.getErrorInfo());
			e.printStackTrace();
		} finally
		{
			if (service != null)
				service.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
