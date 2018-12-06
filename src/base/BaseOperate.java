package base;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.RequestUtil;
import utils.StaticDataUtil;

public class BaseOperate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BaseOperate() {
		super();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestUtil requestUtil = new RequestUtil(request);
		if (!requestUtil.isOnline()) {
			response.getWriter().write(StaticDataUtil.OFFLINE);
			return;
		}

		// 获取参数中的方法
		String method = request.getParameter("method");
		// 获取要操作的Item的id
		String id = request.getParameter("id");
		try {
			// 通过反射获取方法
			Method m = getClass().getDeclaredMethod(method, String.class);
			// 执行方法，并确定操作是否成功
			boolean res = (boolean) m.invoke(this, id);

			// 返回响应数据
			if (res) {
				response.getWriter().append("success");
			} else {
				response.getWriter().append("fail");
			}
		} catch (Exception e) {
			response.getWriter().append("数据库连接错误");
			e.printStackTrace();
		}
	}
}
