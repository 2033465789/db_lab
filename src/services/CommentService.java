package services;

import java.util.ArrayList;
import java.util.LinkedList;

import base.BaseService;
import daos.CommentDao;
import exceptions.DBConnctionException;
import javabeans.CommentFile;

public class CommentService extends BaseService
{
	CommentDao dao;

	public CommentService() throws DBConnctionException
	{
		dao = new CommentDao();
	}

	public LinkedList<CommentFile> getItemById(String id)
	{
		return dao.getItemById(id);
	}

	public LinkedList<CommentFile> getItemByUserId(String id)
	{
		return dao.getItemByUserId(id);
	}

	public LinkedList<CommentFile> getItemByFileId(String id)
	{
		return dao.getItemByFileId(id);
	}

	public boolean InsertCacheItems(ArrayList<CommentFile> cacheList)
	{
		return dao.InsertCacheItems(cacheList);
	}

	@Override
	public boolean close()
	{
		return dao.close();
	}

}
