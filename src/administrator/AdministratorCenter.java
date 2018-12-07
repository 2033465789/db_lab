package administrator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.AppInfos;
import daos.CommentDao;
import daos.ConnectPoolManager;
import daos.SharedDao;
import exceptions.DBConnctionException;
import utils.CacheUtil;
import utils.RequestUtil;

/**
 * Servlet implementation class AdministratorCenter
 */
@WebServlet("/AdministratorCenter")
public class AdministratorCenter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministratorCenter() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestUtil reqTool = RequestUtil.getRequestTool(request);
		if (reqTool.getUser() == null
				|| reqTool.getUser().hasBasePermission() == false) {
			request.setAttribute("alert", "你想干嘛!?!");
			request.getRequestDispatcher("main.jsp").forward(request, response);
			return;
		}
		boolean dbStatus = ConnectPoolManager.getUserConnectionStatus();
		CacheUtil cacheTool = CacheUtil.getCacheTool();
		int sharedCache_size = cacheTool.getSharedCache().size();
		int goodsCache_size = cacheTool.getGoodsCache().size();
		int commentCache_size = cacheTool.getCommentsCache().size();
		request.setAttribute("dbStatus", dbStatus);
		request.setAttribute("sharedCache_size", sharedCache_size);
		request.setAttribute("visiteNum", AppInfos.getVisteNum());
		request.setAttribute("goodsCache_size", goodsCache_size);
		request.setAttribute("commentCache_size", commentCache_size);
		request.setAttribute("alert", "你好,管理员!!!");
		SharedDao sdao = null;
		CommentDao cdao = null;
		try {
			sdao = new SharedDao();
			cdao = new CommentDao();
			request.setAttribute("shared", sdao.getAllItem());
			request.setAttribute("comments", cdao.getAllItem());
			request.getRequestDispatcher("administrator_center.jsp")
					.forward(request, response);
		} catch (DBConnctionException e) {
			e.printStackTrace();
		} finally {
			sdao.close();
			cdao.close();
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
