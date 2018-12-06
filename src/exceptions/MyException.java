package exceptions;

public class MyException extends Exception
{
	private String errorInfo = "";
	private static final long serialVersionUID = 1L;

	public String getErrorInfo()
	{
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo)
	{
		this.errorInfo = errorInfo;
	}

	public MyException(String errorInfo)
	{
		super();
		this.errorInfo = errorInfo;
	}

	public void printStackTrace()
	{
		System.err.println();
		System.err.println("错误原因 :" + getErrorInfo());
		super.printStackTrace();
	}

	public String toString()
	{
		return super.toString();
	}

}
