package com.cencosud.mobile.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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

import com.cencosud.mobile.model.Attributes;
import com.cencosud.mobile.model.Favorites;
import com.cencosud.mobile.model.UserProfile;
import com.cencosud.mobile.model.UserProfileResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.mfp.server.registration.external.model.AuthenticatedUser;

public class UserProfileService {

	private static final String DISPLAY_NAME_FIELD = "displayName";
	private static final String UP_SPECIFIC_PATH = "/specific";
	private static final String CLIENT_ID_HEADER = "X-IBM-Client-Id";
	private static final String CLIENT_SECRET_HEADER = "X-IBM-Client-Secret";
	private static final String USER_PROFILE_PATH = "/userprofile/r1/v1/profile";
	
	
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

	public UserProfile createProfile(AuthenticatedUser authenticatedUser)
			throws IOException, IllegalStateException, SAXException, URISyntaxException {
		String email = getEmail(authenticatedUser);
		UserProfile profile = new UserProfile();
		profile.setDisplayName( authenticatedUser.getDisplayName() == null ? "" :  authenticatedUser.getDisplayName());
		
		logger.finer("Email de usuario: " + email);
		if (!email.equals("")) {
			createOrUpdateCencosudProfile(authenticatedUser, email);
			//getting last saved UserProfile to return updated AuthenticatedUser
			profile = getUserProfileByFields(email,DISPLAY_NAME_FIELD).getProfile();
		}

		return profile;
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

	public UserProfileResponse getUserProfileByFields(String email, String fields)
	           throws URISyntaxException, ClientProtocolException, IOException {
	       // create profile domain object
		UserProfileResponse profile =null;
	       try {
	           
	           // call create profile service
	           System.out.println(email);
	           System.out.println(this.apiBaseUrl + USER_PROFILE_PATH + UP_SPECIFIC_PATH);
	           URIBuilder builder = new URIBuilder(this.apiBaseUrl + USER_PROFILE_PATH + UP_SPECIFIC_PATH)
	                   .setParameter("id", email)
	                   .setParameter("fields", fields);
	           HttpGet request = new HttpGet(builder.build());
	       
	           logger.finer("getProfile: " + request.getURI().toString());
	           HttpResponse response = client.execute(request);
	           
	           profile = mapper.readValue(response.getEntity().getContent(), UserProfileResponse.class);
	           logger.finer("createOrUpdateProfile -> Response Body: " + profile);
	           logger.finer("createProfile -> Response Code: " + response.getStatusLine().getStatusCode());
	       } catch (Exception e) {
	           logger.log(Level.SEVERE, e.getMessage(), e);
	       }
	       return profile;
	   }
	
	private void createOrUpdateCencosudProfile(AuthenticatedUser authenticatedUser, String email)
			throws URISyntaxException, ClientProtocolException, IOException {
		try {
			// create profile domain object
			UserProfile profile = getProfileFromAuthenticatedUser(authenticatedUser, email);
			String profileJson = mapper.writeValueAsString(profile);
			// call create profile service
			System.out.println(profileJson);
			System.out.println(this.apiBaseUrl + USER_PROFILE_PATH);
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

	private UserProfile getProfileFromAuthenticatedUser(AuthenticatedUser authenticatedUser, String email) throws ParseException {
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
		newProfile.setDocument("");
		newProfile.setFavorites(favorites);
		newProfile.setAttributes(attributes);
		newProfile.setDisplayName( getFirstWord(authenticatedUser.getDisplayName()) );
		newProfile.setFullName(authenticatedUser.getDisplayName());
		newProfile.setId(email);
		//Social media data
		newProfile.setBirthDate(getDate(getValueIfExists(authenticatedUser.getAttributes(), "birthday")));
		newProfile.setSex(getValueIfExists(authenticatedUser.getAttributes(), "gender"));
		newProfile.setPhone(getValueIfExists(authenticatedUser.getAttributes(), "phone"));
		//

		return newProfile;
	}

	public Date getDate(String dateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			logger.info("Unparseable Date, returning default");
			return sdf.parse("01/01/3000");
		}
	}
	


	public String getValueIfExists(Map<String, Object> attributes, String key) {
		String value = (String) attributes.get(key);
		if(value == null || "".equals(value) || "null".equals(value)){
			return "";
		}
		return value;
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

	private String getFirstWord(String fullWord) {
		if (fullWord == null){
			return "";
		}
		return fullWord.split(" ")[0];
	}
}
