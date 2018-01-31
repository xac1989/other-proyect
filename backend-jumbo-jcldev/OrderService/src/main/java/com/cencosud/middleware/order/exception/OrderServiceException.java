package com.cencosud.middleware.order.exception;

public class OrderServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public OrderServiceException(String message) {
		super(message);
	}
	
	public OrderServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	
}
