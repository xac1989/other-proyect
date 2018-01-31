package com.cencosud.mobile.exceptions;

@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-09T10:51:58.936-03:00")
public class NotFoundException extends ApiException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4697349636510332899L;
	
	private int code;
	public NotFoundException (int code, String msg) {
		super(code, msg);
		this.code = code;
	}
}
