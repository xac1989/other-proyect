package com.cencosud.mobile.login.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

import com.cencosud.mobile.login.model.LoginRequest;
import com.cencosud.mobile.login.model.LoginResponse;
import com.cencosud.mobile.login.model.UserMigration;
import com.cencosud.mobile.login.model.UserMigrationResponse;
import com.cencosud.mobile.login.model.UserProfileResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginService {

	private static final Logger logger = Logger.getLogger(LoginService.class.getName());

	private static final String LOGIN_PATH = "/r2/v1/userlogin";
	private static final String PROFILE_PATH = "/userprofile/r2/v1/profile";
	private static final String MIGRATION_PATH = "/r2/v1/usermigration";
	private static final String CLIENT_ID_HEADER = "X-IBM-Client-Id";
	private static final String CLIENT_SECRET_HEADER = "X-IBM-Client-Secret";

	private String apiBaseUrl;

	private static CloseableHttpClient client;

	public LoginService(String apiBaseUrl, String apiClientId, String apiClientSecret) {
		this.apiBaseUrl = apiBaseUrl;

		List<Header> headers = new ArrayList<Header>();
		headers.add(new BasicHeader(CLIENT_ID_HEADER, apiClientId));
		headers.add(new BasicHeader(CLIENT_SECRET_HEADER, apiClientSecret));
		client = HttpClients.custom().setDefaultHeaders(headers).build();

	}

	public LoginResponse getLoginResponse(String username, String password) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		URIBuilder builder = null;
		builder = new URIBuilder(this.apiBaseUrl + LOGIN_PATH);
		System.out.println("URL------------------_>" + builder.toString());
		HttpPost request = new HttpPost(builder.build());

		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setPassword(password);
		loginRequest.setUserName(username);

		String jsonInString = mapper.writeValueAsString(loginRequest);
		StringEntity entity = new StringEntity(jsonInString);
		request.setEntity(entity);
		request.setHeader("Content-type", "application/json");
		HttpResponse response = client.execute(request);
		System.out.println(response.toString());
		String responseBody = getStringFromInputStream(response.getEntity().getContent()).trim();
		LoginResponse loginresponse = mapper.readValue(responseBody, LoginResponse.class);
		System.out.println("loginresponse : " + loginresponse);
		return loginresponse;
	}

	public UserProfileResponse getUserProfileResponse(String username) throws Exception {
		Map<String, String> parameters = new HashMap<>();
		parameters.put("id", username);
		return getResponse(UserProfileResponse.class, PROFILE_PATH, parameters);
	}
	
	public UserMigration getUserMigration(String rut) throws Exception {
		UserMigrationResponse userMigration = getResponse(UserMigrationResponse.class,
				MIGRATION_PATH.concat("/").concat(rut));
		return userMigration.getUserMigration();
	}
	
	public <T> T getResponse(Class<T> classResult,String path) throws Exception{
		return getResponse(classResult,path,new HashMap<String,String>());
	}

	public <T> T getResponse(Class<T> classResult, String path, Map<String, String> parameters) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		URIBuilder builder = null;
		builder = new URIBuilder(this.apiBaseUrl.concat(path));
		for(Map.Entry<String, String> parameter: parameters.entrySet()){
			builder.setParameter(parameter.getKey(), parameter.getValue());
		}
		System.out.println("URL------------------_>" + builder.toString());
		HttpGet request = new HttpGet(builder.build());
		System.out.println("request : " + request.getURI().toString());
		HttpResponse response = client.execute(request);
		String responseBody = getStringFromInputStream(response.getEntity().getContent()).trim();
		return mapper.readValue(responseBody,classResult );
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
