package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.BaseDao;
import exceptions.DBConnctionException;
import javabeans.WebInfo;

public class WebDao extends BaseDao
{
	public WebDao() throws DBConnctionException
	{
		super();
	}

	public ResultSet getWebInfo()
	{
		String sql = "select * from webinfo";
		try
		{
			PreparedStatement pst = conn.prepareStatement(sql);
			return pst.executeQuery();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public boolean addWebInfo(WebInfo webinfo)
	{
		String sql = "insert into webinfo(aimURL,imgURL,webName,webDesc) values(?,?,?,?)";
		try
		{
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, webinfo.getAimURL());
			pst.setString(2, webinfo.getImgURL());
			pst.setString(3, webinfo.getWebName());
			pst.setString(4, webinfo.getWebDesc());
			return pst.executeUpdate() == 1;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteWebInfo(WebInfo webinfo)
	{
		String sql = "delete from webinfo where webName = ?";
		try
		{
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, webinfo.getWebName());
			return pst.executeUpdate() == 1;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

}
