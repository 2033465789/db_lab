package utils;

import java.util.ArrayList;
import java.util.Collection;

import base.BaseService;
import exceptions.DBConnctionException;
import exceptions.MyException;
import javabeans.Comment;
import javabeans.Good;
import javabeans.SharedResource;
import services.CommentService;
import services.LostFindService;
import services.SharedService;

public class CacheUtil {
	// 单例
	private static CacheUtil ct;

	static {
		ct = new CacheUtil();
	}

	// 缓存大小
	private final int GOODSCACHE_MAX_SIZE = 50;
	private final int SHAREDCACHE_MAX_SIZE = 50;
	private final int COMMENTCACHE_MAX_SIZE = 50;

	/*
	 * private int lastSharedCount; private int lastLostCount; private int
	 * lastCommentCount;
	 */

	// 缓存列表
	private ArrayList<Good> goodsCache;
	private ArrayList<SharedResource> sharedCache;
	private ArrayList<Comment> commentCache;

	// 构造方法私有化
	private CacheUtil() {
		// 获取一个线程安全的缓存链表
		goodsCache = new ArrayList<Good>(GOODSCACHE_MAX_SIZE + 3);
		sharedCache = new ArrayList<SharedResource>(SHAREDCACHE_MAX_SIZE + 3);
		commentCache = new ArrayList<Comment>(COMMENTCACHE_MAX_SIZE + 3);
	}

	// 返回单个实例
	public static CacheUtil getCacheTool() {
		return ct;
	}

	public ArrayList<SharedResource> getSharedCache() {
		return sharedCache;
	}

	public ArrayList<Comment> getCommentsCache() {
		return commentCache;
	}

	public ArrayList<Good> getGoodsCache() {
		return goodsCache;
	}

	public boolean addItemToSharedCache(SharedResource e) throws MyException, DBConnctionException {
		// 加锁
		synchronized (sharedCache) {
			if (sharedCache.add(e)) {
				// 每当缓存元素满足一定数量时，将缓存内容写入数据库
				if (sharedCache.size() >= SHAREDCACHE_MAX_SIZE) {
					flushdsharedCache();
					if (sharedCache.size() == 0)
						return true;
					else {
						throw new MyException("写入缓存失败\n" + "缓存大小 :" + sharedCache.size());
					}
				}
				return true;
			}
			return false;
		}
	}

	public boolean addItemToCommentCache(Comment e) throws MyException, DBConnctionException {
		// 加锁
		synchronized (commentCache) {
			if (commentCache.add(e)) {
				// 每当缓存元素满足一定数量时，将缓存内容写入数据库
				if (commentCache.size() >= COMMENTCACHE_MAX_SIZE) {
					flushCommentCache();
					if (commentCache.size() == 0)
						return true;
					else {
						throw new MyException("写入缓存失败");
					}
				}
				return true;
			}
			return false;
		}
	}

	public boolean addItemToGoodsCache(Good e) throws MyException, DBConnctionException {
		// 加锁
		synchronized (goodsCache) {
			if (goodsCache.add(e)) {
				// 每当缓存元素满足一定数量时，将缓存内容写入数据库
				if (goodsCache.size() >= GOODSCACHE_MAX_SIZE) {
					// 写数据库
					flushGoodsCache();
					if (goodsCache.size() == 0)
						return true;
					else {
						throw new MyException("写入缓存异常");
					}
				}
				return true;
			}
			return false;
		}
	}

	public static int getNewItemId(Collection<?> coll, String tableName) throws DBConnctionException {
		return coll.size() + BaseService.getTableMAXId(tableName) + 1;
	}

	private void flushCommentCache() throws DBConnctionException {
		ArrayList<Comment> tmp = commentCache;
		commentCache = new ArrayList<Comment>(COMMENTCACHE_MAX_SIZE + 3);
		CommentService service = new CommentService();
		if (!service.InsertCacheItems(tmp)) {
			// 恢复缓存
			commentCache = tmp;
		}
		service.close();
	}

	private void flushGoodsCache() throws DBConnctionException {
		ArrayList<Good> tmp = goodsCache;
		goodsCache = new ArrayList<Good>(GOODSCACHE_MAX_SIZE + 3);
		LostFindService service = new LostFindService();
		if (!service.InsertCacheItems(tmp)) {
			// 恢复缓存
			goodsCache = tmp;
		}
		service.close();
	}

	private void flushdsharedCache() throws DBConnctionException {
		ArrayList<SharedResource> tmp = sharedCache;
		sharedCache = new ArrayList<SharedResource>(GOODSCACHE_MAX_SIZE + 3);
		SharedService service = new SharedService();
		if (!service.InsertCacheItmes(tmp)) {
			sharedCache = tmp;
		}
		service.close();
	}

	public synchronized boolean FlushAllCache() throws MyException, DBConnctionException {
		// 加锁
		synchronized (sharedCache) {
			flushdsharedCache();
		}

		synchronized (goodsCache) {
			flushGoodsCache();
		}

		synchronized (commentCache) {
			flushCommentCache();
		}

		return sharedCache.size() == 0 && goodsCache.size() == 0 && commentCache.size() == 0;
	}
}
