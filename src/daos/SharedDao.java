package daos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import base.BaseDao;
import exceptions.DBConnctionException;
import javabeans.SharedResource;
import javabeans.User;
import utils.StaticDataUtil;

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

	public ResultSet getPageShared(String page) {
		String sql = "select * from shared limit ?,?";
		long curpage = Long.parseLong(page) - 1;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setLong(1, curpage * StaticDataUtil.PAGE_FILE_SIZE);
			pst.setLong(2, StaticDataUtil.PAGE_FILE_SIZE);
			return pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public long getItemCount() {
		String sql = "select count(*) from shared";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet set = pst.executeQuery();
			set.next();
			return set.getLong(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<SharedResource> getAllItem() {
		String sql = "select * from shared";
		ArrayList<SharedResource> list = new ArrayList<>();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet set = pst.executeQuery();
			while (set.next()) {
				list.add(new SharedResource(set.getLong(1), set.getString(2),
						set.getString(3), set.getString(4), set.getString(5),
						set.getString(6), set.getString(7)));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteItem(String uid, long sid) {
		String sql = "call deleteShared(?,?)";
		try {
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, uid);
			cs.setLong(2, sid);
			return cs.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
