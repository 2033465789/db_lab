package module_lost_found;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;

import exceptions.DBConnctionException;
import exceptions.MyException;
import javabeans.Good;
import javabeans.User;
import utils.CacheUtil;
import utils.FileUploadUtil;
import utils.RequestUtil;
import utils.StringUtil;

@WebServlet("/Finder")
public class Finder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Finder() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		FileUploadUtil fileUploadTool = new FileUploadUtil(request, response);
		StringUtil stringTool = StringUtil
				.getStringTool(request.getServletContext());
		RequestUtil rqTool = new RequestUtil(request);
		User user = rqTool.getUser();
		try {
			if (user == null) {
				response.getWriter().append("offline");
				return;
			}

			String newFileName = user.getUid() + "-"
					+ stringTool.getCurrentTime("yyyyMMddHHmmssSSS");
			String filePath = stringTool.getImagespath();
			HashMap<String, String> map = fileUploadTool
					.dealUploadFile(filePath, newFileName);
			String numberInfo = map.get("numberInfo");
			String losterName = map.get("losterName");
			String goodDesc = map.get("goodDesc");
			String foundDddr = map.get("foundDddr");
			String finderName = map.get("finderName");
			String finderPhone = map.get("finderPhone");
			String finderQQorWX = map.get("finderQQorWX");
			String callPath = "images/" + newFileName;
			CacheUtil ct = CacheUtil.getCacheTool();
			int id = CacheUtil.getNewItemId(ct.getGoodsCache(), "lost");
			if (ct.addItemToGoodsCache(new Good(id, user.getUid(), numberInfo,
					losterName, goodDesc, foundDddr, finderName, finderPhone,
					finderQQorWX, callPath))) {
				response.getOutputStream().write("上传成功".getBytes("utf-8"));
			} else {
				response.getOutputStream().write("物品已经被提交".getBytes("utf-8"));
			}

		} catch (FileUploadException e1) {
			e1.printStackTrace();
		} catch (MyException e) {
			response.getOutputStream()
					.write(e.getErrorInfo().getBytes("utf-8"));
			e.printStackTrace();
		} catch (DBConnctionException e) {
			response.getOutputStream()
					.write(e.getErrorInfo().getBytes("utf-8"));
			e.printStackTrace();
		}
	}

}
