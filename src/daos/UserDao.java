package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.BaseDao;
import exceptions.DBConnctionException;
import javabeans.User;
import utils.StaticDataUtil;

public class UserDao extends BaseDao {
	public UserDao() throws DBConnctionException {
		super();
	}

	public boolean containUser(String uid, String pwd) {
		String sql = "select count(*) from user where uid = ? and pwd = ?";
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, uid);
			pst.setString(2, pwd);
			ResultSet res = pst.executeQuery();
			res.next();
			return res.getInt(1) == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean idExisted(String uid) {
		String sql = "select * from user where uid = ?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, uid);
			ResultSet res = pst.executeQuery();
			return res.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean insertUser(User user) {
//		 uid varchar(20), 	 #用户名 
//         pwd varchar(20) not null,      	 #密码
//         nickname varchar(20) not null, 	 #昵称
//         permission varchar(20) not null,   #权限等级
//         registerTime datetime not null,     #注册时间
		String sql = "insert into user(uid,pwd,nickname,permission,registerTime) values(?,?,?,?,now())";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUid());
			pst.setString(2, user.getPwd());
			pst.setString(3, user.getNickname());
			pst.setInt(4, StaticDataUtil.USER);
			return pst.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ResultSet getUserInfo(String userId) {
		String sql = "select * from user where uid = ?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userId);
			return pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
