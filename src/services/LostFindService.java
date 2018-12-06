package services;

import java.sql.ResultSet;
import java.util.ArrayList;

import base.BaseService;
import daos.LostFindDao;
import exceptions.DBConnctionException;
import javabeans.Good;
import javabeans.User;

public class LostFindService extends BaseService
{
	private LostFindDao dao;

	public LostFindService() throws DBConnctionException
	{
		dao = new LostFindDao();
	}

	public ResultSet getAllItems()
	{
		return dao.getAllItems();
	}

	public ResultSet getAllUserItems(User user)
	{
		return dao.getAllUserItems(user);
	}

	public boolean findLoster(int id)
	{
		return dao.findLoster(id);
	}

	public boolean deleteItemById(int id)
	{
		return dao.deleteItemById(id);
	}

	public ResultSet getAllItemsOrderByDesc()
	{
		return dao.getAllItemsOrderByDesc();
	}

	// 插入多条缓存记录
	public boolean InsertCacheItems(ArrayList<Good> insertCache)
	{
		return dao.InsertCacheItems(insertCache);
	}

	@Override
	public boolean close()
	{
		return dao.close();
	}
}
