package com.scanandgo.mobile.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.mfp.server.registration.external.model.AuthenticatedUser;
import com.scanandgo.mobile.model.Attributes;
import com.scanandgo.mobile.model.Favorites;
import com.scanandgo.mobile.model.UserProfile;
import com.scanandgo.mobile.model.UserWhiteList;

public class UserProfileService {

	private static final String CLIENT_ID_HEADER = "X-IBM-Client-Id";
	private static final String CLIENT_SECRET_HEADER = "X-IBM-Client-Secret";
	private static final String USER_PROFILE_PATH = "/profile";
	private static final String USER_WHITELIST_RUT_PATH = "/whiteList/findByRut";
	private static final String USER_WHITELIST_EMAIL_PATH = "/whiteList/findByEmail";
	
	private static final Logger logger = Logger.getLogger(UserProfileService.class.getName());
	private static CloseableHttpClient client;
	
	private transient final ObjectMapper mapper = new ObjectMapper();
	private String apiBaseUrl;

	public UserProfileService(String apiBaseUrl, String apiClientId, String apiClientSecret) {
		this.apiBaseUrl = apiBaseUrl;
		// set headers for request
		List<Header> headers = new ArrayList<Header>();
		headers.add(new BasicHeader(CLIENT_ID_HEADER, apiClientId));
		headers.add(new BasicHeader(CLIENT_SECRET_HEADER, apiClientSecret));
		
		client = HttpClients.custom().setDefaultHeaders(headers).build();
	}

	public void createProfile(AuthenticatedUser authenticatedUser)
			throws IOException, IllegalStateException, SAXException, URISyntaxException {
		String email = getEmail(authenticatedUser);
		logger.finer("Email de usuario: " + email);
		String rutPuntosCencosud = getRutPuntosCencosud(authenticatedUser);
		logger.finer("rutPuntosCencosud: " + rutPuntosCencosud);
		createOrUpdateCencosudProfile(authenticatedUser, email, rutPuntosCencosud);
	}
	
	public boolean validateEmailOnWhiteList(String email, String rutPuntosCencosud)
			throws URISyntaxException, ClientProtocolException, IOException {
		try {
			// call create profile service
			URIBuilder builder = null;
			if(email != null && !email.equals("")){
				builder = new URIBuilder(this.apiBaseUrl + USER_WHITELIST_EMAIL_PATH);
				builder.setParameter("email", email);
			} else {
				builder = new URIBuilder(this.apiBaseUrl + USER_WHITELIST_RUT_PATH);
				builder.setParameter("rut", rutPuntosCencosud);
			}
			System.out.println("URL------------------_>"+builder.toString());
			HttpGet request = new HttpGet(builder.build());
			logger.finer("putProfile: " + request.getURI().toString());
			HttpResponse response = client.execute(request);
			String responseBody = getStringFromInputStream(response.getEntity().getContent()).trim();
			logger.finer("createOrUpdateProfile -> Response Body: " + responseBody);
			System.out.println("createOrUpdateProfile -> Response Body: " + responseBody);
			logger.finer("createProfile -> Response Code: " + response.getStatusLine().getStatusCode());
			System.out.println("createProfile -> Response Code: " + response.getStatusLine().getStatusCode());
			if(responseBody != null && !responseBody.equals("")){
				mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
				UserWhiteList userInfo = mapper.readValue(responseBody, UserWhiteList.class);
				return userInfo.getActivo();
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			e.printStackTrace();
		}
		
		return false;
	}

	private String getEmail(AuthenticatedUser authenticatedUser) {
		if (authenticatedUser != null && authenticatedUser.getAttributes() != null
				&& authenticatedUser.getAttributes().containsKey("email")
				&& authenticatedUser.getAttributes().get("email") != null) {
				String email = (String)authenticatedUser.getAttributes().get("email");
				if (email != null) {
					email = email.trim();
					if (email.equalsIgnoreCase("null") || email.equalsIgnoreCase("")) {
						return "";
					} else {

						return email;
					}
				}
		}
		return "";
	}

	private String getRutPuntosCencosud(AuthenticatedUser authenticatedUser) {
		if (authenticatedUser != null && authenticatedUser.getAttributes() != null
				&& authenticatedUser.getAttributes().containsKey("rutPuntosCencosud")
				&& authenticatedUser.getAttributes().get("rutPuntosCencosud") != null) {
				String rut = (String)authenticatedUser.getAttributes().get("rutPuntosCencosud");
				if (rut != null) {
					rut = rut.trim();
					if (rut.equalsIgnoreCase("null") || rut.equalsIgnoreCase("")) {
						return "";
					} else {
						return rut;
					}
				}
		}
		return "";
	}

	private void createOrUpdateCencosudProfile(AuthenticatedUser authenticatedUser, String email, String rutPuntosCencosud)
			throws URISyntaxException, ClientProtocolException, IOException {
		try {
			// create profile domain object
			UserProfile profile = getProfileFromAuthenticatedUser(authenticatedUser, email, rutPuntosCencosud);
			String profileJson = mapper.writeValueAsString(profile);
			// call create profile service
			URIBuilder builder = new URIBuilder(this.apiBaseUrl + USER_PROFILE_PATH);
			HttpPost request = new HttpPost(builder.build());
			StringEntity requestEntity = new StringEntity(profileJson, ContentType.APPLICATION_JSON);
			request.setEntity(requestEntity);
			logger.finer("putProfile: " + request.getURI().toString());
			HttpResponse response = client.execute(request);
			String responseBody = getStringFromInputStream(response.getEntity().getContent()).trim();
			logger.finer("createOrUpdateProfile -> Response Body: " + responseBody);
			logger.finer("createProfile -> Response Code: " + response.getStatusLine().getStatusCode());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private UserProfile getProfileFromAuthenticatedUser(AuthenticatedUser authenticatedUser, String email, String rutPuntosCencosud) {
		// List of categories
		List<String> categories = new ArrayList<String>();
		// Favorites
		Favorites favorites = new Favorites();
		favorites.setCategories(categories);
		// Attributes
		Attributes attributes = new Attributes();
		attributes.setAttr("");
		// Profile
		UserProfile newProfile = new UserProfile();
		newProfile.setFavorites(favorites);
		newProfile.setAttributes(attributes);
		newProfile.setDisplayName(authenticatedUser.getDisplayName());
		newProfile.setUserProfileId(email);
		newProfile.setRutPuntosCencosud(rutPuntosCencosud);

		return newProfile;
	}

	private static String getStringFromInputStream(InputStream is) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.log(Level.SEVERE, e.getMessage(), e);
				}
			}
		}
		return sb.toString();
	}
}
