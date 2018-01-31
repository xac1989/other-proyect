package com.cencosud.middleware.register.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * 
 * <h1>GenericResponse</h1>
 * <p>
 * Repuesta generica que contiene el código y el error del servicio.
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Sep 4, 2017
 */
@JsonInclude(Include.NON_NULL)
public class GenericResponse {

	private Error error;
	
	/**
	 * 
	 * @param code
	 * @param message
	 */
	public GenericResponse(Integer code, String message) {
		error = new Error(code, message);
	}
	
	public GenericResponse() {
		error = null;
	}
	
	/**
	 * @return the error
	 */
	public Error getError() {
		return error;
	}


	public class Error{
		private final Integer code;
		private final String message;
		
		/**
		 * @param code
		 * @param message
		 */
		public Error(Integer code, String message) {
			super();
			this.code = code;
			this.message = message;
		}

		/**
		 * @return the code
		 */
		public Integer getCode() {
			return code;
		}

		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}
		
		
		
	}
	
}
