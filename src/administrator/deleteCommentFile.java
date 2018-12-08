package administrator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CommentFileDao;
import daos.SharedDao;
import exceptions.DBConnctionException;
import javabeans.User;
import utils.RequestUtil;

/**
 * Servlet implementation class deleteCommentFile
 */
@WebServlet("/admin/deleteCommentFile")
public class deleteCommentFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleteCommentFile() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		System.out.println(cid);
		if (cid == null) {
			request.setAttribute("alert", "你想干嘛?!?");
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
			CommentFileDao dao = null;
			try {
				dao = new CommentFileDao();
				if (dao.deleteItem(user.getUid(), Long.parseLong(cid)))
					request.getRequestDispatcher("/admin/AdministratorCenter")
							.forward(request, response);
				else {

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
