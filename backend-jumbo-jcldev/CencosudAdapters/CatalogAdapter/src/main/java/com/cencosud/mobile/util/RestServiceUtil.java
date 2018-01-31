package com.cencosud.mobile.util;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cencosud.mobile.search.adapter.NotFoundException;

/**
 * 
 * 
 * <h1>RestServiceUtil</h1>
 * <p>
 * Utilidad para consumir servicios del API
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jun 30, 2017
 */
@Service
public class RestServiceUtil {

    private static final Logger logger = Logger.getLogger(RestServiceUtil.class.getName());

	private static final String CLIENT_ID_HEADER = "X-IBM-Client-Id";
	private static final String CLIENT_SECRET_HEADER = "X-IBM-Client-Secret";

	@Value("${api_base_url}")
    private String apiBaseUrl;
    @Value("${api_client_id}")
    private String apiClientId;
    @Value("${api_secret}")
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
	public <T, E> RestServiceResponse<E> executeService(String path, T document, Class<E> responseClass, Map<String, Object> queryParams,
			RequestProtocolEnum method) throws NotFoundException {
		try {
			WebTarget fullTextTarget = ClientBuilder.newClient().target(apiBaseUrl).path(path);
			if (queryParams != null) {
				for (Map.Entry<String, Object> tmpEntry : queryParams.entrySet()) {
					fullTextTarget = fullTextTarget.queryParam(tmpEntry.getKey(), tmpEntry.getValue());
				}
			}

			logger.finer(String.format("----------------> %s %s", method.getName(), fullTextTarget.getUri()));
			Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON);

			invocationBuilder.header(CLIENT_ID_HEADER, this.getApiClientId());
			invocationBuilder.header(CLIENT_SECRET_HEADER, this.getApiSecret());
			Response response = null;

			switch (method) {
			case GET:
				response = invocationBuilder.get();
				break;
			case POST:
				response = invocationBuilder.post(Entity.entity(document, MediaType.APPLICATION_JSON));
				break;
			case PUT:
				response = invocationBuilder.put(Entity.entity(document, MediaType.APPLICATION_JSON));
				break;
			case DELETE:
				response = invocationBuilder.delete();
				break;
			}
			logger.finer("Se ejecuto el servicio con status " + response.getStatus());
			RestServiceResponse<E> serviceResponse = new RestServiceResponse<E>(response.getStatus(), response.getHeaders());
			if (response.getStatus() == Status.NO_CONTENT.getStatusCode()) {
				return serviceResponse;
			}
			return serviceResponse.setResponse(response.readEntity(responseClass));
		} catch (Exception e) {
		    logger.log(Level.SEVERE, "Error al consumir el servicio " + path, e);
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
