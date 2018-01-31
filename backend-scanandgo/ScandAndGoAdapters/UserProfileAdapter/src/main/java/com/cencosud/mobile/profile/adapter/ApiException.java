package com.cencosud.mobile.profile.adapter;

@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-26T10:53:29.306-03:00")
public class ApiException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8646963640585392607L;
	private int code;
	public ApiException (int code, String msg) {
		super(msg);
		this.code = code;
	}
}
