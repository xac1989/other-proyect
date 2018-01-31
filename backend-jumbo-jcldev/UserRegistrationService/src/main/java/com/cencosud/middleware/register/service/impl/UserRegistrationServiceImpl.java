package com.cencosud.middleware.register.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.register.dto.CreateUserRequest;
import com.cencosud.middleware.register.dto.SendCodeRequest;
import com.cencosud.middleware.register.exception.VtexServiceException;
import com.cencosud.middleware.register.model.enums.RegisterResponseEnum;
import com.cencosud.middleware.register.model.enums.SendCodeResponseEnum;
import com.cencosud.middleware.register.repository.UserRegistrationRepository;
import com.cencosud.middleware.register.service.UserRegistrationService;

/**
 * 
 * 
 * <h1>UserRegistrationServiceImpl</h1>
 * <p>
 * Servicios para el registro de usuario
 * </p>
 * 
 * @author fernando.castro
 * @version 1.0
 * @since Sep 1, 2017
 */
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

	// Region2 = JUMBO CHILE
	private static final String REGION_ID = "r2";
	private String token = "";
	
	private static final Logger logger = LoggerFactory.getLogger(UserRegistrationServiceImpl.class);

	@Autowired
	private UserRegistrationRepository userRegistrationRepository;

	@Override
	public String getRegionId() {
		return REGION_ID;
	}

	@Override
	public SendCodeResponseEnum sendCodeValidation(SendCodeRequest request) {
		String tmpToken = this.token;
		SendCodeResponseEnum response = null;
		logger.info("Objeto request recibido: "+request);
		try {
			if (request.getUserShouldExist()) {
				if (!userRegistrationRepository.validateVtexEmail(request.getEmail())) {
					return SendCodeResponseEnum.EMAIL_DOESNT_EXIST;
				}
			} else {
				if (userRegistrationRepository.validateVtexEmail(request.getEmail())) {
					return SendCodeResponseEnum.EXISTING_EMAIL;
				}
			}
		} catch (VtexServiceException e) {
			logger.error(e.getMessage(), e);
			return SendCodeResponseEnum.SERVER_ERROR;
		}
		if (!StringUtils.isEmpty(tmpToken)) {
			response = userRegistrationRepository.sendCodeValidation(request.getEmail(), tmpToken);
		}

		if (response == null || response.equals(SendCodeResponseEnum.INVALID_TOKEN)) {
			String newToken = requestToken(tmpToken);
			response = userRegistrationRepository.sendCodeValidation(request.getEmail(), newToken);
		}
		return response;
	}

	private String requestToken(String tmpToken) {
		if (tmpToken.equals(this.token)) {
			synchronized (this) {
				if (tmpToken.equals(this.token)) {
					String newToken = userRegistrationRepository.getToken();
					this.token = newToken;
					return newToken;
				}
			}
		}
		return tmpToken;
	}

	@Override
	public RegisterResponseEnum setAndCreateUser(CreateUserRequest request) {
		String tmpToken = this.token;
		RegisterResponseEnum response = null;
		String scapePassword = request.getPassword();

		if (!StringUtils.isEmpty(tmpToken)) {
			response = userRegistrationRepository.setAndCreateUser(request.getValidationCode(), request.getEmail(),
					scapePassword, tmpToken);
		}

		if (response == null || response.equals(RegisterResponseEnum.INVALID_TOKEN)) {
			String newToken = requestToken(tmpToken);
			response = userRegistrationRepository.setAndCreateUser(request.getValidationCode(), request.getEmail(),
					scapePassword, newToken);
		}
		if (response.equals(RegisterResponseEnum.SUCCESS)) {
			if (StringUtils.isNotEmpty(request.getName()) && StringUtils.isNotEmpty(request.getLastName())) {
				userRegistrationRepository.updateUser(request.getName(), request.getLastName(), request.getEmail());
			}
		}
		return response;
	}

}
