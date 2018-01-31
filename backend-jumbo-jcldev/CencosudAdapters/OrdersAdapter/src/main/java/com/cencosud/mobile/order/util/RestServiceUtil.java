package com.cencosud.mobile.order.util;

import java.util.Map;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cencosud.mobile.order.adapter.NotFoundException;
/**
 * 
 * 
 * <h1>RestServiceUtil</h1>
 * <p>
 * Utilidad para consumir servicios del API
 * </p>
 */
public class RestServiceUtil {

	private static final String CLIENT_ID_HEADER = "X-IBM-Client-Id";
	private static final String CLIENT_SECRET_HEADER = "X-IBM-Client-Secret";

	private String apiBaseUrl;
	private String apiClientId;
	private String apiSecret;
	
	
	/**
	 * 
	 * @param path
	 *            {@link String} Direcion del servicio.
	 * @param document
	 *            {@link T} Objeto request
	 * @param responseClass
	 *            {@link Class}<{@link E}> Tipo de respuesta
	 * @param queryParams
	 *            {@link Map}<{@link String}, {@link Object}> query param de la
	 *            url
	 * @param method
	 *            {@link RequestProtocolEnum} Tipo de petición
	 * @return {@link E}i
	 * @throws NotFoundException
	 */
	public <E> E executeService(String path, Class<E> responseClass, Map<String, Object> queryParams)
			throws NotFoundException {
		try {
			WebTarget fullTextTarget = ClientBuilder.newClient().target(apiBaseUrl).path(path);
			if (queryParams != null) {
				for (Map.Entry<String, Object> tmpEntry : queryParams.entrySet()) {
					fullTextTarget = fullTextTarget.queryParam(tmpEntry.getKey(), tmpEntry.getValue());
				}
			}
			
			Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON);

			invocationBuilder.header(CLIENT_ID_HEADER, this.getApiClientId());
			invocationBuilder.header(CLIENT_SECRET_HEADER, this.getApiSecret());
			Response response = null;

			response = invocationBuilder.get();
			if(response.getStatus() == Status.NO_CONTENT.getStatusCode()){
				return null;
			}
			return response.readEntity(responseClass);
		} catch (Exception e) {
			throw new NotFoundException(Status.NOT_FOUND.getStatusCode(), e.getMessage());
		}
	}

	/**
	 * @return the apiBaseUrl
	 */
	public String getApiBaseUrl() {
		return apiBaseUrl;
	}

	/**
	 * @param apiBaseUrl
	 *            the apiBaseUrl to set
	 */
	public void setApiBaseUrl(String apiBaseUrl) {
		this.apiBaseUrl = apiBaseUrl;
	}

	/**
	 * @return the apiClientId
	 */
	public String getApiClientId() {
		return apiClientId;
	}

	/**
	 * @param apiClientId
	 *            the apiClientId to set
	 */
	public void setApiClientId(String apiClientId) {
		this.apiClientId = apiClientId;
	}

	/**
	 * @return the apiSecret
	 */
	public String getApiSecret() {
		return apiSecret;
	}

	/**
	 * @param apiSecret
	 *            the apiSecret to set
	 */
	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

}
