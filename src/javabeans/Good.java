package javabeans;

import java.io.Serializable;

public class Good implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String finderId, numberInfo, losterName, goodDesc, foundAddr, finderName, finderPhone, finderQQorWX,
			imagePath;

	private int id;

	public Good()
	{

	}

	public Good(int id, String finderId, String numberInfo, String losterName, String goodDesc, String foundAddr,
			String finderName, String finderPhone, String finderQQorWX, String imagePath)
	{
		super();
		this.id = id;
		this.finderId = finderId;
		this.numberInfo = numberInfo;
		this.losterName = losterName;
		this.goodDesc = goodDesc;
		this.foundAddr = foundAddr;
		this.finderName = finderName;
		this.finderPhone = finderPhone;
		this.finderQQorWX = finderQQorWX;
		this.imagePath = imagePath;
	}

	public Good(String finderId, String numberInfo, String losterName, String goodDesc, String foundAddr,
			String finderName, String finderPhone, String finderQQorWX, String imagePath)
	{
		super();
		this.finderId = finderId;
		this.numberInfo = numberInfo;
		this.losterName = losterName;
		this.goodDesc = goodDesc;
		this.foundAddr = foundAddr;
		this.finderName = finderName;
		this.finderPhone = finderPhone;
		this.finderQQorWX = finderQQorWX;
		this.imagePath = imagePath;
	}

	@Override
	public String toString()
	{
		return "Good [finderId=" + finderId + ", numberInfo=" + numberInfo + ", losterName=" + losterName
				+ ", goodDesc=" + goodDesc + ", foundAddr=" + foundAddr + ", finderName=" + finderName
				+ ", finderPhone=" + finderPhone + ", finderQQorWX=" + finderQQorWX + ", imagePath=" + imagePath
				+ ", id=" + id + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((finderId == null) ? 0 : finderId.hashCode());
		result = prime * result + ((finderName == null) ? 0 : finderName.hashCode());
		result = prime * result + ((finderPhone == null) ? 0 : finderPhone.hashCode());
		result = prime * result + ((finderQQorWX == null) ? 0 : finderQQorWX.hashCode());
		result = prime * result + ((foundAddr == null) ? 0 : foundAddr.hashCode());
		result = prime * result + ((goodDesc == null) ? 0 : goodDesc.hashCode());
		result = prime * result + ((losterName == null) ? 0 : losterName.hashCode());
		result = prime * result + ((numberInfo == null) ? 0 : numberInfo.hashCode());
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

		Good other = (Good) obj;

		if (this.id == other.id)
			return true;

		if (finderId == null)
		{
			if (other.finderId != null)
				return false;
		} else if (!finderId.equals(other.finderId))
			return false;
		if (finderName == null)
		{
			if (other.finderName != null)
				return false;
		} else if (!finderName.equals(other.finderName))
			return false;
		if (finderPhone == null)
		{
			if (other.finderPhone != null)
				return false;
		} else if (!finderPhone.equals(other.finderPhone))
			return false;
		if (finderQQorWX == null)
		{
			if (other.finderQQorWX != null)
				return false;
		} else if (!finderQQorWX.equals(other.finderQQorWX))
			return false;
		if (foundAddr == null)
		{
			if (other.foundAddr != null)
				return false;
		} else if (!foundAddr.equals(other.foundAddr))
			return false;
		if (goodDesc == null)
		{
			if (other.goodDesc != null)
				return false;
		} else if (!goodDesc.equals(other.goodDesc))
			return false;

		if (losterName == null)
		{
			if (other.losterName != null)
				return false;
		} else if (!losterName.equals(other.losterName))
			return false;
		if (numberInfo == null)
		{
			if (other.numberInfo != null)
				return false;
		} else if (!numberInfo.equals(other.numberInfo))
			return false;
		return true;
	}

	public String getFinderId()
	{
		return finderId;
	}

	public void setFinderId(String finderId)
	{
		this.finderId = finderId;
	}

	public String getNumberInfo()
	{
		return numberInfo;
	}

	public void setNumberInfo(String numberInfo)
	{
		this.numberInfo = numberInfo;
	}

	public String getLosterName()
	{
		return losterName;
	}

	public void setLosterName(String losterName)
	{
		this.losterName = losterName;
	}

	public String getGoodDesc()
	{
		return goodDesc;
	}

	public void setGoodDesc(String goodDesc)
	{
		this.goodDesc = goodDesc;
	}

	public String getFoundAddr()
	{
		return foundAddr;
	}

	public void setFoundAddr(String foundAddr)
	{
		this.foundAddr = foundAddr;
	}

	public String getFinderName()
	{
		return finderName;
	}

	public void setFinderName(String finderName)
	{
		this.finderName = finderName;
	}

	public String getFinderPhone()
	{
		return finderPhone;
	}

	public void setFinderPhone(String finderPhone)
	{
		this.finderPhone = finderPhone;
	}

	public String getFinderQQorWX()
	{
		return finderQQorWX;
	}

	public void setFinderQQorWX(String finderQQorWX)
	{
		this.finderQQorWX = finderQQorWX;
	}

	public String getImagePath()
	{
		return imagePath;
	}

	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

}
