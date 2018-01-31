package com.cencosud.mobile.exceptions;

@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-09T10:51:58.936-03:00")
public class ApiException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6070954155895568919L;
	
	private int code;
	public ApiException (int code, String msg) {
		super(msg);
		this.code = code;
	}
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
	
	
}
