package com.cencosud.mobile.login.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import org.json.JSONObject;

public class FacebookAuthorizationUtils {
	private static final Logger logger = Logger.getLogger(FacebookAuthorizationUtils.class.getName());
	private static FacebookAuthorizationUtils instance;

	private static final String CHECK_APP_ACCESS_TOKEN_URL = "https://graph.facebook.com/app?access_token={0}";

	private FacebookAuthorizationUtils() {
	}

	public static synchronized FacebookAuthorizationUtils getInstance() {
		if (instance == null) {
			instance = new FacebookAuthorizationUtils();
		}
		return instance;
	}

	/**
	 * Check given user access token if it belongs to Wong application.
	 * 
	 * @param userAccessToken
	 * @param wongAppName
	 * @return
	 * @throws Exception
	 */
	public boolean checkUserAccessToken(String userAccessToken, String wongAppName, SSLSocketFactory sslSocketFactory)
			throws Exception {
		boolean isValid = false;

		HttpsURLConnection connection = null;

		try {
			String req = MessageFormat.format(CHECK_APP_ACCESS_TOKEN_URL, userAccessToken);
			connection = (HttpsURLConnection) new URL(req).openConnection();
			connection.setSSLSocketFactory(sslSocketFactory);
			connection.setRequestMethod("GET");
			connection.connect();

			int responseCode = connection.getResponseCode();
			String content = readContent(
					responseCode == 200 ? connection.getInputStream() : connection.getErrorStream());

			if (content != null) {
				JSONObject response = new JSONObject(content);
				String applicationName = response.getString("name");
				if (!applicationName.equals(wongAppName))
					isValid = false;
				else
					isValid = true;
			}

		} catch (Exception e) {
			logger.log(Level.SEVERE,
					MessageFormat.format("Failed to check Facebook user access token {0}", userAccessToken), e);
			throw e;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return isValid;
	}

	private String readContent(InputStream inputStream) throws IOException {
		String content = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		while ((line = in.readLine()) != null) {
			content += line + " ";
		}
		in.close();
		return content;
	}
}
