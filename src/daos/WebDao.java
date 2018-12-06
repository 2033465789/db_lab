package daos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import base.BaseDao;
import exceptions.DBConnctionException;
import javabeans.WebInfo;

public class WebDao extends BaseDao {
	public WebDao() throws DBConnctionException {
		super();
	}

	public ResultSet getWebInfo() {
		String sql = "select * from website";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			return pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean addWebInfo(WebInfo webinfo, String uid) {
		// String sql = "insert into website(aimURL,imgURL,webName,webDesc) values(?,?,?,?)";
		String sql = "{call addWebsite(?,?,?,?,?,?)}";
		try {
			CallableStatement pst = conn.prepareCall(sql);
			pst.setString(1, uid);
			pst.setString(2, webinfo.getAimURL());
			pst.setString(3, webinfo.getImgURL());
			pst.setString(4, webinfo.getWebName());
			pst.setString(5, webinfo.getWebDesc());
			pst.registerOutParameter(6, Types.BOOLEAN);
			pst.execute();
			return pst.getBoolean(6);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteWebInfo(WebInfo webinfo, String uid) {
		String sql = "call deleteWebsite(?,?,?)";
		try {
			CallableStatement pst = conn.prepareCall(sql);
			pst.setString(1, uid);
			pst.setLong(2, webinfo.getId());
			pst.registerOutParameter(3, Types.BOOLEAN);
			pst.execute();
			return pst.getBoolean(3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) throws DBConnctionException {
		WebDao dao = new WebDao();
		System.out.println(dao.addWebInfo(
				new WebInfo(1, null, null, null, null), "20161060137"));
	}
}
