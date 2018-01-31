package com.cencosud.middleware.address.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.address.model.ErrorMessage;
import com.cencosud.middleware.address.model.ErrorResponse;

@ControllerAdvice
@RestController
public class ExceptionHandlerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);
	private static final String CUSTOM_CODE_ILEGAL_ARG = "8";

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleException(HttpServletRequest req, Exception ex) {
		String errorMessageId = UUID.randomUUID().toString();
		LOGGER.error("Error: " + errorMessageId + " Message: " + ex.getMessage(), ex);

		ErrorResponse response = new ErrorResponse();
		ErrorMessage em = new ErrorMessage();
		em.setHttpCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		em.setHttpMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		response.setErrorMessage(em);
		return response;
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleIllegalArgument(HttpServletRequest req, Exception ex) {
		String errorMessageId = UUID.randomUUID().toString();
		LOGGER.error("Error: {} Message: {}", errorMessageId, ex.getMessage()); 
		LOGGER.error("", ex);
		
		ErrorMessage errorMessage = new ErrorMessage(CUSTOM_CODE_ILEGAL_ARG, ex.getMessage());
		ErrorResponse response = new ErrorResponse();
		response.setErrorMessage(errorMessage);
		
		return response;
	}
}
