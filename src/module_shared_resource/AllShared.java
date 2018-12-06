package module_shared_resource;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.DBConnctionException;
import javabeans.SharedResource;
import services.SharedService;
import utils.CacheUtil;
import utils.RequestUtil;
import utils.StaticDataUtil;

/**
 * Servlet implementation class AllShared
 */
@WebServlet("/AllShared")
public class AllShared extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AllShared()
	{
		super();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		RequestUtil requestUtil = new RequestUtil(request);
		if (!requestUtil.isOnline())
		{
			response.getWriter().append(StaticDataUtil.OFFLINE);
			return;
		}
		SharedService service = null;
		try
		{
			service = new SharedService();
			ArrayList<SharedResource> allShared = new ArrayList<SharedResource>();

			getItemsFromDB(allShared, service, requestUtil);

			getItemsFromCache(allShared);

			request.setAttribute("allShared", allShared);
			request.getRequestDispatcher("all_shared.jsp").forward(request, response);
		} catch (DBConnctionException e)
		{
			request.setAttribute("info", e.getErrorInfo());
			request.getRequestDispatcher("error.jsp").forward(request, response);
			e.printStackTrace();
		} catch (SQLException e)
		{
			request.getRequestDispatcher("error.jsp").forward(request, response);
			e.printStackTrace();
		} finally
		{
			if (service != null)
				service.close();
		}
	}

	private void getItemsFromCache(ArrayList<SharedResource> allShared)
	{
		allShared.addAll(CacheUtil.getCacheTool().getSharedCache());
	}

	private void getItemsFromDB(ArrayList<SharedResource> allShared, SharedService service, RequestUtil requestUtil)
			throws SQLException
	{
		ResultSet rset = service.getAllResourceByUser(requestUtil.getUser());
		while (rset.next())
		{
			SharedResource item = new SharedResource(rset.getInt("sid"), rset.getString("fileName"),
					rset.getString("uid"), rset.getString("uploadTime"), rset.getString("filePath"),
					rset.getString("fileType"), rset.getString("fileDesc"));

			allShared.add(item);
		}
	}
}
