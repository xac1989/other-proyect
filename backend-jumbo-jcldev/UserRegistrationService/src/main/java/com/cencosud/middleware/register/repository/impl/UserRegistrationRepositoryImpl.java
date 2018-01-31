package com.cencosud.middleware.register.repository.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

import com.cencosud.middleware.register.exception.VtexServiceException;
import com.cencosud.middleware.register.model.UserProfileData;
import com.cencosud.middleware.register.model.enums.RegisterResponseEnum;
import com.cencosud.middleware.register.model.enums.SendCodeResponseEnum;
import com.cencosud.middleware.register.repository.UserRegistrationRepository;
import com.cencosud.middleware.register.util.VtexServiceUtil;

@Repository
public class UserRegistrationRepositoryImpl implements UserRegistrationRepository {

	private final static String PATH_TOKEN = "/api/vtexid/pub/authentication/start";
	private final static String PATH_VALIDATION_CODE = "/api/vtexid/pub/authentication/accesskey/send";
	private final static String PATH_SET_PASSWORD = "/api/vtexid/pub/authentication/classic/setpassword";
	private final static String PATH_UPTATE_PROFILE = "/api/profile-system/pvt/profiles/$USER_EMAIL/personalData";
	
	private static final Logger logger = LoggerFactory.getLogger(UserRegistrationRepositoryImpl.class);

	@Value("${jumbo.scope}")
	private String scope;

	@Autowired
	@Qualifier("vTexService")
	private VtexServiceUtil vtexService;

	@Autowired
	@Qualifier("vTexProfile")
	private VtexServiceUtil vTexProfile;

	@Override
	public SendCodeResponseEnum sendCodeValidation(String email, String token) {
		Map<String, String> queryParam = new HashMap<>();
		queryParam.put("authenticationToken", token);
		queryParam.put("email", escapeUrlString(email));

		String jsonResult;
		try {
			jsonResult = vtexService.executeService(PATH_VALIDATION_CODE, null, String.class, HttpMethod.GET,
					queryParam);
			logger.info("Respuesta al enviar el servicio: "+jsonResult);
			if (StringUtils.isEmpty(jsonResult) || !jsonResult.contains("code")) {
				return SendCodeResponseEnum.SUCCESS;
			}
			return SendCodeResponseEnum.SERVER_ERROR;
		} catch (VtexServiceException exception) {
			try {
				logger.info("Error devuelto: : "+exception.getMessage());
				return SendCodeResponseEnum.findByName(exception.getMessage());
			} catch (Exception e) {
				logger.error(exception.getMessage(), exception);
				return SendCodeResponseEnum.SERVER_ERROR;
			}
		}
	}

	@Override
	public RegisterResponseEnum setAndCreateUser(String codeValidation, String email, String password, String token) {
		Map<String, String> queryParam = new HashMap<>();
		queryParam.put("authenticationToken", token);
		queryParam.put("accessKey", codeValidation);
		queryParam.put("newPassword", escapeUrlString(password));
		queryParam.put("login", escapeUrlString(email));

		String jsonResult = "";
		try {
			jsonResult = vtexService.executeService(PATH_SET_PASSWORD, null, String.class, HttpMethod.GET, queryParam);
			logger.info("Respuesta al enviar el servicio: "+jsonResult);
		} catch (VtexServiceException exception) {
			Integer code = exception.getCode();
			if (code.equals(401)) {
				return RegisterResponseEnum.BLOCKED_USER;
			}
			logger.error(exception.getMessage(), exception);
			return RegisterResponseEnum.SERVER_ERROR;
		}

		if (StringUtils.isEmpty(jsonResult)) {
			return RegisterResponseEnum.SERVER_ERROR;
		}
		JSONObject jsonObj = new JSONObject(jsonResult);
		String authStatus = jsonObj.getString("authStatus");

		try {
			RegisterResponseEnum status = RegisterResponseEnum.findByName(authStatus);
			return status;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return RegisterResponseEnum.SERVER_ERROR;
		}
	}

	@Override
	public String getToken() {
		Map<String, String> queryParam = new HashMap<>();
		queryParam.put("scope", scope);

		String jsonResult = "";
		try {
			jsonResult = vtexService.executeService(PATH_TOKEN, null, String.class, HttpMethod.GET, queryParam);
		} catch (VtexServiceException e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("Respuesta al enviar el servicio: "+jsonResult);
		if (StringUtils.isEmpty(jsonResult))
			return null;
		JSONObject jsonObj = new JSONObject(jsonResult);
		String token = jsonObj.getString("authenticationToken");

		return token;
	}

	@Override
	public void updateUser(String name, String lastName, String email) {
		UserProfileData userProfile = new UserProfileData(name, lastName, email, null);
		try {
			System.out.println("-------------------------->"+userProfile.toString());
			vTexProfile.executeService(PATH_UPTATE_PROFILE.replace("$USER_EMAIL", escapeUrlString(email)), userProfile, String.class,
					HttpMethod.POST);
		} catch (VtexServiceException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public boolean validateVtexEmail(String email) throws VtexServiceException {
		String jsonResponse=null;
		jsonResponse = vTexProfile.executeService(PATH_UPTATE_PROFILE.replace("$USER_EMAIL", escapeUrlString(email)), null,
					String.class, HttpMethod.GET);
		logger.info("Respuesta al enviar el servicio: "+jsonResponse);
		if (StringUtils.isEmpty(jsonResponse) || jsonResponse.equals("null")) {
			return false;
		}
		
		JSONObject jsonObj = new JSONObject(jsonResponse);
		String firstName = jsonObj.optString("firstName", null);
		String lastName = jsonObj.optString("firstName", null);
		if(StringUtils.isNotEmpty(firstName)&&StringUtils.isNotEmpty(lastName)) {
			return true;
		}
		return false;
	}
	
	private String escapeUrlString(String data) {
		String escape = data;
		try {
			escape = URLEncoder.encode(escape, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e1) {
			logger.error(e1.getMessage(), e1);
		}
		
		return escape;
	}

}
