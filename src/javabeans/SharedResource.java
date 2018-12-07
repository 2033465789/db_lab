package javabeans;

public class SharedResource
{
	private String fileName, uploadUser, uploadTime, filePath, fileType, fileDesc;
	private int id;

	public SharedResource()
	{

	}

	// 通过对象引用构造
	public SharedResource(SharedResource e)
	{
		super();
		this.id = e.id;
		this.fileName = e.fileName;
		this.uploadUser = e.uploadUser;
		this.uploadTime = e.uploadTime;
		this.filePath = e.filePath;
		this.fileType = e.fileType;
		this.fileDesc = e.fileDesc;
	}

	// sharedId, fileName, uploadUser, uploadTime, filePath, fileType, fileDesc
	public SharedResource(int id, String fileName, String uploadUser, String uploadTime, String filePath,
			String fileType, String fileDesc)
	{
		super();
		this.id = id;
		this.fileName = fileName;
		this.uploadUser = uploadUser;
		this.uploadTime = uploadTime;
		this.filePath = filePath;
		this.fileType = fileType;
		this.fileDesc = fileDesc;
	}

	@Override
	public String toString()
	{
		return "SharedResource [fileName=" + fileName + ", uploadUser=" + uploadUser + ", uploadTime=" + uploadTime
				+ ", filePath=" + filePath + ", fileType=" + fileType + ", fileDesc=" + fileDesc + ", sharedId=" + id
				+ "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileDesc == null) ? 0 : fileDesc.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
		result = prime * result + ((fileType == null) ? 0 : fileType.hashCode());
		result = (int) (prime * result + id);
		result = prime * result + ((uploadTime == null) ? 0 : uploadTime.hashCode());
		result = prime * result + ((uploadUser == null) ? 0 : uploadUser.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SharedResource other = (SharedResource) obj;
		if (id == other.id)
			return true;

		if (fileDesc == null)
		{
			if (other.fileDesc != null)
				return false;
		} else if (!fileDesc.equals(other.fileDesc))
			return false;
		if (fileName == null)
		{
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (filePath == null)
		{
			if (other.filePath != null)
				return false;
		} else if (!filePath.equals(other.filePath))
			return false;
		if (fileType == null)
		{
			if (other.fileType != null)
				return false;
		} else if (!fileType.equals(other.fileType))
			return false;
		if (uploadUser == null)
		{
			if (other.uploadUser != null)
				return false;
		} else if (!uploadUser.equals(other.uploadUser))
			return false;
		return true;
	}

	public long getId()
	{
		return id;
	}

	public void setId(int sharedId)
	{
		this.id = sharedId;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getUploadUser()
	{
		return uploadUser;
	}

	public void setUploadUser(String uploadUser)
	{
		this.uploadUser = uploadUser;
	}

	public String getUploadTime()
	{
		return uploadTime;
	}

	public void setUploadTime(String uploadTime)
	{
		this.uploadTime = uploadTime;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	public String getFileType()
	{
		return fileType;
	}

	public void setFileType(String fileType)
	{
		this.fileType = fileType;
	}

	public String getFileDesc()
	{
		return fileDesc;
	}

	public void setFileDesc(String fileDesc)
	{
		this.fileDesc = fileDesc;
	}

	// 对象是否包含字段
	private String toSearchString()
	{
		return fileName + " " + uploadUser + " " + fileDesc;
	}

	public boolean containsInfo(String searchInfo)
	{
		return toSearchString().contains(searchInfo);
	}
}
