package com.cencosud.mobile.adapter;

import java.util.Properties;

import org.apache.commons.lang3.BooleanUtils;

import com.cencosud.mobile.service.impl.LoginService;
import com.ibm.mfp.security.checks.base.UserAuthenticationSecurityCheckConfig;

public class WongPeLoginAdapterConfiguration extends UserAuthenticationSecurityCheckConfig{
	public static final String KEEP_ORIGINAL_TOKEN = "keepOriginalToken";
	public static final String API_BASE_URL = "api_base_url";
	public static final String API_CLIENT_ID = "api_client_id";
	public static final String API_CLIENT_SECRET = "api_secret";

	private boolean keepOriginalToken;
	private LoginService loginService;

	public WongPeLoginAdapterConfiguration(Properties properties) {
		super(properties);
		keepOriginalToken = BooleanUtils.toBoolean(properties.getProperty(KEEP_ORIGINAL_TOKEN));

		String apiBaseurl = getStringProperty(API_BASE_URL, properties, "");
		String apiClientId = getStringProperty(API_CLIENT_ID, properties, "");
		String apiClientSecret = getStringProperty(API_CLIENT_SECRET, properties, "");

		setLoginService(new LoginService(apiBaseurl, apiClientId, apiClientSecret));
	}

	public boolean isKeepOriginalToken() {
		return keepOriginalToken;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
}
