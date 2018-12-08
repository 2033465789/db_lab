package base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import daos.ConnectPoolManager;
import exceptions.DBConnctionException;

public class BaseDao {
	protected Connection conn;

	protected BaseDao() throws DBConnctionException {
		conn = ConnectPoolManager.getConnection();
		if (conn == null)
			throw new DBConnctionException("无法连接数据库");
	}

	public static int getTableMAXId(String tableName)
			throws DBConnctionException {
		String id = tableName.charAt(0) + "id";
		String sql = "select max(" + id + ") from " + tableName;
		Connection con = null;
		try {
			con = ConnectPoolManager.getConnection();
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rset = pst.executeQuery();
			if (rset.next()) {
				return rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectPoolManager.closeConnect(con);
		}
		return 0;
	}

	public int exe_sql(String sql) throws DBConnctionException {
		return 0;
	}

	public static int getVisiteNum() throws DBConnctionException {
		String sql = "select visiteNum from app";
		Connection con = null;
		try {
			con = ConnectPoolManager.getConnection();
			if (con == null)
				throw new DBConnctionException("无法连接数据库");
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rset = pst.executeQuery();
			if (rset.next()) {
				return rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectPoolManager.closeConnect(con);
		}
		return 0;
	}

	public static boolean setVisiteNum(int newNum) throws DBConnctionException {
		String sql = "update app set visiteNum = ?";
		Connection con = null;
		try {
			con = ConnectPoolManager.getConnection();
			if (con == null)
				throw new DBConnctionException("无法连接数据库");
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, newNum);
			return pst.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectPoolManager.closeConnect(con);
		}
		return false;
	}
	// 回收连接
	protected void finalize() throws Throwable {
		if (!conn.isClosed())
			System.err.println("成功回收忘记关闭的连接");
		ConnectPoolManager.closeConnect(conn);
		super.finalize();
	}

	public boolean close() {
		try {
			ConnectPoolManager.closeConnect(conn);
			return conn.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
