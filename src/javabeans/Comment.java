package javabeans;

public class Comment
{
	private String id, uid, fid, content, createTime, deleteTime;

	public Comment()
	{
		super();
	}

	public Comment(String id, String uid, String fid, String content, String createTime, String deleteTime)
	{
		super();
		this.id = id;
		this.uid = uid;
		this.fid = fid;
		this.content = content;
		this.createTime = createTime;
		this.deleteTime = deleteTime;
	}

	@Override
	public String toString()
	{
		return "Comment [id=" + id + ", uid=" + uid + ", fid=" + fid + ", content=" + content + ", createTime="
				+ createTime + ", deleteTime=" + deleteTime + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((deleteTime == null) ? 0 : deleteTime.hashCode());
		result = prime * result + ((fid == null) ? 0 : fid.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
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
		Comment other = (Comment) obj;
		if (content == null)
		{
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (createTime == null)
		{
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (deleteTime == null)
		{
			if (other.deleteTime != null)
				return false;
		} else if (!deleteTime.equals(other.deleteTime))
			return false;
		if (fid == null)
		{
			if (other.fid != null)
				return false;
		} else if (!fid.equals(other.fid))
			return false;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (uid == null)
		{
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getUid()
	{
		return uid;
	}

	public void setUid(String uid)
	{
		this.uid = uid;
	}

	public String getFid()
	{
		return fid;
	}

	public void setFid(String fid)
	{
		this.fid = fid;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	public String getDeleteTime()
	{
		return deleteTime;
	}

	public void setDeleteTime(String deleteTime)
	{
		this.deleteTime = deleteTime;
	}

}
