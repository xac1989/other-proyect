package com.cencosud.mobile.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLSocketFactory;

import com.cencosud.mobile.login.adapter.LoginVendor;
import com.cencosud.mobile.login.adapter.SocialLoginAdapter;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.ibm.mfp.server.registration.external.model.AuthenticatedUser;

public class GoogleSupport implements LoginVendor {

	private static final Logger logger = Logger.getLogger(GoogleSupport.class.getName());

	private static final String CLIENT_ID_CONFIG_PROPERTY = "google.clientId";

	private NetHttpTransport transport;
	private JsonFactory jsonFactory;

	private GoogleIdTokenVerifier[] verifiers;

	public String[] getConfigurationPropertyNames() {
		return new String[] { CLIENT_ID_CONFIG_PROPERTY };
	}

	@Override
	public void setConfiguration(Properties properties, SSLSocketFactory sslSocketFactory) {
		transport = new NetHttpTransport.Builder().setSslSocketFactory(sslSocketFactory).build();
		jsonFactory = new JacksonFactory();

		String clientId = properties.getProperty(CLIENT_ID_CONFIG_PROPERTY);
		if (clientId != null && !clientId.isEmpty() && clientId.endsWith("apps.googleusercontent.com")) {
			verifiers = new GoogleIdTokenVerifier[] { createTokenVerifier(clientId, "https://accounts.google.com"),
					createTokenVerifier(clientId, "accounts.google.com"), };
		} else {
			verifiers = null;
		}
	}

	private GoogleIdTokenVerifier createTokenVerifier(String clientId, String issuer) {
		return new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
				.setAudience(Collections.singletonList(clientId)).setIssuer(issuer).build();
	}

	public boolean isEnabled() {
		return verifiers != null;
	}

	public AuthenticatedUser validateTokenAndCreateUser(Map<String, Object> credentials, String checkName) {
		GoogleIdToken idToken = null;
		String errorMsg = "";
		String idTokenStr = (String) credentials.get(SocialLoginAdapter.TOKEN_KEY);
		logger.finest("Log-in google, token: " + idTokenStr);
		for (GoogleIdTokenVerifier verifier : verifiers) {
			try {
				idToken = verifier.verify(idTokenStr);
				if (idToken != null)
					break;
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
				errorMsg = e.toString() + "\n";
			}
		}
		if (idToken == null) {
			logger.severe("Failed to validate google Id token: " + errorMsg);
			return null;
		}

		GoogleIdToken.Payload payload = idToken.getPayload();
		String userId = payload.getSubject();
		String name = (String) payload.get("name");
		Map<String, Object> attributes = new HashMap<>();

		String email = payload.getEmail();
		if (email != null) {
			attributes.put("email", email);
		}

		copyProperty("picture", payload, attributes, "profilePicture");
		copyProperty("family_name", payload, attributes, "familyName");
		copyProperty("given_name", payload, attributes, "givenName");

		AuthenticatedUser user = new AuthenticatedUser(userId, name, checkName, attributes);

		return user;
	}

	private void copyProperty(String propertyName, GoogleIdToken.Payload from, Map<String, Object> to,
			String toPropertyName) {
		Object value = from.get(propertyName);
		if (value != null)
			to.put(toPropertyName, value);
	}

}
