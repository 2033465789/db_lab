package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.BaseDao;
import exceptions.DBConnctionException;
import javabeans.DormitoryInfo;
import javabeans.User;

public class UserDao extends BaseDao
{
	public UserDao() throws DBConnctionException
	{
		super();
	}

	public boolean containUser(String id, String pwd) throws Exception
	{
		String sql = "select * from users where id = ? and pwd = ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, id);
		pst.setString(2, pwd);
		return pst.executeQuery().next();
	}

	public boolean idExisted(String id)
	{
		String sql = "select * from users where id = ?";
		try
		{
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			ResultSet res = pst.executeQuery();
			return res != null && res.next();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean insertUser(String userId, String pwd)
	{
		String sql = "insert into users(id,pwd) values(?,?)";
		try
		{
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userId);
			pst.setString(2, pwd);
			boolean users = pst.executeUpdate() == 1;
			pst.close();
			boolean userinfo = false;
			if (users)
				userinfo = insertUserInfo(new User(userId, null, null, null, "500", 1));
			return (users && userinfo);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public ResultSet getUserInfo(String userId)
	{
		String sql = "select * from userinfo where userId=?";
		ResultSet set = null;
		try
		{
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userId);
			set = pst.executeQuery();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return set;
	}

	public boolean AlterDormtoryInfo(String userId, DormitoryInfo dormInfo)
	{
		String sql = "update userinfo set userDormAddr=?,userDormWhich=?,userDormTag=? where userId = " + userId;
		try
		{
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, dormInfo.getDormAddr());
			pst.setString(2, dormInfo.getDormWhich());
			pst.setString(3, dormInfo.getDormTag());
			return pst.executeUpdate() == 1;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean insertUserInfo(User user)
	{
		String sql = "insert into userinfo(userId,userDormAddr,userDormWhich,userDormTag,cardBalance,userPermission) values(?,?,?,?,?,?)";
		DormitoryInfo dormInfo = user.getUserDormInfo();
		try
		{
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUserId());
			pst.setString(2, dormInfo.getDormAddr());
			pst.setString(3, dormInfo.getDormWhich());
			pst.setString(4, dormInfo.getDormTag());
			pst.setString(5, user.getCardBalance());
			pst.setInt(6, user.getUserPermission());
			return pst.executeUpdate() == 1;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
}
