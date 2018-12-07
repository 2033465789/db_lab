package services;

import java.sql.ResultSet;
import java.util.ArrayList;

import base.BaseService;
import daos.SharedDao;
import exceptions.DBConnctionException;
import javabeans.SharedResource;
import javabeans.User;

public class SharedService extends BaseService
{
	private SharedDao dao;

	public SharedService() throws DBConnctionException
	{
		dao = new SharedDao();
	}

	public boolean InsertCacheItmes(ArrayList<SharedResource> sharedCache)
	{
		return dao.InsertCacheItmes(sharedCache);
	}

	public ResultSet getAllResource()
	{
		return dao.getAllResource();
	}

	public ResultSet getAllResourceByUser(User user)
	{
		return dao.getAllResourceByUser(user);
	}

	public SharedResource getItemById(String id)
	{
		return dao.getItemById(id);
	}

	public boolean deleteItemById(int id)
	{
		return dao.deleteItemById(id);
	}

	@Override
	public boolean close()
	{
		return dao.close();
	}

	
}
