package com.cencosud.mobile.config;

public class AppConfig {
	
	private String proxyUrl;
	private String proxyPort;
	private String clientId;
	private String clientSecret;
	private String apiBaseUrl;
	private String apiBasePath;
	
	public String getProxyUrl() {
		return proxyUrl;
	}
	
	public void setProxyUrl(String proxyUrl) {
		this.proxyUrl = proxyUrl;
	}
	
	public String getProxyPort() {
		return proxyPort;
	}
	
	public void setProxyPort(String proxyPort) {
		this.proxyPort = proxyPort;
	}
	
	public String getClientId() {
		return clientId;
	}
	
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public String getClientSecret() {
		return clientSecret;
	}
	
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	
	public String getApiBaseUrl() {
		return apiBaseUrl;
	}
	
	public void setApiBaseUrl(String apiBaseUrl) {
		System.out.println(apiBaseUrl);
		this.apiBaseUrl = apiBaseUrl;
	}
	
	public String getApiBasePath() {
		return apiBasePath;
	}
	
	public void setApiBasePath(String apiBasePath) {
		this.apiBasePath = apiBasePath;
	}
}
