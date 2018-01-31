package com.cencosud.middleware.register.exception;

public class VtexServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7501674194617142586L;
	
	private Integer code;
	
	public VtexServiceException() {
		
	}

	/**
	 * @param code
	 */
	public VtexServiceException(Integer code, String message) {
		super(message);
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
	
	

}
