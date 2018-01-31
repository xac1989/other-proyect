package com.cencosud.mobile.adapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cencosud.mobile.model.LoginResponse;
import com.cencosud.mobile.model.UserProfileResponse;
import com.ibm.mfp.security.checks.base.UserAuthenticationSecurityCheck;
import com.ibm.mfp.server.registration.external.model.AuthenticatedUser;
import com.ibm.mfp.server.security.external.checks.SecurityCheckConfiguration;

public class WongPeLoginAdapter extends UserAuthenticationSecurityCheck{
private static final Logger logger = LoggerFactory.getLogger(WongPeLoginAdapter.class);
	
	public static final String USERNAME = "username";
	public static final String PASS = "password";
	public static final String SUCCESS = "Success";
	public static final String ARROBA = "@";

	private String errorMsg;
	private String userId;
	private String userDisplayName;
	private Map<String, Object> attributes;

	@Override
	protected Map<String, Object> createChallenge() {
		Map<String, Object> challenge = new HashMap<>();
		challenge.put("errorMsg", errorMsg);
		challenge.put("remainingAttempts", getRemainingAttempts());
		return challenge;
	}

	@Override
	protected AuthenticatedUser createUser() {
		return new AuthenticatedUser(userId, userDisplayName, getName(), attributes);
	}

	private boolean isEmail(String userName){
		return userName.contains(ARROBA);
	}
	@Override
	protected boolean validateCredentials(Map<String, Object> credential) {
		
		logger.info("username : {}",credential.get(USERNAME));
		logger.info("password : {}",credential.get(PASS));

		boolean result = false;
		if (isValidCredential(credential)) {
			logger.info("credential is valid!");
			String userName = credential.get(USERNAME).toString();
			String password = credential.get(PASS).toString();
			try {
				if (!isEmail(userName)) {
					return false;
				}
				
				LoginResponse loginResponse = getConfiguration().getLoginService().getLoginResponse(userName, password);
				System.out.println("loginResponse " + loginResponse);
				if (SUCCESS.equals(loginResponse.getDescription())) {
					UserProfileResponse userProfileResponse = getConfiguration().getLoginService()
							.getUserProfileResponse(userName);
					System.out.println("userProfileResponse " + userProfileResponse);
					attributes = new HashMap<String, Object>();
					attributes.put("email", userName);
					attributes.put("userId", loginResponse.getUserId());
					attributes.put("cookies", loginResponse.getCookies());
					userDisplayName = userProfileResponse.getProfile().getFullName();
					return true;
				} else {
					errorMsg = "WrongCredentials";
					return false;
				}
			} catch (Exception e) {
				logger.error("Error validating credential ",e);
			}
		}

		return result;
	}

	private boolean isValidCredential(Map<String, Object> credentials) {
		if (MapUtils.isEmpty(credentials)) {
			errorMsg = "No credentials were given.";
			return false;
		}

		if (credentials.get(USERNAME) == null || credentials.get(PASS) == null) {
			errorMsg = "Empty Credentials";
			return false;
		}

		if (StringUtils.isBlank(credentials.get(USERNAME).toString())
				|| StringUtils.isBlank(credentials.get(PASS).toString())) {
			errorMsg = "WrongCredentials";
			return false;
		}

		return true;
	}

	@Override
	public SecurityCheckConfiguration createConfiguration(Properties properties) {
		return new WongPeLoginAdapterConfiguration(properties);
	}

	@Override
	protected WongPeLoginAdapterConfiguration getConfiguration() {
		return (WongPeLoginAdapterConfiguration) super.getConfiguration();

	}
}
