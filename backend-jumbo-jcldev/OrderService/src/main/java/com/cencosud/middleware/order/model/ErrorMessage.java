package com.cencosud.middleware.order.model;

public class ErrorMessage {

	private String uid;
	private String httpCode;
    private String httpMessage;
    private String moreInformation;
	
    public ErrorMessage() {}
    public ErrorMessage(String uid, String httpCode, String httpMessage, String moreInformation) {
		this.uid = uid;
		this.httpCode = httpCode;
		this.httpMessage = httpMessage;
		this.moreInformation = moreInformation;
	}
    
    public String getUid() {
		return uid;
	}
	
    public void setUid(String uid) {
		this.uid = uid;
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
	
    public String getMoreInformation() {
		return moreInformation;
	}
	
    public void setMoreInformation(String moreInformation) {
		this.moreInformation = moreInformation;
	}	
	
}
