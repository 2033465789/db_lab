package utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import exceptions.MyException;

public class FileUploadUtil {
	private HttpServletRequest request;
	private HttpServletResponse response;
	public long uploadLength = 0;

	public FileUploadUtil(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}

	/*
	 * @para filepath 文件保存位置@
	 * 
	 * @return request中的文件信息
	 */

	public HashMap<String, String> dealUploadFile(String filePath, String newFileName)
			throws FileUploadException, UnsupportedEncodingException, IOException, MyException {
		HashMap<String, String> map = new HashMap<String, String>();
		if (ServletFileUpload.isMultipartContent(request)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			if (items.isEmpty()) {
				throw new MyException("上传的数据存在错误");
			}
			for (FileItem item : items) {
				if (item.isFormField()) {// 如果是普通表单字段
					String name = item.getFieldName();
					String value = item.getString("utf-8");
					if (!InputCheckUtil.isOK(value))
						throw new MyException("上传的数据存在错误");
					map.put(name, value);
				} else {// 如果是文件
					if (!InputCheckUtil.isOK(filePath))
						throw new MyException("上传的数据存在错误");
					String fileName = item.getName();
					map.put("fileName", fileName);
					// 子线程中写入，避免大文件造成阻塞
					new Thread(new Runnable() {
						public void run() {
							File file = new File(filePath);
							if (!file.exists()) {
								file.mkdir();
							}
							try {
								File aimFile = null;
								if (newFileName == null) {
									aimFile = new File(file, fileName);
								} else {
									aimFile = new File(file, newFileName);
								}
								item.write(aimFile);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}).start();
				}
			}
		} else {
			response.sendRedirect("main");
			throw new MyException("不是一个文件请求");
		}
		return map;
	}
}
