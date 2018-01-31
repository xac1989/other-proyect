package com.cencosud.middleware.address.model;

public class ErrorMessage{

	private String httpCode;
	private String httpMessage;
	
	public ErrorMessage (){
		
	}
	
	public ErrorMessage(String httpCode, String httpMessage){
		this.httpCode = httpCode;
		this.httpMessage = httpMessage;
	}
	
	
	public String getHttpCode() {
		return httpCode;
	}
	public void setHttpCode(String httpCode) {
		this.httpCode = httpCode;
	}
	public String getHttpMessage() {
		return httpMessage;
	}
	public void setHttpMessage(String httpMessage) {
		this.httpMessage = httpMessage;
	}
	
	
	
}
