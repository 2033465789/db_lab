package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import base.BaseDao;
import exceptions.DBConnctionException;
import javabeans.Good;
import javabeans.User;

public class LostFindDao extends BaseDao {
	public LostFindDao() throws DBConnctionException {
		super();
	}

	public ResultSet getAllItems() {
		String sql = "select * from lost";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			return pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ResultSet getAllUserItems(User user) {
		String sql = "select * from lost where uid=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUid());
			return pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteItemById(long id) {
		String sql = "delete from lost where lid=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setLong(1, id);
			return pst.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean findLoster(long id) {
		return deleteItemById(id);
	}

	public ResultSet getAllItemsOrderByDesc() {
		String sql = "select * from lost order by lid desc";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			return pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 插入多条缓存记录
	public boolean InsertCacheItems(ArrayList<Good> insertCache) {
		int res = 0;
		try {
			String sql = "INSERT INTO lost(uid,numberInfo,losterName,goodDesc,foundAddr,finderName,finderPhone,finderQQorWX,ImagePath) VALUES(?,?,?,?,?,?,?,?,?)";
			// 取消自动提交
			conn.setAutoCommit(false);
			for (Good good : insertCache) {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, good.getFinderId());
				pst.setString(2, good.getNumberInfo());
				pst.setString(3, good.getLosterName());
				pst.setString(4, good.getGoodDesc());
				pst.setString(5, good.getFoundAddr());
				pst.setString(6, good.getFinderName());
				pst.setString(7, good.getFinderPhone());
				pst.setString(8, good.getFinderQQorWX());
				pst.setString(9, good.getImagePath());
				res += pst.executeUpdate();
			}
			// 手动提交
			conn.commit();
			// 还原自动提交
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// 出错则回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return res == insertCache.size();
	}
}
