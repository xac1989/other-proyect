package com.cencosud.middleware.register.service;

import com.cencosud.middleware.register.dto.CreateUserRequest;
import com.cencosud.middleware.register.dto.SendCodeRequest;
import com.cencosud.middleware.register.model.enums.RegisterResponseEnum;
import com.cencosud.middleware.register.model.enums.SendCodeResponseEnum;

/**
 * 
 * 
 * <h1>UserRegistrationService</h1>
 * <p>
 * Logica del servicio de registro de uauario.
 * </p>
 * 
 * @author fernando.castro
 * @version 1.0
 * @since Sep 1, 2017
 */
public interface UserRegistrationService {
	
	/**
	 * 
	 * @return {@link String}
	 */
	String getRegionId();

	/**
	 * 
	 * @param request {@link SendCodeRequest}
	 * @return {@link SendCodeResponseEnum}
	 */
	SendCodeResponseEnum sendCodeValidation(SendCodeRequest request);
	
	/**
	 * Método para crear al usuario.
	 * @param request {@link CreateUserRequest request}
	 * @return {@link RegisterResponseEnum}
	 */
	RegisterResponseEnum setAndCreateUser(CreateUserRequest request);
}
