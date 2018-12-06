package module_website;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;

import exceptions.DBConnctionException;
import exceptions.MyException;
import javabeans.User;
import javabeans.WebInfo;
import services.WebService;
import utils.FileUploadUtil;
import utils.RequestUtil;
import utils.StaticDataUtil;
import utils.StringUtil;

/**
 * Servlet implementation class AddWebsite
 */
@WebServlet("/AddWebsite")
public class AddWebsite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddWebsite() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取各种工具
		FileUploadUtil fileUploadUtil = new FileUploadUtil(request, response);
		StringUtil stringUtil = StringUtil.getStringTool(request.getServletContext());
		RequestUtil requestUtil = RequestUtil.getRequestTool(request);
		List<WebInfo> webInfos = null;
		WebService service = null;
		try {
			webInfos = StaticDataUtil.getWebInfo();
			User user = requestUtil.getUser();
			if (user == null) {
				request.getRequestDispatcher("LogIn").forward(request, response);
				return;
			}
			String filePath = stringUtil.getWebImgPath();
			HashMap<String, String> map = fileUploadUtil.dealUploadFile(filePath, null);
			String webName = map.get("webName");
			String webDesc = map.get("webDesc");
			String aimURL = map.get("webURL");
			String imgURL = "webImgs/" + map.get("fileName");
			service = new WebService();
			WebInfo webinfo = new WebInfo(0, aimURL, imgURL, webName, webDesc);
			if (service.addWebInfo(webinfo) && webInfos.add(webinfo)) {
				response.getOutputStream().write("添加成功".getBytes("utf-8"));
			} else {
				response.getOutputStream().write("添加失败".getBytes("utf-8"));
			}
		} catch (FileUploadException e1) {
			e1.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		} catch (DBConnctionException e2) {
			response.getWriter().append(e2.getErrorInfo());
			e2.printStackTrace();
		} finally {
			if (service != null)
				service.close();
		}
	}

}
