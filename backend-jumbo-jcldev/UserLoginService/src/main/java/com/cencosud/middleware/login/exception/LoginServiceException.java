package com.cencosud.middleware.login.exception;

public class LoginServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public LoginServiceException(String message) {
		super(message);
	}
	
	public LoginServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
