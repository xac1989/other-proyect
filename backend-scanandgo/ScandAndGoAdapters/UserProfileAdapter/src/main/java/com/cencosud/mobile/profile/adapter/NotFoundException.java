package com.cencosud.mobile.profile.adapter;

@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-26T10:53:29.306-03:00")
public class NotFoundException extends ApiException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6303551654229967340L;
	private int code;
	public NotFoundException (int code, String msg) {
		super(code, msg);
		this.code = code;
	}
}
