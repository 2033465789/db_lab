package javabeans;

import utils.StaticDataUtil;

public class User
{

	private String userId, cardBalance;
	private int userPermission;
	private DormitoryInfo userDormInfo;

	public User()
	{
	}

	public User(String userId, String dormAddr, String dormWhich, String dormTag, String cardBalance, int permission)
	{
		super();
		setUserDormInfo(new DormitoryInfo(dormAddr, dormWhich, dormTag));
		setUserId(userId);
		setCardBalance(cardBalance);
		setUserPermission(permission);
	}

	public String toString()
	{
		return "User [userId=" + userId + ", cardBalance=" + cardBalance + ", userDormInfo=" + userDormInfo + "]";
	}

	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardBalance == null) ? 0 : cardBalance.hashCode());
		result = prime * result + ((userDormInfo == null) ? 0 : userDormInfo.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	public boolean equals(Object obj)
	{
		// 学号相同即认为为同一学生
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId == null)
		{
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getCardBalance()
	{
		return cardBalance;
	}

	public void setCardBalance(String cardBalance)
	{
		this.cardBalance = cardBalance;
	}

	public DormitoryInfo getUserDormInfo()
	{
		return userDormInfo;
	}

	public void setUserDormInfo(DormitoryInfo userDormInfo)
	{
		this.userDormInfo = userDormInfo;
	}

	public int getUserPermission()
	{
		return userPermission;
	}

	// 不对外开放设置用户权限等级
	private void setUserPermission(int permission)
	{
		userPermission = permission;
	}

	public boolean hasBasePermission()
	{
		return getUserPermission() >= StaticDataUtil.MIN_ADMIN;
	}

	public boolean isAdministrator()
	{
		return getUserPermission() == StaticDataUtil.MIN_ADMIN;
	}

	public boolean isSuperAdministrator()
	{
		return getUserPermission() == StaticDataUtil.SUPER_ADMIN;
	}
}
