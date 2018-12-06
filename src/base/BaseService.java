package base;

import exceptions.DBConnctionException;

public abstract class BaseService
{
	protected BaseService() throws DBConnctionException
	{
		super();
	}

	public abstract boolean close();

	// 获取表的条目总数
	public static int getTableMAXId(String tableName) throws DBConnctionException
	{
		return BaseDao.getTableMAXId(tableName);
	}

	public static int getVisiteNum() throws DBConnctionException
	{
		return BaseDao.getVisiteNum();
	}

	public static boolean setVisiteNum(int newNum) throws DBConnctionException
	{
		return BaseDao.setVisiteNum(newNum);
	}
}
