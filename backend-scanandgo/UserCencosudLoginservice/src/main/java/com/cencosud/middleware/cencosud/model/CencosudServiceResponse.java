package com.cencosud.middleware.cencosud.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CencosudServiceResponse {
	private String accessToken;
	private String tokenType;
	private String refreshToken;
	private String expireIn;
	private String clientId;
	private String error;
	
	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}
	/**
	 * @param accessToken the accessToken to set
	 */
	@JsonProperty("access_token")
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	/**
	 * @return the tokenType
	 */
	public String getTokenType() {
		return tokenType;
	}
	/**
	 * @param tokenType the tokenType to set
	 */
	@JsonProperty("token_type")
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	/**
	 * @return the refreshToken
	 */
	public String getRefreshToken() {
		return refreshToken;
	}
	/**
	 * @param refreshToken the refreshToken to set
	 */
	@JsonProperty("refresh_token")
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	/**
	 * @return the expireIn
	 */
	public String getExpireIn() {
		return expireIn;
	}
	/**
	 * @param expireIn the expireIn to set
	 */
	@JsonProperty("expire_in")
	public void setExpireIn(String expireIn) {
		this.expireIn = expireIn;
	}
	/**
	 * @return the client_id
	 */
	public String getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the client_id to set
	 */
	@JsonProperty("client_id")
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	@JsonProperty("error")
	public void setError(String error) {
		this.error = error;
	}
	
	
}
