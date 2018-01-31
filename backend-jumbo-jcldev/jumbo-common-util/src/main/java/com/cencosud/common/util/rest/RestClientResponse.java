package com.cencosud.common.util.rest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RestClientResponse<E> {
	
	private final int status;
    private final E response;
    private final Map<String, List<String>> headers;
    private final String errorBody;
    
	public RestClientResponse(int status) {
        this(status, Collections.<String, List<String>>emptyMap(), "");
    }
    
    public RestClientResponse(int status, Map<String, List<String>> headers, String errorBody) {
        this(status, headers, null, errorBody);
    }
    
    RestClientResponse(int status, Map<String, List<String>> headers, E response, String errorBody) {
        this.status = status;
        this.headers = Collections.unmodifiableMap(headers);
        this.response = response;
        this.errorBody = errorBody;
    }

    public int getStatus() {
        return status;
    }

    public E getResponse() {
        return response;
    }

    public RestClientResponse<E> setResponse(E response) {
        return new RestClientResponse<>(status, headers, response, errorBody);
    }
    
    /**
   	 * @return the headers
   	 */
   	public Map<String, List<String>> getHeaders() {
   		return headers;
   	}

	/**
	 * @return the errorBody
	 */
	public String getErrorBody() {
		return errorBody;
	}

}
