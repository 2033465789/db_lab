package module_lost_found;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.DBConnctionException;
import exceptions.MyException;
import javabeans.Good;
import services.LostFindService;
import utils.CacheUtil;
import utils.InputCheckUtil;
import utils.StringUtil;

/**
 * Servlet implementation class Loster
 */
@WebServlet("/Loster")
public class Loster extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Loster()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		StringUtil stringTool = StringUtil.getStringTool(getServletContext());
		String searchInfo = request.getParameter("searchInfo");
		try
		{
			if (!InputCheckUtil.isOK(searchInfo))
			{
				response.getOutputStream().write("no searchInfomatin".getBytes("utf-8"));
				throw new MyException("没有搜索信息");
			}
			String resData = createResponseData(searchInfo, stringTool);
			response.getOutputStream().write(resData.getBytes("utf-8"));
		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (MyException e)
		{
			e.printStackTrace();
		} catch (DBConnctionException e)
		{
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

	String createResponseData(String searchInfo, StringUtil stringTool) throws SQLException, DBConnctionException
	{
		StringBuilder res = new StringBuilder("<table id='lost-good-table'>");
		res.append("<thead><tr> <td>失主姓名</td> <td>数字信息</td> <td>物品描述</td> <td>拾取地点</td> "
				+ "<td>联系人姓名</td> <td>联系人电话</td> <td>QQ或者微信</td> <td>物品图片</td></tr></thead>  <tbody>");

		findGoodFromDB(res, searchInfo, stringTool);
		findGoodFromCache(res, searchInfo, stringTool);

		res.append("</tbody>");
		return res.toString();
	}

	// 选择缓存中符合条件的物品信息
	public void findGoodFromCache(StringBuilder res, String searchInfo, StringUtil stringTool) throws SQLException
	{
		Iterator<Good> iterator = CacheUtil.getCacheTool().getGoodsCache().iterator();
		while (iterator.hasNext())
		{
			Good good = iterator.next();
			if (stringTool.goodContainInfo(good, searchInfo))
			{
				stringTool.makeTr(res, good);
			}
		}
	}

	// 选择数据库中符合条件的物品信息
	public void findGoodFromDB(StringBuilder res, String searchInfo, StringUtil stringTool)
			throws SQLException, DBConnctionException
	{
		LostFindService service = new LostFindService();
		ResultSet set = service.getAllItems();
		while (set.next())
		{
			Good good = new Good(set.getString(2), set.getString(3), set.getString(4), set.getString(5),
					set.getString(6), set.getString(7), set.getString(8), set.getString(9), set.getString(10));
			if (stringTool.goodContainInfo(good, searchInfo))
			{
				stringTool.makeTr(res, good);
			}
		}
		service.close();
	}

}
