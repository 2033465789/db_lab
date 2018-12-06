package app;

import base.BaseService;
import exceptions.DBConnctionException;

//单例
public class AppInfos {
	private static boolean userConnectStatus = true, adminConnectStatus = true;
	private static int visteNum = 0;
	private static int lastNum = 0;
	private static final int STEP = 10;
	static {
		try {
			visteNum = BaseService.getVisiteNum();
			lastNum = visteNum;
		} catch (DBConnctionException e) {
			e.printStackTrace();
		}
	}

	public static boolean getAdminConnectStatus() {
		return adminConnectStatus;
	}

	public static void setAdminConnectStatus(boolean connectStatus) {
		adminConnectStatus = connectStatus;
	}

	public static boolean getUserConnectStatus() {
		return userConnectStatus;
	}

	public static void setUserConnectStatus(boolean connectStatus) {
		userConnectStatus = connectStatus;
	}

	public static int getVisteNum() {
		return visteNum;
	}

	public static void setVisteNum(int newNum) throws DBConnctionException {
		visteNum = newNum;
		if (lastNum + STEP < visteNum) {
			BaseService.setVisiteNum(newNum);
			lastNum = visteNum;
		}
	}

}
