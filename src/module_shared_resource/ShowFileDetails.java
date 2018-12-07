package module_shared_resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.BaseService;
import daos.CommentDao;
import exceptions.DBConnctionException;
import javabeans.CommentFile;
import javabeans.SharedResource;
import services.SharedService;
import utils.CacheUtil;
import utils.StaticDataUtil;

/**
 * Servlet implementation class ShowFile
 */
@WebServlet("/ShowFileDetails")
public class ShowFileDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowFileDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String page = request.getParameter("page");
		if (page == null)
			page = "1";
		SharedService sharedService = null;
		CommentDao dao = null;
		try {
			sharedService = new SharedService();
			int dbMaxId = BaseService.getTableMAXId("shared");
			SharedResource sharedResource = null;
			if (Integer.parseInt(id) <= dbMaxId) {
				sharedResource = sharedService.getItemById(id);
			} else {
				for (SharedResource item : CacheUtil.getCacheTool()
						.getSharedCache()) {
					if (item.getSid() == Integer.parseInt(id)) {
						sharedResource = item;
						break;
					}
				}
			}
			// 从数据库获取缓存
			dao = new CommentDao();
			LinkedList<CommentFile> comments = dao.getItemAsPageByFileId(id,
					page);
			// 从缓存中获取评论
			getCommentsFormCache(comments, id);
			request.setAttribute("file", sharedResource);
			request.setAttribute("comments", comments);
			request.setAttribute("page", Long.parseLong(page));
			request.setAttribute("pageSize", StaticDataUtil.PAGE_COMMENT_SIZE);
			request.setAttribute("itemCount", dao.getItemCount(id));
			request.getRequestDispatcher("show_file_details.jsp")
					.forward(request, response);
			dao.close();
		} catch (DBConnctionException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request,
					response);
			e.printStackTrace();
		} finally {
			if (sharedService != null)
				sharedService.close();
			if (dao != null)
				dao.close();
		}
	}

	private void getCommentsFormCache(LinkedList<CommentFile> comments,
			String id) {
		ArrayList<CommentFile> commentCache = CacheUtil.getCacheTool()
				.getCommentsCache();
		for (CommentFile e : commentCache) {
			if (e.getSid() == Long.parseLong(id)) {
				comments.add(e);
			}
		}
	}

}
