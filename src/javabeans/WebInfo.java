package javabeans;

public class WebInfo {
	private String aimURL, imgURL, webName, webDesc;
	private long wid;

	public WebInfo(int id, String aimURL, String imgURL, String webName,
			String webDesc) {
		super();
		this.aimURL = aimURL;
		this.imgURL = imgURL;
		this.webName = webName;
		this.webDesc = webDesc;
		this.setId(id);
	}

	public WebInfo() {
		super();
	}

	@Override
	public String toString() {
		return "WebInfo [aimURL=" + aimURL + ", imgURL=" + imgURL + ", webName="
				+ webName + ", webDesc=" + webDesc + ", id=" + wid + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aimURL == null) ? 0 : aimURL.hashCode());
		result = prime * result + (int) wid;
		result = prime * result + ((imgURL == null) ? 0 : imgURL.hashCode());
		result = prime * result + ((webDesc == null) ? 0 : webDesc.hashCode());
		result = prime * result + ((webName == null) ? 0 : webName.hashCode());
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
		WebInfo other = (WebInfo) obj;
		if (aimURL == null) {
			if (other.aimURL != null)
				return false;
		} else if (!aimURL.equals(other.aimURL))
			return false;
		if (wid != other.wid)
			return false;
		if (imgURL == null) {
			if (other.imgURL != null)
				return false;
		} else if (!imgURL.equals(other.imgURL))
			return false;
		if (webDesc == null) {
			if (other.webDesc != null)
				return false;
		} else if (!webDesc.equals(other.webDesc))
			return false;
		if (webName == null) {
			if (other.webName != null)
				return false;
		} else if (!webName.equals(other.webName))
			return false;
		return true;
	}

	public String getAimURL() {
		return aimURL;
	}

	public void setAimURL(String aimURL) {
		this.aimURL = aimURL;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public String getWebDesc() {
		return webDesc;
	}

	public void setWebDesc(String webDesc) {
		this.webDesc = webDesc;
	}

	public long getId() {
		return wid;
	}

	public void setId(long id) {
		this.wid = id;
	}
}
