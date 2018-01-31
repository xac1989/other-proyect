package com.cencosud.mobile.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.springframework.util.CollectionUtils;

public class RestServiceResponse<E> {

    private final int status;
    private final E response;
    private final Map<String, Object> headers;

    public RestServiceResponse(int status, Map<String, Object> headers) {
        this(status, headers, null);
    }

    public RestServiceResponse(int status, MultivaluedMap<String, Object> headers) {
        this(status, toMap(headers), null);
    }

    private RestServiceResponse(int status, Map<String, Object> headers, E response) {
        this.status = status;
        this.headers = Collections.unmodifiableMap(headers);
        this.response = response;
    }

    public int getStatus() {
        return status;
    }

    public E getResponse() {
        return response;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public RestServiceResponse<E> setResponse(E response) {
        return new RestServiceResponse<>(status, headers, response);
    }

    private static Map<String, Object> toMap(MultivaluedMap<String, Object> headers) {
        Map<String, Object> map = new HashMap<>();
        for (Map.Entry<String, List<Object>> entry : headers.entrySet()) {
            if (!CollectionUtils.isEmpty(entry.getValue())) {
                if (entry.getValue().size() == 1) {
                    map.put(entry.getKey(), entry.getValue().get(0));
                } else {
                    map.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return map;
    }

    public Response toResponse() {
        return Response.status(getStatus()).entity(getResponse()).build();
    }
}
