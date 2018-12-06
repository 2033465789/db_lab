package services;

import java.sql.ResultSet;

import base.BaseService;
import daos.WebDao;
import exceptions.DBConnctionException;
import javabeans.WebInfo;

public class WebService extends BaseService
{
	private WebDao dao;

	public WebService() throws DBConnctionException
	{
		dao = new WebDao();
	}

	public ResultSet getWebInfo()
	{
		return dao.getWebInfo();
	}

	public boolean addWebInfo(WebInfo webinfo)
	{
		return dao.addWebInfo(webinfo);
	}

	public boolean deleteWebInfo(WebInfo webinfo)
	{
		return dao.deleteWebInfo(webinfo);
	}

	@Override
	public boolean close()
	{
		return dao.close();
	}
	
	
}
