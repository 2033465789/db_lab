package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.DBConnctionException;
import javabeans.WebInfo;
import services.WebService;

public class StaticDataUtil {
	private static List<WebInfo> webInfos;
	// 用户为管理员管理员的最小权限等级
	public final static int MIN_ADMIN = 2;
	// 用户为管理员管理员的最大权限等级
	public final static int SUPER_ADMIN = 3;
	public final static int PAGE_SIZE = 4;
	// 普通用户
	public final static int USER = 1;

	// 默认文件最大上传大小
	public final static int DEAFAULT_FILE_UPLOAD_ELNGTH = 1024 * 1024 * 10;

	// 用户未登录
	public final static String OFFLINE = "offline";

	// 从数据库加载，常用网址信息
	private static void loadWebInfos() throws DBConnctionException {
		webInfos = new ArrayList<WebInfo>();
		ResultSet rset = new WebService().getWebInfo();
		try {
			while (rset.next()) {
				webInfos.add(new WebInfo(rset.getInt(1), rset.getString(2),
						rset.getString(3), rset.getString(4),
						rset.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 获取常用网址信息
	public static List<WebInfo> getWebInfo() throws DBConnctionException {
		if (webInfos == null) {
			loadWebInfos();
		}
		return webInfos;
	}
}
