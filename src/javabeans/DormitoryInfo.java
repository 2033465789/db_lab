package javabeans;

public class DormitoryInfo {
	private String dormAddr, dormWhich, dormTag;

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dormAddr == null) ? 0 : dormAddr.hashCode());
		result = prime * result + ((dormTag == null) ? 0 : dormTag.hashCode());
		result = prime * result + ((dormWhich == null) ? 0 : dormWhich.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DormitoryInfo other = (DormitoryInfo) obj;
		if (dormAddr == null) {
			if (other.dormAddr != null)
				return false;
		} else if (!dormAddr.equals(other.dormAddr))
			return false;
		if (dormTag == null) {
			if (other.dormTag != null)
				return false;
		} else if (!dormTag.equals(other.dormTag))
			return false;
		if (dormWhich == null) {
			if (other.dormWhich != null)
				return false;
		} else if (!dormWhich.equals(other.dormWhich))
			return false;
		return true;
	}

	DormitoryInfo() {

	}

	public DormitoryInfo(String dormAddr, String dormWhich, String dormTag) {
		super();
		this.dormAddr = dormAddr;
		this.dormWhich = dormWhich;
		this.dormTag = dormTag;
	}

	public String getDormAddr() {
		return dormAddr;
	}

	public void setDormAddr(String dormAddr) {
		this.dormAddr = dormAddr;
	}

	public String getDormWhich() {
		return dormWhich;
	}

	public void setDormWhich(String dormWhich) {
		this.dormWhich = dormWhich;
	}

	public String getDormTag() {
		return dormTag;
	}

	public void setDormTag(String dormTag) {
		this.dormTag = dormTag;
	}

}
