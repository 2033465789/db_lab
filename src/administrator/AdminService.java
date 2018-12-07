package administrator;

import daos.ConnectPoolManager;
import exceptions.DBConnctionException;
import exceptions.MyException;
import javabeans.User;
import utils.CacheUtil;

//只能在该包内访问
class AdminService
{

	static boolean closeConnection(User user)
	{
		if (ConnectPoolManager.denyUserConnect(user))
		{
			return true;
		} else
		{
			return false;
		}
	}

	static boolean openConnection(User user)
	{
		if (ConnectPoolManager.permitUserConnect(user))
		{
			return true;
		} else
		{
			return false;
		}
	}

	static boolean FlushCache(User user) throws MyException, DBConnctionException
	{
		if (user.hasBasePermission())
		{
			// 写缓存
			CacheUtil cacheTool = CacheUtil.getCacheTool();
			return cacheTool.FlushAllCache();
		} else
		{
			throw new MyException("非管理员，非法进行该操作");
		}
	}
}
