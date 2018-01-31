package com.scanandgo.mobile.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.mfp.server.registration.external.model.AuthenticatedUser;
import com.scanandgo.mobile.login.adapter.LoginVendor;
import com.scanandgo.mobile.login.adapter.SocialLoginAdapter;
import com.scanandgo.mobile.login.security.FacebookAuthorizationUtils;

public class FacebookSupport implements LoginVendor {

	private static final Logger logger = Logger.getLogger(FacebookSupport.class.getName());
	private static final String USER_PROFILE_ENDPOINT = "https://graph.facebook.com/{0}/me?fields=id,name,email,location,picture.width(200).height(200).type(square)&access_token={1}";
	private static final String SACN_AND_GO_APPLICATION_NAME = "scanandgo.mobileApplicationName";
	private static final String FACEBOOK_APPI_VERSION = "facebook.apiVersion";

	private String scanAndGoAppName;
	private String apiVersion;

	private ObjectMapper mapper = new ObjectMapper();
	private SSLSocketFactory sslSocketFactory;

	public String[] getConfigurationPropertyNames() {
		return new String[] { SACN_AND_GO_APPLICATION_NAME, FACEBOOK_APPI_VERSION };
	}

	@Override
	public void setConfiguration(Properties properties, SSLSocketFactory sslSocketFactory) {
		this.sslSocketFactory = sslSocketFactory;
		this.scanAndGoAppName = properties.getProperty(SACN_AND_GO_APPLICATION_NAME);
		this.apiVersion = properties.getProperty(FACEBOOK_APPI_VERSION);
	}

	public boolean isEnabled() {
		return true;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public AuthenticatedUser validateTokenAndCreateUser(Map<String, Object> credentials, String checkName) {
		HttpsURLConnection connection = null;
		String error;
		String tokenStr = (String) credentials.get(SocialLoginAdapter.TOKEN_KEY);
		logger.finest("Log-in facebook, token: " + tokenStr);
		try {
			// Before proceeding we need to validate the provided token
			if (!FacebookAuthorizationUtils.getInstance().checkUserAccessToken(tokenStr, this.scanAndGoAppName,
					sslSocketFactory)) {
				logger.severe("Provided token " + tokenStr + " is not a valid Sacn and Go application token.");
				return null;
			} else {
				// Provided token is a valid one, proceed to create an
				// AuthenticatedUser
				String req = MessageFormat.format(USER_PROFILE_ENDPOINT, apiVersion, tokenStr);
				connection = (HttpsURLConnection) new URL(req).openConnection();
				connection.setSSLSocketFactory(sslSocketFactory);
				connection.setRequestMethod("GET");
				connection.connect();

				int responseCode = connection.getResponseCode();
				String content = readContent(
						responseCode == 200 ? connection.getInputStream() : connection.getErrorStream());

				if (responseCode == 200) {
					Map data = mapper.readValue(content, Map.class);
					HashMap<String, Object> userAttributes = new HashMap<>();
					Map<String, Object> picture = (Map<String, Object>) ((Map<String, Object>) data.get("picture"))
							.get("data");

					userAttributes.put("email", data.get("email"));
					Boolean isDefaultPicture = (Boolean) picture.get("is_silhouette");
					if (isDefaultPicture) {
						userAttributes.put("profilePicture", "");
					} else {
						userAttributes.put("profilePicture", picture.get("url"));
					}

					return new AuthenticatedUser((String) data.get("id"), (String) data.get("name"), checkName,
							userAttributes);
				} else {
					error = content;
				}
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			error = e.toString();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}

		logger.severe("Failed to validate Facebook access token: " + error);
		return null;
	}

	private String readContent(InputStream inputStream) throws IOException {
		String content = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		while ((line = in.readLine()) != null) {
			content += line + "\n";
		}
		in.close();
		return content;
	}
}
