package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import base.BaseDao;
import exceptions.DBConnctionException;
import javabeans.CommentFile;
import utils.StaticDataUtil;

public class CommentDao extends BaseDao {
	public CommentDao() throws DBConnctionException {
		super();
	}

	private LinkedList<CommentFile> doQueryAsList(String sql, String id) {
		LinkedList<CommentFile> list = new LinkedList<CommentFile>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new CommentFile(rs.getLong("cid"), rs.getString("uid"),
						rs.getLong("sid"), rs.getString("content"),
						rs.getDate("createTime").toString()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public LinkedList<CommentFile> getItemById(String id) {
		String sql = "select * from commentFile where cid= ?";
		return doQueryAsList(sql, id);
	}

	public LinkedList<CommentFile> getItemByUserId(String id) {
		String sql = "select * from commentFile where uid= ?";
		return doQueryAsList(sql, id);
	}

	public LinkedList<CommentFile> getItemByFileId(String id) {
		String sql = "select * from commentFile where sid= ?";
		return doQueryAsList(sql, id);
	}

	public boolean InsertCacheItems(ArrayList<CommentFile> cacheList) {
		String sql = "insert into commentFile(uid,sid,content,createTime) value(?,?,?,curdate())";
		int res = 0;
		try {
			conn.setAutoCommit(false);
			for (CommentFile comment : cacheList) {
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, comment.getUid());
				pst.setLong(2, comment.getSid());
				pst.setString(3, comment.getContent());
				res += pst.executeUpdate();
			}
			// 手动提交
			conn.commit();
			// 回复自动提交
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return res == cacheList.size();
	}

	public LinkedList<CommentFile> getItemAsPageByFileId(String sid,
			String page) {
		long curpage = Long.parseLong(page) - 1;
		String sql = "select * from commentFile where sid= ? limit "
				+ curpage * StaticDataUtil.PAGE_COMMENT_SIZE + ","
				+ StaticDataUtil.PAGE_COMMENT_SIZE + "";
		return doQueryAsList(sql, sid);
	}

	public long getItemCount(String sid) {
		try {
			String sql = "select count(*) from commentFile where sid= ? ";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, sid);
			ResultSet set = pst.executeQuery();
			set.next();
			return set.getLong(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
