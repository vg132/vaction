package com.vgsoftware.vaction;

public class VActionException extends Exception
{
	public VActionException()
	{
	}

	public VActionException(String message)
	{
		super(message);
	}

	public VActionException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
