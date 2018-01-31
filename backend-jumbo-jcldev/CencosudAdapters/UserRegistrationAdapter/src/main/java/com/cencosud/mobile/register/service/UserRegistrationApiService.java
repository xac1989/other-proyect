package com.cencosud.mobile.register.service;

import com.cencosud.mobile.register.dto.CreateUserRequest;
import com.cencosud.mobile.register.dto.SendCodeRequest;
import com.cencosud.mobile.register.model.GenericResponse;

/**
 * 
 * 
 * <h1>UserRegistrationApiService</h1>
 * <p>
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Sep 6, 2017
 */
public interface UserRegistrationApiService {
	
	GenericResponse sendCode(SendCodeRequest request);
	GenericResponse registerUser(CreateUserRequest request);

}
