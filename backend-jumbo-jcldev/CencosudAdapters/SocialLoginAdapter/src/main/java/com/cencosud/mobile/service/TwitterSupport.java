package com.cencosud.mobile.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import com.cencosud.mobile.login.adapter.LoginVendor;
import com.cencosud.mobile.login.adapter.SocialLoginAdapter;
import com.cencosud.mobile.model.ErrorMessage;
import com.cencosud.mobile.model.TwitterRequest;
import com.cencosud.mobile.model.UserProfileInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.mfp.server.registration.external.model.AuthenticatedUser;

public class TwitterSupport implements LoginVendor {

	private static final Logger logger = Logger.getLogger(TwitterSupport.class.getName());
	private static final String WONG_APP = "wong.app";
	private static final String LOGIN_SERVICE_URL = "login.service";
	private static final String TWITTER_USER_PROFILE_ENDPOINT = "/login/twitter";
	private final ObjectMapper mapper = new ObjectMapper();

	private SSLSocketFactory sslSocketFactory;
	private String app;
	private String loginServiceURL;

	@Override
	public String[] getConfigurationPropertyNames() {
		return new String[] { WONG_APP, LOGIN_SERVICE_URL };
	}

	@Override
	public void setConfiguration(Properties properties, SSLSocketFactory sslSocketFactory) {
		this.sslSocketFactory = sslSocketFactory;
		this.app = properties.getProperty(WONG_APP);
		this.loginServiceURL = properties.getProperty(LOGIN_SERVICE_URL);
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public AuthenticatedUser validateTokenAndCreateUser(Map<String, Object> credentials, String checkName) {

		String oauthToken = (String) credentials.get(SocialLoginAdapter.CLIENT_KEY);
		String oauthSecretToken = (String) credentials.get(SocialLoginAdapter.CLIENT_SECRET_KEY);

		logger.finest("Log-in twitter, oauthToken: " + oauthToken + ", oauthSecretToken: " + oauthSecretToken);

		String error = "";
		HttpsURLConnection connection = null;
		String req = this.loginServiceURL + TWITTER_USER_PROFILE_ENDPOINT;

		try {
			connection = (HttpsURLConnection) new URL(req).openConnection();
			connection.setSSLSocketFactory(sslSocketFactory);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestMethod("POST");

			connection.setDoOutput(true);
			TwitterRequest request = new TwitterRequest(this.app, oauthToken, oauthSecretToken);
			OutputStream wr = connection.getOutputStream();
			wr.write(mapper.writeValueAsString(request).getBytes("UTF-8"));
			wr.flush();
			wr.close();

			connection.connect();

			int responseCode = connection.getResponseCode();

			if (responseCode == 200) {
				UserProfileInfo userInfo = mapper.readValue(connection.getInputStream(), UserProfileInfo.class);
				HashMap<String, Object> userAttributes = new HashMap<>();

				if (userInfo.getIsDefaultProfileImage()) {
					userAttributes.put("profilePicture", "");
				} else {
					userAttributes.put("profilePicture", userInfo.getProfilePicture().replace("http", "https"));
				}
				userAttributes.put("email", userInfo.getEmail());
				return new AuthenticatedUser(userInfo.getId(), userInfo.getDisplayName(), checkName, userAttributes);

			} else {
				ErrorMessage errorMessage = mapper.readValue(connection.getErrorStream(), ErrorMessage.class);
				if (errorMessage != null) {
					logger.severe("Error :" + errorMessage.getHttpCode() + ":" + errorMessage.getHttpMessage() + " - "
							+ errorMessage.getMoreInformation());
				}
				error = "No se encontraron datos para las keys:" + oauthToken + " : " + oauthSecretToken;
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			error = e.getMessage();
		}

		logger.severe("Failed to validate Twitter access token: " + error);
		return null;
	}
}
