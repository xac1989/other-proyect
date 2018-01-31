package com.cencosud.middleware.enrollment.model;

/**
 * 
 * 
 * <h1>ErrorMessage</h1>
 * <p>
 * Mensaje de error genérico.
 * </p>
 */
public class ErrorMessage{

	private String httpCode;
	private String httpMessage;
	private String moreInformation;
	/**
	 * @return the httpCode
	 */
	public String getHttpCode() {
		return httpCode;
	}
	/**
	 * @param httpCode the httpCode to set
	 */
	public void setHttpCode(String httpCode) {
		this.httpCode = httpCode;
	}
	/**
	 * @return the httpMessage
	 */
	public String getHttpMessage() {
		return httpMessage;
	}
	/**
	 * @param httpMessage the httpMessage to set
	 */
	public void setHttpMessage(String httpMessage) {
		this.httpMessage = httpMessage;
	}
	/**
	 * @return the moreInformation
	 */
	public String getMoreInformation() {
		return moreInformation;
	}
	/**
	 * @param moreInformation the moreInformation to set
	 */
	public void setMoreInformation(String moreInformation) {
		this.moreInformation = moreInformation;
	}
	
	
}
