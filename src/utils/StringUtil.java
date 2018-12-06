package utils;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import javabeans.Good;

public class StringUtil {
	private ServletContext app;

	private String imagesPath;
	private String sharedPath;
	private String webImgPath;
	private String deletedDirectory;
	private static StringUtil stringTool;

	private StringUtil(ServletContext sc) {
		app = sc;
		webImgPath = app.getRealPath("") + "webImgs/";
		imagesPath = app.getRealPath("") + "images/";
		sharedPath = app.getRealPath("") + "sharedResource/";
		deletedDirectory = app.getRealPath("") + "deletedDirectory/";
	}

	public boolean JspRequest(String name) {
		return (name.length() > 0 && name.charAt(0) >= 'a' && name.charAt(0) <= 'z');
	}

	public String getDeletedDirectory() {
		return deletedDirectory;
	}

	public String getSharedPath() {
		return sharedPath;
	}

	public String getWebImgPath() {
		return webImgPath;
	}

	public String getImagespath() {
		return imagesPath;
	}

	public static StringUtil getStringTool(ServletContext sc) {
		if (stringTool == null)
			stringTool = new StringUtil(sc);
		return stringTool;
	}

	/*
	 * @para formatter yyyyMMddHHmmssSSS 年-毫秒
	 */

	public String getCurrentTime(String formatter) {

		long currentTime = System.currentTimeMillis();

		SimpleDateFormat sdf = new SimpleDateFormat(formatter);

		Date date = new Date(currentTime);

		return sdf.format(date);
	}

	public boolean goodContainInfo(Good good, String searchInfo) {
		String[] infos = searchInfo.split(" ");
		for (String info : infos) {
			if (good.getNumberInfo().contains(info) || good.getLosterName().contains(info)
					|| good.getGoodDesc().contains(info) || good.getFoundAddr().contains(info)) {
				return true;
			}
		}
		return false;
	}

	public void makeTr(StringBuilder res, Good good) throws SQLException {
		res.append("<tr>");
		res.append("<td>").append(good.getLosterName()).append("</td>");
		res.append("<td>").append(good.getNumberInfo()).append("</td>");
		res.append("<td>").append(good.getGoodDesc()).append("</td>");
		res.append("<td>").append(good.getFoundAddr()).append("</td>");
		res.append("<td>").append(good.getFinderName()).append("</td>");
		res.append("<td>").append(good.getFinderPhone()).append("</td>");
		res.append("<td>").append(good.getFinderQQorWX()).append("</td>");
		res.append("<td><img src='").append(good.getImagePath()).append("'></td>");
		res.append("</tr> ");
	}

}
