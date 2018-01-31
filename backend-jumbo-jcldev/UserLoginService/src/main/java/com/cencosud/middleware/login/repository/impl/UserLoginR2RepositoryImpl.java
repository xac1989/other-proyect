package com.cencosud.middleware.login.repository.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.cencosud.middleware.login.client.HttpLoginClient;
import com.cencosud.middleware.login.configuration.ApplicationConfig.JanisProperties;
import com.cencosud.middleware.login.configuration.ApplicationConfig.RegionProperties;
import com.cencosud.middleware.login.model.UserMigration;
import com.cencosud.middleware.login.model.UserLogin;
import com.cencosud.middleware.login.repository.UserLoginRepository;

public class UserLoginR2RepositoryImpl implements UserLoginRepository {

	private static final Logger logger = LoggerFactory.getLogger(UserLoginR2RepositoryImpl.class);

	private RegionProperties regionProperties;
	private JanisProperties janisProperties;
	
	private final static String DATE_FORMAT= "yyyy-MM-dd HH:mm:ss";

	public void setRegionProperties(RegionProperties regionProperties) {
		this.regionProperties = regionProperties;
	}

	public void setJanis(JanisProperties janis) {
		this.janisProperties = janis;
	}

	@Override
	public String getToken() {

		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.add("scope", regionProperties.getVtexToken().getScope());
		String schema = regionProperties.getVtexToken().getSchema();
		String host = regionProperties.getVtexToken().getHost();
		String path = regionProperties.getVtexToken().getPath();
		String response = HttpLoginClient.executeAsObject(schema, host, path, String.class, parameters);
		JSONObject jsonObject = new JSONObject(response);
		return jsonObject.get("authenticationToken").toString();
	}

	@Override
	public UserLogin loginUser(String token, String userName, String password) throws Exception {
		logger.info("starting login to " + userName);
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.add("authenticationToken", token);
		parameters.add("login", userName);
		parameters.add("password", password);

		String schema = regionProperties.getVtexLogin().getSchema();
		String host = regionProperties.getVtexLogin().getHost();
		String path = regionProperties.getVtexLogin().getPath();

		UserLogin userLogin = HttpLoginClient.executeAsObject(schema, host, path, UserLogin.class, parameters);
		userLogin.setDomain(regionProperties.getVtexCookie().getDomain());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 1);
		DateFormat format = new SimpleDateFormat(DATE_FORMAT);
		format.setTimeZone(TimeZone.getTimeZone("GMT"));

		String expirationDate = format.format(calendar.getTime());
		userLogin.setExpirationDate(expirationDate);
		userLogin.setPath(regionProperties.getVtexCookie().getPath());
		return userLogin;
	}

	public UserMigration getMigrationInfo(String rut) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.add("doc", rut);

		Map<String, String> headersMap = new HashMap<>();
		headersMap.put(janisProperties.getHeaderKey(), janisProperties.getHeaderValue());

		UserMigration userMigration = HttpLoginClient.executeAsObject(janisProperties.getSchema(), janisProperties.getHost(),
				janisProperties.getPath(), UserMigration.class, parameters, janisProperties.getPort(), headersMap);
		return userMigration;
	}

}
