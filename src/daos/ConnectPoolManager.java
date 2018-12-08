package daos;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import app.AppInfos;
import exceptions.DBConnctionException;
import exceptions.MyException;
import javabeans.User;

public class ConnectPoolManager {
	private static ComboPooledDataSource dataSource = null;
	static {
		dataSource = new ComboPooledDataSource();
	}

	public static Connection getConnection() throws DBConnctionException {
		Connection conn = null;
		try {
			if (dataSource == null) {
				throw new DBConnctionException("数据库初始化错误");
			}
			if (AppInfos.getUserConnectStatus()) {
				conn = dataSource.getConnection();
			} else {
				throw new DBConnctionException("数据库暂时关闭连接。。。。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static boolean denyUserConnect(User user) {
		if (user != null && user.hasBasePermission()) {
			AppInfos.setUserConnectStatus(false);
			return true;
		} else {
			try {
				throw new MyException("非管理员，无法进行该操作");
			} catch (MyException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static boolean permitUserConnect(User user) {
		if (user != null && user.hasBasePermission()) {
			AppInfos.setUserConnectStatus(true);
			return true;
		} else {
			try {
				throw new MyException("非管理员，无法进行该操作");
			} catch (MyException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static void closeConnect(Connection connection) {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean getUserConnectionStatus() {
		return AppInfos.getUserConnectStatus();
	}
}
