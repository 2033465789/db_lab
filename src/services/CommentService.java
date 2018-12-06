package services;

import java.util.ArrayList;
import java.util.LinkedList;

import base.BaseService;
import daos.CommentDao;
import exceptions.DBConnctionException;
import javabeans.Comment;

public class CommentService extends BaseService
{
	CommentDao dao;

	public CommentService() throws DBConnctionException
	{
		dao = new CommentDao();
	}

	public LinkedList<Comment> getItemById(String id)
	{
		return dao.getItemById(id);
	}

	public LinkedList<Comment> getItemByUserId(String id)
	{
		return dao.getItemByUserId(id);
	}

	public LinkedList<Comment> getItemByFileId(String id)
	{
		return dao.getItemByFileId(id);
	}

	public boolean InsertCacheItems(ArrayList<Comment> cacheList)
	{
		return dao.InsertCacheItems(cacheList);
	}

	@Override
	public boolean close()
	{
		return dao.close();
	}

}
