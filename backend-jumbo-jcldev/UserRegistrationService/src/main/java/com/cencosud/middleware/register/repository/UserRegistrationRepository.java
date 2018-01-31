package com.cencosud.middleware.register.repository;

import com.cencosud.middleware.register.exception.VtexServiceException;
import com.cencosud.middleware.register.model.enums.RegisterResponseEnum;
import com.cencosud.middleware.register.model.enums.SendCodeResponseEnum;

/**
 * 
 * 
 * <h1>UserRegistrationRepository</h1>
 * <p>
 * Servicio de consulta de información
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Sep 1, 2017
 */
public interface UserRegistrationRepository {
	
	/**
	 * Obtiene el token de consulta
	 * @return {@link String}
	 */
	String getToken();
	
	/**
	 * Método encargado de enviar el código al email del usuario. 
	 * @param email {@link String}
	 * @param token {@link String}
	 * @return {@link SendCodeResponseEnum}
	 */
	SendCodeResponseEnum sendCodeValidation(String email, String token);
	
	/**
	 * Método para crear al usuario.
	 * @param codeValidation {@link String}
	 * @param email {@link String}
	 * @param password {@link String}
	 * @return {@link RegisterResponseEnum}
	 */
	RegisterResponseEnum setAndCreateUser(String codeValidation, String email, String password, String token);
	
	/**
	 * Metodo para actualizar al usuario
	 * @param name {@link String}}
	 * @param lastName {@link String}
	 */
	void updateUser(String name, String lastName, String email);
	
	/**
	 * 
	 * @param email {@link String}
	 * @return
	 * @throws VtexServiceException 
	 */
	boolean validateVtexEmail(String email) throws VtexServiceException;
}
