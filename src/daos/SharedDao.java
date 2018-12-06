package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import base.BaseDao;
import exceptions.DBConnctionException;
import javabeans.SharedResource;
import javabeans.User;

public class SharedDao extends BaseDao {
	public SharedDao() throws DBConnctionException {
		super();
	}

	public ResultSet getAllResourceByUser(User user) {
		String sql = "select * from shared where uid=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUid());
			return pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteItemById(int id) {
		String sql = "delete from shared where sid=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			return pst.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean InsertCacheItmes(ArrayList<SharedResource> sharedCache) {
		int res = 0;
		try {
			String sql = "INSERT INTO shared(fileName,uid,uploadTime,filePath,fileType,fileDesc) VALUES(?,?,?,?,?,?)";
			// 取消自动提交
			conn.setAutoCommit(false);
			for (SharedResource shared : sharedCache) {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, shared.getFileName());
				pst.setString(2, shared.getUploadUser());
				pst.setString(3, shared.getUploadTime());
				pst.setString(4, shared.getFilePath());
				pst.setString(5, shared.getFileType());
				pst.setString(6, shared.getFileDesc());
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
		return res == sharedCache.size();
	}

	public SharedResource getItemById(String id) {
		String sql = "select * from shared where sid = ?";
		SharedResource resource = null;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, (int) Long.parseLong(id));
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				resource = new SharedResource(rs.getInt("sid"),
						rs.getString("fileName"), rs.getString("uid"),
						rs.getString("uploadTime"), rs.getString("filePath"),
						rs.getString("fileType"), rs.getString("fileDesc"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resource;
	}

	public ResultSet getAllResource() {
		String sql = "select * from shared";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			return pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
