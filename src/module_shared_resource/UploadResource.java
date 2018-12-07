package module_shared_resource;

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
import javabeans.SharedResource;
import javabeans.User;
import utils.CacheUtil;
import utils.FileUploadUtil;
import utils.RequestUtil;
import utils.StaticDataUtil;
import utils.StringUtil;

/**
 * Servlet implementation class UploadResource
 */
@WebServlet("/UploadResource")
public class UploadResource extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public long uploadLength = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadResource() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		FileUploadUtil fileUploadTool = new FileUploadUtil(request, response);
		StringUtil stringTool = StringUtil
				.getStringTool(request.getServletContext());
		RequestUtil rqTool = RequestUtil.getRequestTool(request);
		User user = rqTool.getUser();
		try {
			if (user == null) {
				response.getWriter().write(StaticDataUtil.OFFLINE);
				return;
			}
			String filePath = stringTool.getSharedPath();
			HashMap<String, String> map = fileUploadTool
					.dealUploadFile(filePath, null);
			String fileName = map.get("fileName");
			String fileType = map.get("fileType");
			String fileDesc = map.get("fileDesc");
			String uploadUser = user.getUid();
			String uploadTime = stringTool.getCurrentTime("yyyy-MM-dd");
			String callPath = stringTool.getSharedPath() + fileName;
			// 加入缓存
			CacheUtil ct = CacheUtil.getCacheTool();
			int id = CacheUtil.getNewItemId(ct.getSharedCache(), "shared");
			if (ct.addItemToSharedCache(new SharedResource(id, fileName,
					uploadUser, uploadTime, callPath, fileType, fileDesc))) {
				response.getOutputStream().write("success".getBytes("utf-8"));
			} else {
				response.getOutputStream().write("failed".getBytes("utf-8"));
			}
		} catch (FileUploadException e1) {
			e1.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		} catch (DBConnctionException e) {
			response.getWriter().append(e.getErrorInfo());
			e.printStackTrace();
		}
	}

}
