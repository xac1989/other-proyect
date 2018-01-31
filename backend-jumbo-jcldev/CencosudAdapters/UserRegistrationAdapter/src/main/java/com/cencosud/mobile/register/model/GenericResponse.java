package com.cencosud.mobile.register.model;

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
public class GenericResponse {

	private Error error;
	
	
	public GenericResponse() {
	}
	
	/**
	 * 
	 * @param error
	 */
	public GenericResponse(Error error) {
		this.error = error;
	}
	
	/**
	 * @return the error
	 */
	public Error getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(Error error) {
		this.error = error;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GenericResponse [error=");
		builder.append(error.toString());
		builder.append("]");
		return builder.toString();
	}




	public class Error{
		private Integer code;
		private String message;
		
		public Error() {
		
		}
		
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

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Error [code=");
			builder.append(code);
			builder.append(", message=");
			builder.append(message);
			builder.append("]");
			return builder.toString();
		}
		
	}
	
}
