package javabeans;

import utils.StaticDataUtil;

public class User
{
	private int userPermission;//权限等级
	private String uid; 	 //用户名 
	private String pwd ;     	 //密码
	private String  nickname ; 	 //昵称
	private String  registerTime;    //注册时间
	
	public User()
	{
	}

	public User(String uid, String pwd, String nickname) {
		super();
		this.uid = uid;
		this.pwd = pwd;
		this.nickname = nickname;
	}
	public User(String uid, String pwd, String nickname,int permission) {
		super();
		this.uid = uid;
		this.pwd = pwd;
		this.userPermission = permission;
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "User [userPermission=" + userPermission + ", uid=" + uid
				+ ", pwd=" + pwd + ", nickname=" + nickname  + ", registerTime=" + registerTime + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result
				+ ((registerTime == null) ? 0 : registerTime.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result + userPermission;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (registerTime == null) {
			if (other.registerTime != null)
				return false;
		} else if (!registerTime.equals(other.registerTime))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		if (userPermission != other.userPermission)
			return false;
		return true;
	}

	public int getUserPermission() {
		return userPermission;
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
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

}
