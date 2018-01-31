package com.cencosud.mobile.search.adapter;

@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-09T16:45:59.781-03:00")
public class ApiException extends Exception{
	protected int code;
	public ApiException (int code, String msg) {
		super(msg);
		this.code = code;
	}
    public int getCode() {
        return code;
    }
}
