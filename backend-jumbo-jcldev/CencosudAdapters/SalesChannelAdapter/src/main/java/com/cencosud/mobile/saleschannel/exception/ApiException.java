package com.cencosud.mobile.saleschannel.exception;

@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2016-12-26T16:58:25.127-03:00")
public class ApiException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3968002574137556260L;
	private int code;
	public ApiException (int code, String msg) {
		super(msg);
		this.setCode(code);
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
