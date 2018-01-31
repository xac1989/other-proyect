package com.cencosud.mobile.recommendations.adapter;

@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2016-12-26T16:58:25.127-03:00")
public class NotFoundException extends ApiException {
	private int code;
	public NotFoundException (int code, String msg) {
		super(code, msg);
		this.code = code;
	}
}
