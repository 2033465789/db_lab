package administrator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.SharedDao;
import exceptions.DBConnctionException;
import javabeans.User;
import utils.RequestUtil;

/**
 * Servlet implementation class deleteShared
 */
@WebServlet("/admin/deleteShared")
public class deleteShared extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleteShared() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter("sid");
		if (sid == null) {
			request.getRequestDispatcher("/admin/AdministratorCenter")
					.forward(request, response);
			return;
		} else {
			RequestUtil reqTool = RequestUtil.getRequestTool(request);
			User user = reqTool.getUser();
			if (user == null) {
				request.setAttribute("alert", "未登录");
				request.getRequestDispatcher("/admin/AdministratorCenter")
						.forward(request, response);
				return;
			}
			if (!user.hasBasePermission()) {
				request.setAttribute("alert", "没有权限");
				request.getRequestDispatcher("/admin/AdministratorCenter")
						.forward(request, response);
				return;
			}
			SharedDao dao = null;
			try {
				dao = new SharedDao();
				if (dao.deleteItem(user.getUid(), Long.parseLong(sid)))
					request.getRequestDispatcher("/admin/AdministratorCenter")
							.forward(request, response);
				else
				{
					
				}
			} catch (DBConnctionException e) {
				e.printStackTrace();
			} finally {
				dao.close();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
