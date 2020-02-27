package com.mentorondemand.exception;

@SuppressWarnings("serial")
public class GlobalException extends RuntimeException
{
	public GlobalException(String message)
	{
		super(message);
	}
}
