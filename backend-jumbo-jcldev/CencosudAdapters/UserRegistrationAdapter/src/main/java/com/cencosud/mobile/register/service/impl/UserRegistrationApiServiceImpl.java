package com.cencosud.mobile.register.service.impl;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.cencosud.mobile.register.dto.CreateUserRequest;
import com.cencosud.mobile.register.dto.SendCodeRequest;
import com.cencosud.mobile.register.exception.NotFoundException;
import com.cencosud.mobile.register.model.GenericResponse;
import com.cencosud.mobile.register.model.enums.RequestProtocolEnum;
import com.cencosud.mobile.register.service.UserRegistrationApiService;
import com.cencosud.mobile.register.util.RestServiceUtil;

/**
 * 
 * 
 * <h1>RegisterApiServiceImpl</h1>
 * <p>
 * Implementación del servicio de Registro
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Sep 1, 2017
 */
@Service
public class UserRegistrationApiServiceImpl implements UserRegistrationApiService {

	private RestServiceUtil restServiceUtil;
	
	private static final String SEND_CODE_VALIDATION = "/userRegistration/r2/v1/send";
	private static final String USER_REGISTRATION = "/userRegistration/r2/v1/setPassword";
	
	static Logger logger = Logger.getLogger(UserRegistrationApiServiceImpl.class.getName());
	/**
	 * @return the restServiceUtil
	 */
	public RestServiceUtil getRestServiceUtil() {
		return restServiceUtil;
	}

	/**
	 * @param restServiceUtil
	 *            the restServiceUtil to set
	 */
	public void setRestServiceUtil(RestServiceUtil restServiceUtil) {
		this.restServiceUtil = restServiceUtil;
	}

	@Override
	public GenericResponse sendCode(SendCodeRequest request) {
		GenericResponse response = null;
		try {
			response = restServiceUtil.executeService(SEND_CODE_VALIDATION, request, GenericResponse.class, null, RequestProtocolEnum.POST);
			
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public GenericResponse registerUser(CreateUserRequest request) {
		GenericResponse response = null;
		try {
			response = restServiceUtil.executeService(USER_REGISTRATION, request, GenericResponse.class, null, RequestProtocolEnum.POST);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public static void main(String[] args) {
		UserRegistrationApiServiceImpl userApi = new UserRegistrationApiServiceImpl();

		RestServiceUtil restService = new RestServiceUtil();
		restService.setApiBaseUrl("https://api.us.apiconnect.ibmcloud.com/supermercados-cencosud-wong-quality/qa");
		restService.setApiClientId("df5f6e04-9a2e-4920-b298-3302f4dd93c4");
		restService.setApiSecret("pT4pN8pH7oM7jW2vU0nK4gM0fV3iB2tM8tC5rU2rN0xG1vX5aY");

		userApi.setRestServiceUtil(restService);

		try {
			long start = System.currentTimeMillis();
			SendCodeRequest request = new SendCodeRequest("fweewffwe@fasf.com", true);
			GenericResponse usl = userApi.sendCode(request);
			long end = System.currentTimeMillis();
			System.out.println("Tiempo de ejecución: " + (end - start) + "ms");
			System.out.println(usl.toString());
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
}
