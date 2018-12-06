package module_shared_resource;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.DBConnctionException;
import exceptions.MyException;
import javabeans.SharedResource;
import services.SharedService;
import utils.CacheUtil;
import utils.InputCheckUtil;

/**
 * Servlet implementation class SharedResource
 */
@WebServlet("/DownloadResource")
public class DownloadResource extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadResource()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String searchInfo = request.getParameter("searchInfo");
		// 返回所有资源的集合
		LinkedList<SharedResource> shared = null;
		try
		{
			shared = getAllInfo();
			// 判断是否为搜索
			if (InputCheckUtil.isOK(searchInfo))
			{
				dealSearchInfo(shared, searchInfo);
			}
			request.setAttribute("shared", shared);
			request.getRequestDispatcher("download_resource.jsp").forward(request, response);
		} catch (SQLException e)
		{
			request.getRequestDispatcher("error.jsp").forward(request, response);
			e.printStackTrace();
		} catch (DBConnctionException e)
		{
			request.setAttribute("info", e.getErrorInfo());
			request.getRequestDispatcher("error.jsp").forward(request, response);
			e.printStackTrace();
		} catch (MyException e)
		{
			request.setAttribute("info", e.getErrorInfo());
			request.getRequestDispatcher("error.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	private void dealSearchInfo(LinkedList<SharedResource> shared, String searchInfo)
	{
		Iterator<SharedResource> iterator = shared.iterator();
		while (iterator.hasNext())
		{
			SharedResource item = iterator.next();
			if (!item.containsInfo(searchInfo))
			{
				iterator.remove();
			}
		}
	}

	private LinkedList<SharedResource> getAllInfo() throws SQLException, DBConnctionException
	{
		// 返回所有资源的集合
		LinkedList<SharedResource> shared = new LinkedList<>();
		SharedService service = new SharedService();
		// 从数据库获取数据
		ResultSet rset = service.getAllResource();
		while (rset.next())
		{
			SharedResource item = new SharedResource(rset.getInt("id"), rset.getString("fileName"),
					rset.getString("uploadUser"), rset.getString("uploadTime"), rset.getString("filePath"),
					rset.getString("fileType"), rset.getString("fileDesc"));
			shared.add(item);
		}

		// 从缓存获取数据
		CacheUtil ct = CacheUtil.getCacheTool();
		for (SharedResource item : ct.getSharedCache())
		{
			shared.add(item);
		}
		service.close();
		return shared;
	}

	// 判断文件是否存在,存在则加入响应数据
	// private void dealItem(LinkedList<SharedResource> shared, SharedResource
	// item)
	// {
	// StringUtil strTool = StringUtil.getStringTool(getServletContext());
	// String filePath = strTool.getSharedPath() + item.getFileName();
	// if (new File(filePath).exists())
	// {
	//
	// } else
	// {
	// try
	// {
	// throw new MyException("文件不存在,但是数据库中");
	// } catch (MyException e)
	// {
	// e.printStackTrace();
	// }
	// }
	// }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
