package com.cencosud.middleware.product.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.product.model.ErrorMessage;

/**
 * 
 * 
 * <h1>ExceptionHandlerController</h1>
 * <p>
 * Clase encargada de cachar todas las excepciones y regresar un mensaje general.
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Mar 22, 2017
 */
@ControllerAdvice
@RestController
public class ExceptionHandlerController {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage handleException(HttpServletRequest req, Exception ex) {
		String errorMessageId = UUID.randomUUID().toString();
		logger.error("Error: " + errorMessageId + " Message: " + ex.getMessage(), ex);

		ErrorMessage em = new ErrorMessage();
		em.setHttpCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		em.setHttpMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		em.setMoreInformation(ex.getMessage());
		return em;
	}
}
