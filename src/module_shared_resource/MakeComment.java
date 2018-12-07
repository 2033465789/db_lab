package module_shared_resource;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.DBConnctionException;
import exceptions.MyException;
import javabeans.CommentFile;
import javabeans.User;
import utils.CacheUtil;
import utils.InputCheckUtil;
import utils.RequestUtil;

/**
 * Servlet implementation class MakeComment
 */
@WebServlet("/MakeComment")
public class MakeComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MakeComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String content = request.getParameter("commentContent");
		String fid = request.getParameter("fileId");
		try {
			if (!InputCheckUtil.isOK(fid) || !InputCheckUtil.isOK(content)) {
				response.getWriter().append("数据错误");
				return;
			}
			RequestUtil requestUtil = RequestUtil.getRequestTool(request);
			User user = requestUtil.getUser();
			if (user == null) {
				response.getWriter().append("offline").flush();
				return;
			}
			CommentFile comment = new CommentFile(0, user.getUid(),
					Long.parseLong(fid), content);
			if (CacheUtil.getCacheTool().addItemToCommentCache(comment)) {
				response.getWriter().append("success").flush();
			} else {
				response.getWriter().append("failed").flush();
			}
		} catch (MyException e) {
			e.printStackTrace();
		} catch (DBConnctionException e) {
			response.getWriter().append("无法连接数据库").flush();
			e.printStackTrace();
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
