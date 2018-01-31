package com.cencosud.mobile.search.adapter;

import java.util.logging.Logger;

import javax.ws.rs.core.Context;

import com.ibm.mfp.adapter.api.ConfigurationAPI;
import com.ibm.mfp.adapter.api.MFPJAXRSApplication;

public class SearchAdapterApplication extends MFPJAXRSApplication {

	private static Logger logger = Logger.getLogger(SearchAdapterApplication.class.getName());
	private static String API_BASE_URL = "api_base_url";
	private static String API_CLIENT_ID = "api_client_id";
	private static String API_SECRET = "api_secret";

	private String apiBaseUrl;
	private String apiSecret;
	private String apiClientId;

	@Context
	ConfigurationAPI configurationAPI;

	public String getApiBaseUrl() {
		return apiBaseUrl;
	}

	public String getApiSecret() {
		return apiSecret;
	}
	
	public String getApiClientId() {
		return apiClientId;
	}

	protected void init() throws Exception {
		apiBaseUrl = configurationAPI.getPropertyValue(API_BASE_URL);
		apiClientId = configurationAPI.getPropertyValue(API_CLIENT_ID);
		apiSecret = configurationAPI.getPropertyValue(API_SECRET);
		logger.info("SearchAdapter initialized!");
	}

	protected void destroy() throws Exception {
		logger.info("SearchAdapter destroyed!");
	}

	protected String getPackageToScan() {
		return getClass().getPackage().getName();
	}
}
