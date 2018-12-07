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

/**
 * Servlet implementation class RecentlyGoods
 */
@WebServlet("/RecentlyGoods")
public class RecentlyGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final int RETURN_RECENTLY_GOODS_NUM = 3;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecentlyGoods() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CacheUtil cacheTool = CacheUtil.getCacheTool();
		try {
			String resData = createResponseData(cacheTool);
			response.getOutputStream().write(resData.getBytes("utf-8"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DBConnctionException e) {
			response.getOutputStream().write("无法连接数据库".getBytes("utf-8"));
			e.printStackTrace();
		}
	}

	private String createResponseData(CacheUtil cacheTool) throws SQLException, DBConnctionException {
		StringBuilder res = new StringBuilder("<table id='recently-good-table'>");
		res.append("<thead><tr> <td>失主姓名</td> <td>物品描述</td> <td>物品图片</td></tr></thead>  <tbody>");
		ArrayList<Good> goods = cacheTool.getGoodsCache();
		int size = 0;
		for (Good good : goods) {
			res.append("<tr>");
			res.append("<td>").append(good.getLosterName()).append("</td>");
			res.append("<td>").append(good.getGoodDesc()).append("</td>");
			res.append("<td><img img data-recently='data-recently' src='").append(good.getImagePath()).append("'</td>");
			res.append("</tr> ");
			size++;
			if (size == RETURN_RECENTLY_GOODS_NUM) {
				break;
			}
		}

		if (size < RETURN_RECENTLY_GOODS_NUM) {
			LostFindService service = new LostFindService();
			ResultSet rset = service.getAllItemsOrderByDesc();
			if (rset != null) {
				while (rset.next()) {
					res.append("<tr>");
					res.append("<td>").append(rset.getString(4)).append("</td>");
					res.append("<td>").append(rset.getString(5)).append("</td>");
					res.append("<td><img data-recently='data-recently' src='").append(rset.getString(10))
							.append("'</td>");
					res.append("</tr> ");
					size++;
					if (size == RETURN_RECENTLY_GOODS_NUM) {
						break;
					}
				}
			}
			service.close();
		}
		res.append("</tbody>");
		if (size == 0) {

			return "暂无数据";
		}
		return res.toString();
	}
}
