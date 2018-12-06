package services;

import java.sql.ResultSet;

import base.BaseService;
import daos.UserDao;
import exceptions.DBConnctionException;
import javabeans.DormitoryInfo;
import javabeans.User;

public class UserService extends BaseService
{
	private UserDao dao;

	public UserService() throws DBConnctionException
	{
		dao = new UserDao();
	}

	public boolean containUser(String id, String pwd) throws Exception
	{
		return dao.containUser(id, pwd);
	}

	public boolean idExisted(String id)
	{
		return dao.idExisted(id);
	}

	public boolean insertUser(String userId, String pwd)
	{
		return dao.insertUser(userId, pwd);
	}

	public ResultSet getUserInfo(String userId)
	{
		return dao.getUserInfo(userId);
	}

	public boolean AlterDormtoryInfo(String userId, DormitoryInfo dormInfo)
	{
		return dao.AlterDormtoryInfo(userId, dormInfo);
	}

	public boolean insertUserInfo(User user)
	{
		return dao.insertUserInfo(user);
	}

	@Override
	public boolean close()
	{
		return dao.close();
	}
	
	
}
