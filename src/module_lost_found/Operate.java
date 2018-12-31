package module_lost_found;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;

import base.BaseOperate;
import base.BaseService;
import daos.SharedDao;
import exceptions.DBConnctionException;
import javabeans.Good;
import javabeans.SharedResource;
import services.LostFindService;
import utils.CacheUtil;

@WebServlet("/Operate")
public class Operate extends BaseOperate {
	private static final long serialVersionUID = 1L;

	public boolean delFound(String id, String uid) throws DBConnctionException {
		LostFindService service = new LostFindService();
		boolean flag = false;
		int itemId = Integer.parseInt(id);
		if (itemId <= BaseService.getTableMAXId("lost")) {
			flag = service.deleteItemById(itemId);
		} else {
			flag = delGoodFromCache(CacheUtil.getCacheTool().getGoodsCache(),
					itemId);
		}
		service.close();
		return flag;
	}

	private boolean delGoodFromCache(ArrayList<Good> goodsCache, int itemId) {
		for (Good good : goodsCache) {
			if (good.getId() == itemId) {
				return goodsCache.remove(good);
			}
		}
		return false;
	}

	private boolean delSharedFromCache(ArrayList<SharedResource> cache,
			long itemId) {
		for (SharedResource item : cache) {
			if (item.getSid() == itemId) {
				return cache.remove(item);
			}
		}
		return false;
	}

	public boolean findLoster(String id, String uid)
			throws DBConnctionException {
		LostFindService service = new LostFindService();
		boolean flag = service.findLoster(Integer.parseInt(id));
		service.close();
		return flag;
	}

	public boolean delShared(String id, String uid)
			throws DBConnctionException {
		SharedDao service = new SharedDao();
		long itemId = Long.parseLong(id);
		try {
			if (itemId <= BaseService.getTableMAXId("shared")) {
				return service.deleteItem(uid, itemId);
			} else {
				return delSharedFromCache(
						CacheUtil.getCacheTool().getSharedCache(), itemId);
			}
		} finally {
			service.close();
		}
	}
}
