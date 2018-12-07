package module_lost_found;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;

import base.BaseOperate;
import base.BaseService;
import exceptions.DBConnctionException;
import javabeans.Good;
import javabeans.SharedResource;
import services.LostFindService;
import services.SharedService;
import utils.CacheUtil;

@WebServlet("/Operate")
public class Operate extends BaseOperate
{
	private static final long serialVersionUID = 1L;

	public boolean delFound(String id) throws DBConnctionException
	{
		LostFindService service = new LostFindService();
		boolean flag = false;
		int itemId = Integer.parseInt(id);
		if (itemId <= BaseService.getTableMAXId("lost"))
		{
			flag = service.deleteItemById(itemId);
		} else
		{
			flag = delGoodFromCache(CacheUtil.getCacheTool().getGoodsCache(), itemId);
		}
		service.close();
		return flag;
	}

	private boolean delGoodFromCache(ArrayList<Good> goodsCache, int itemId)
	{
		for (Good good : goodsCache)
		{
			System.out.println(good.getId() + "   " + itemId);
			if (good.getId() == itemId)
			{
				return goodsCache.remove(good);
			}
		}
		return false;
	}

	private boolean delSharedFromCache(ArrayList<SharedResource> cache, int itemId)
	{
		for (SharedResource item : cache)
		{
			System.out.println(item.getSid() + "   " + itemId);
			if (item.getSid() == itemId)
			{
				return cache.remove(item);
			}
		}
		return false;
	}

	public boolean findLoster(String id) throws DBConnctionException
	{
		LostFindService service = new LostFindService();
		boolean flag = service.findLoster(Integer.parseInt(id));
		service.close();
		return flag;
	}

	public boolean delShared(String id) throws DBConnctionException
	{
		SharedService service = new SharedService();
		boolean flag = false;
		int itemId = Integer.parseInt(id);
		if (itemId <= BaseService.getTableMAXId("shared"))
		{
			flag = service.deleteItemById(itemId);
		} else
		{
			flag = delSharedFromCache(CacheUtil.getCacheTool().getSharedCache(), itemId);
		}
		service.close();
		return flag;
	}
}
