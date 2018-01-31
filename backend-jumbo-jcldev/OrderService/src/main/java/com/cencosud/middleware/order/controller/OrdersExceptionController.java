package com.cencosud.middleware.order.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.order.model.ErrorMessage;


@ControllerAdvice
@RestController
public class OrdersExceptionController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrdersExceptionController.class);

	@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleException(HttpServletRequest req, Exception ex) {
        String errorMessageId = UUID.randomUUID().toString();
        logger.error("Error: " + errorMessageId + " Message: " + ex.getMessage(), ex);

        ErrorMessage em = new ErrorMessage(
        		errorMessageId,
        		String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
        		HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), 
        		ex.getMessage()
        		);
        
        return em;
    }
    
}