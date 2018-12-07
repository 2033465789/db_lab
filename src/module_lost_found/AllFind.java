package module_lost_found;

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
import javabeans.Good;
import services.LostFindService;
import utils.CacheUtil;
import utils.RequestUtil;
import utils.StaticDataUtil;

@WebServlet("/AllFind")
public class AllFind extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestUtil requestUtil = new RequestUtil(request);
		if (!requestUtil.isOnline()) {
			response.getWriter().append(StaticDataUtil.OFFLINE);
			return;
		}
		LostFindService service = null;
		try {
			service = new LostFindService();
			ArrayList<Good> allFind = new ArrayList<Good>();

			getItemsFromDB(allFind, service, requestUtil);
			getItemsFromCache(allFind);

			request.setAttribute("allFind", allFind);
			request.getRequestDispatcher("all_find.jsp").forward(request, response);
		} catch (DBConnctionException e) {
			request.setAttribute("info", e.getErrorInfo());
			request.getRequestDispatcher("error.jsp").forward(request, response);
			e.printStackTrace();
		} catch (SQLException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			e.printStackTrace();
		} finally {
			if (service != null)
				service.close();
		}
	}

	private void getItemsFromCache(ArrayList<Good> allFind) {
		allFind.addAll(CacheUtil.getCacheTool().getGoodsCache());
	}

	private void getItemsFromDB(ArrayList<Good> allFind, LostFindService service, RequestUtil requestUtil)
			throws SQLException {
		ResultSet rset = service.getAllUserItems(requestUtil.getUser());
		while (rset.next()) {
			Good item = new Good(rset.getInt("lid"), rset.getString("uid"), rset.getString("numberInfo"),
					rset.getString("losterName"), rset.getString("goodDesc"), rset.getString("foundAddr"),
					rset.getString("finderName"), rset.getString("finderPhone"), rset.getString("finderQQorWX"),
					rset.getString("imagePath"));
			allFind.add(item);
		}
	}
}
