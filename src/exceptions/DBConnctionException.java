package exceptions;

public class DBConnctionException extends Exception
{
	private static final long serialVersionUID = 1L;
	private String errorInfo = null;

	public String getErrorInfo()
	{
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo)
	{
		this.errorInfo = errorInfo;
	}

	public DBConnctionException(String errorInfo)
	{
		super();
		this.errorInfo = errorInfo;
	}

	public void printStackTrace()
	{
		System.err.println();
		if (getErrorInfo() != null)
			System.err.println("错误原因 :" + getErrorInfo());
		super.printStackTrace();
	}

	public String toString()
	{
		return super.toString();
	}

}
