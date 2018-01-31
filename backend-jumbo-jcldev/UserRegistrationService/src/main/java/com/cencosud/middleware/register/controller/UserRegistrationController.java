package com.cencosud.middleware.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.register.annotation.Loggable;
import com.cencosud.middleware.register.dto.CreateUserRequest;
import com.cencosud.middleware.register.dto.GenericResponse;
import com.cencosud.middleware.register.dto.SendCodeRequest;
import com.cencosud.middleware.register.factory.UserRegistrationServiceFactory;
import com.cencosud.middleware.register.model.enums.RegisterResponseEnum;
import com.cencosud.middleware.register.model.enums.SendCodeResponseEnum;

/**
 * 
 * 
 * <h1>RegisterController</h1>
 * <p>
 * Servicios expuestos
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Sep 5, 2017
 */
@Loggable
@RestController
@RequestMapping(path = { "/{region}/v1/userRegistration", "/{region}/v1/userRegistration/" }, produces = "application/json; charset=UTF-8")
public class UserRegistrationController {
	
	@Autowired
	private UserRegistrationServiceFactory userRegistrationServiceFactory;
	
	/**
	 * Servicio encargo de enviar el código de validación
	 * @param request {@link SendCodeRequest}
	 * @param region {@link String}
	 * @return {@link ResponseEntity}<{@link GenericResponse}>
	 */
	@RequestMapping(path = { "send/",
	"send" }, method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public  ResponseEntity<GenericResponse> senCodevalidation(@RequestBody(required = true) SendCodeRequest request,
			@PathVariable("region") String region) {
		
		SendCodeResponseEnum response = userRegistrationServiceFactory.getService(region).sendCodeValidation(request);
		
		if(response.getId() == 0) {
			return new ResponseEntity<>(new GenericResponse(), HttpStatus.valueOf(response.getHttpCode()));
		}
		return new ResponseEntity<>(new GenericResponse(response.getId(), response.getDescription()), HttpStatus.valueOf(response.getHttpCode()));
	}
	
	/**
	 * Servicio que crea el usuario.
	 * @param request {@link CreateUserRequest}
	 * @param region {@link String}
	 * @return {@link ResponseEntity}<{@link GenericResponse}>
	 */
	@RequestMapping(path = { "setPassword/",
	"setPassword" }, method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public  ResponseEntity<GenericResponse> setAndCreateUser(@RequestBody(required = true) CreateUserRequest request,
			@PathVariable("region") String region) {
		RegisterResponseEnum response = userRegistrationServiceFactory.getService(region).setAndCreateUser(request);
		
		if(response.getId() == 0) {
			return new ResponseEntity<>(new GenericResponse(), HttpStatus.valueOf(response.getHttpCode()));
		}
		return new ResponseEntity<>(new GenericResponse(response.getId(), response.getDescription()), HttpStatus.valueOf(response.getHttpCode()));
	}
}
