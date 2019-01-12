package com.example.spring;

@SuppressWarnings("serial")
public class ErrorException extends NullPointerException {
	
	public ErrorException(String exception) {
		super(exception);
	}

}
