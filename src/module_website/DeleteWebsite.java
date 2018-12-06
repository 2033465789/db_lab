package module_website;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.DBConnctionException;
import exceptions.MyException;
import javabeans.WebInfo;
import services.WebService;
import utils.InputCheckUtil;
import utils.StaticDataUtil;
import utils.StringUtil;

/**
 * Servlet implementation class DeleteWebsite
 */
@WebServlet("/DeleteWebsite")
public class DeleteWebsite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 用于判断是否存在要删除的网站
	private boolean exist = false;

	public DeleteWebsite() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String webName = request.getParameter("webName");
		try {
			if (InputCheckUtil.isOK(webName)) {
				try {
					exist = false;
					if (deleteWeb(webName)) {
						response.getOutputStream().write("success".getBytes("utf-8"));
					} else if (exist == false) {
						response.getOutputStream().write("不存在该网址信息".getBytes("utf-8"));
					} else {
						response.getOutputStream().write("服务器出错".getBytes("utf-8"));
					}
				} catch (MyException e) {
					response.getOutputStream().write(("删除失败:" + e.getErrorInfo()).getBytes("utf-8"));
					e.printStackTrace();
				} catch (DBConnctionException e) {
					response.getWriter().append("无法连接数据库");
					e.printStackTrace();
				}
			} else {
				response.sendRedirect("main");
			}
		} catch (MyException e) {
			response.getWriter().append(e.getErrorInfo());
			e.printStackTrace();
		}
	}

	private boolean deleteWeb(String webName) throws MyException, DBConnctionException {
		List<WebInfo> webInfos = StaticDataUtil.getWebInfo();
		for (WebInfo webinfo : webInfos) {
			if (webinfo.getWebName().equals(webName)) {
				exist = true;
				// 1. 从缓存中移除
				webInfos.remove(webinfo);

				// 2. 移除图片，移动图片至指定文件夹
				String url = webinfo.getImgURL();
				// 获取图片名字
				String imgName = url.substring(url.indexOf("/") + 1);
				// 获取图片file对象
				StringUtil stringTool = StringUtil.getStringTool(getServletContext());
				// 图片源文件
				File imgSrcFile = new File(stringTool.getWebImgPath(), imgName);
				File deletedDirectory = new File(stringTool.getDeletedDirectory());
				if (!deletedDirectory.exists()) {
					deletedDirectory.mkdirs();
				}
				String deletedName = stringTool.getCurrentTime("yyyyMMddHHmmss") + "_" + imgSrcFile.getName();
				// 图片移除到的文件
				File deletedFile = new File(deletedDirectory, deletedName);
				imgSrcFile.renameTo(deletedFile);
				// 3. 从数据库中删除
				WebService service = null;
				try {
					service = new WebService();
					if (service.deleteWebInfo(webinfo)) {
						return true;
					} else {
						// 如果删除数据库失败，则回退缓存内容和图片
						deletedFile.renameTo(imgSrcFile);
						webInfos.add(webinfo);
						throw new MyException("数据库更新失败");
					}
				} catch (DBConnctionException e) {
					// 如果删除数据库失败，则回退缓存内容和图片
					deletedFile.renameTo(imgSrcFile);
					webInfos.add(webinfo);
					throw new DBConnctionException(e.getErrorInfo());
				} finally {
					if (service != null)
						service.close();
				}
			}
		}
		return false;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
