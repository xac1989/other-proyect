package com.cencosud.mobile.saleschannel.exception;

@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2016-12-26T16:58:25.127-03:00")
public class NotFoundException extends ApiException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8914020373819810401L;
	private int code;
	
	public NotFoundException (int code, String msg) {
		super(code, msg);
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
