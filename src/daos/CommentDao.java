package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import base.BaseDao;
import exceptions.DBConnctionException;
import javabeans.Comment;

public class CommentDao extends BaseDao
{
	public CommentDao() throws DBConnctionException
	{
		super();
	}

	private LinkedList<Comment> doQueryAsList(String sql, String id)
	{
		LinkedList<Comment> list = new LinkedList<Comment>();
		try
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				list.add(new Comment(rs.getString("id"), rs.getString("uid"), rs.getString("fid"),
						rs.getString("content"), rs.getString("createTime"), rs.getString("deleteTime")));
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return list;
	}

	public LinkedList<Comment> getItemById(String id)
	{
		String sql = "select * from comment where id= ?";
		return doQueryAsList(sql, id);
	}

	public LinkedList<Comment> getItemByUserId(String id)
	{
		String sql = "select * from comment where uid= ?";
		return doQueryAsList(sql, id);
	}

	public LinkedList<Comment> getItemByFileId(String id)
	{
		String sql = "select * from comment where fid= ?";
		return doQueryAsList(sql, id);
	}

	public boolean InsertCacheItems(ArrayList<Comment> cacheList)
	{
		String sql = "INSERT INTO comment(uid,fid,content,createTime,deleteTime) VALUES(?,?,?,?,?)";
		int res = 0;
		try
		{
			conn.setAutoCommit(false);
			for (Comment comment : cacheList)
			{
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setString(1, comment.getUid());
				pst.setString(2, comment.getFid());
				pst.setString(3, comment.getContent());
				pst.setString(4, comment.getCreateTime());
				pst.setString(5, comment.getDeleteTime());
				res += pst.executeUpdate();
			}

			// 手动提交
			conn.commit();

			// 回复自动提交
			conn.setAutoCommit(true);
		} catch (SQLException e)
		{
			try
			{
				conn.rollback();
			} catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return res == cacheList.size();
	}
}
