package administrator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.AppInfos;
import daos.ConnectPoolManager;
import utils.CacheUtil;

/**
 * Servlet implementation class AdministratorCenter
 */
@WebServlet("/AdministratorCenter")
public class AdministratorCenter extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministratorCenter()
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
		request.getRequestDispatcher("administrator_center.jsp").forward(request, response);
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
