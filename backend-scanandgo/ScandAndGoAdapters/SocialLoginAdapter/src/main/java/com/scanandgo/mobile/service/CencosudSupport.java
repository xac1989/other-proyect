package com.scanandgo.mobile.service;

import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.mfp.server.registration.external.model.AuthenticatedUser;
import com.scanandgo.mobile.login.adapter.LoginVendor;
import com.scanandgo.mobile.login.adapter.SocialLoginAdapter;
import com.scanandgo.mobile.model.CencosudServiceRequest;
import com.scanandgo.mobile.model.ErrorMessage;
import com.scanandgo.mobile.model.UserProfileInfo;

public class CencosudSupport implements LoginVendor {

	private static final Logger logger = Logger.getLogger(CencosudSupport.class.getName());
	private static final String LOGIN_SERVICE_URL = "login.cencosud.service";
	private static final String LOGIN_CENCOSUD_ENDPOINT = "/login";

	private SSLSocketFactory sslSocketFactory;
	private String loginServiceURL;
	
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public String[] getConfigurationPropertyNames() {
		return new String[] { LOGIN_SERVICE_URL };
	}

	@Override
	public void setConfiguration(Properties properties, SSLSocketFactory sslSocketFactory) {
		this.sslSocketFactory = sslSocketFactory;
		this.loginServiceURL = properties.getProperty(LOGIN_SERVICE_URL);
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public AuthenticatedUser validateTokenAndCreateUser(Map<String, Object> credentials, String checkName) {
		String userName = (String) credentials.get(SocialLoginAdapter.USER_CENCOSUD);
		String password = (String) credentials.get(SocialLoginAdapter.PASSWORD_CENCOSUD);

		logger.finest("Log-in cencosud, username: " + userName + ", password " + password);
		System.out.println("Log-in cencosud, username: " + userName + ", password " + password);
		String error = "";
		HttpsURLConnection connection = null;
		String req = this.loginServiceURL + LOGIN_CENCOSUD_ENDPOINT;
		System.out.println(req);
		try {
			connection = (HttpsURLConnection) new URL(req).openConnection();
			connection.setSSLSocketFactory(sslSocketFactory);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Authorization", "Basic Y2VuY29hcHB1c2VyOkhqenRXN2RRTDdZOFhKNWNaYnBmZ01IYg==");
			connection.setRequestMethod("POST");

			connection.setDoOutput(true);
			CencosudServiceRequest request = new CencosudServiceRequest(userName, password);
			OutputStream wr = connection.getOutputStream();
			wr.write(mapper.writeValueAsString(request).getBytes("UTF-8"));
			wr.flush();
			wr.close();

			connection.connect();

			int responseCode = connection.getResponseCode();

			if (responseCode == 200) {
				mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
				UserProfileInfo userInfo = mapper.readValue(connection.getInputStream(), UserProfileInfo.class);
				HashMap<String, Object> userAttributes = new HashMap<>();

				userAttributes.put("profilePicture", userInfo.getProfilePicture());
				
				userAttributes.put("email", userInfo.getEmail());
				userAttributes.put("rutPuntosCencosud", userName);
				return new AuthenticatedUser(userInfo.getId(), userInfo.getDisplayName(), checkName, userAttributes);

			} else {
				ErrorMessage errorMessage = mapper.readValue(connection.getErrorStream(), ErrorMessage.class);
				if (errorMessage != null) {
					logger.severe("Error :" + errorMessage.getHttpCode() + ":" + errorMessage.getHttpMessage() + " - "
							+ errorMessage.getMoreInformation());
				}
				error = "No se encontraron datos para el rut:" + userName;;
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			error = e.getMessage();
		}

		logger.severe("Failed to validate Cencosud access token: " + error);
		return null;
	}

}
