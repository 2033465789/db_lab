package services;

import java.sql.ResultSet;
import java.sql.SQLException;

import base.BaseService;
import daos.UserDao;
import exceptions.DBConnctionException;
import javabeans.User;

public class UserService extends BaseService {
	private UserDao dao;

	public UserService() throws DBConnctionException {
		dao = new UserDao();
	}

	public boolean containUser(String id, String pwd) throws Exception {
		return dao.containUser(id, pwd);
	}

	public boolean idExisted(String id) {
		return dao.idExisted(id);
	}

	public boolean insertUser(User user) {
		return dao.insertUser(user);
	}

	public User getUserInfo(String userId) {
		ResultSet reset = dao.getUserInfo(userId);
		try {
			reset.next();
			return new User(reset.getString("uid"), null,
					reset.getString("nickname"), reset.getInt("permission"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean close() {
		return dao.close();
	}

}
