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
import exceptions.DBConnctionException;
import javabeans.Comment;
import javabeans.SharedResource;
import services.CommentService;
import services.SharedService;
import utils.CacheUtil;

/**
 * Servlet implementation class ShowFile
 */
@WebServlet("/ShowFileDetails")
public class ShowFileDetails extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowFileDetails()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String id = request.getParameter("id");
		SharedService sharedService = null;
		try
		{
			sharedService = new SharedService();
			int dbMaxId = BaseService.getTableMAXId("shared");
			SharedResource sharedResource = null;
			if (Integer.parseInt(id) <= dbMaxId)
			{
				sharedResource = sharedService.getItemById(id);
			} else
			{
				for (SharedResource item : CacheUtil.getCacheTool().getSharedCache())
				{
					if (item.getId() == Integer.parseInt(id))
					{
						sharedResource = item;
						break;
					}
				}
			}
			// 从数据库获取缓存
			LinkedList<Comment> comments = new CommentService().getItemByFileId(id);
			// 从缓存中获取评论
			getCommentsFormCache(comments, id);
			request.setAttribute("file", sharedResource);
			request.setAttribute("comments", comments);
			request.getRequestDispatcher("show_file_details.jsp").forward(request, response);
		} catch (DBConnctionException e)
		{
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
			e.printStackTrace();
		} finally
		{
			if (sharedService != null)
				sharedService.close();
		}
	}

	private void getCommentsFormCache(LinkedList<Comment> comments, String id)
	{
		ArrayList<Comment> commentCache = CacheUtil.getCacheTool().getCommentsCache();
		for (Comment e : commentCache)
		{
			if (e.getFid().equals(id))
			{
				comments.add(e);
			}
		}
	}

}
