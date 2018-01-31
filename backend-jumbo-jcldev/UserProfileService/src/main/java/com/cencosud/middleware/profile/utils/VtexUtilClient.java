package com.cencosud.middleware.profile.utils;

import java.net.URI;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cencosud.middleware.profile.annotation.Loggable;

/**
 * 
 * 
 * <h1>VtexClient</h1>
 * <p>
 * Cliente para utilizar los servicios de Vtex
 * </p>
 * 
 * @author fernando.castro
 * @version 1.0
 * @since Jun 26, 2017
 */
public class VtexUtilClient {

	private final static String X_VTEX_API_APPKEY = "X-VTEX-API-AppKey";
	private final static String X_VTEX_API_APPTOKEN = "X-VTEX-API-AppToken";

	private final String connectionUrl;
	private final Integer port;
	private final String apiKey;
	private final String secretKey;
	private final String schema;

	private static final Logger logger = LoggerFactory.getLogger(VtexUtilClient.class);

	/**
	 * @param connectionUrl
	 *            {@link String}
	 * @param port
	 *            {@link Integer}
	 * @param apiKey
	 *            {@link String}
	 * @param secretKey
	 *            {@link String}
	 * @param schema
	 *            {@link String}
	 */
	public VtexUtilClient(String connectionUrl, Integer port, String apiKey, String secretKey, String schema) {
		super();
		this.connectionUrl = connectionUrl;
		this.port = port;
		this.apiKey = apiKey;
		this.secretKey = secretKey;
		this.schema = schema;
	}
	

	/**
	 * 
	 * @param url {@link URI} URL del servicio Vtex
	 * @param document {@link E} Documento enviado por en el RequestBody<br> debe ser nulo en el caso de ser GET
	 * @param responseClass {@link T} Tipo de respuesta para el {@link ResponseEntity}<br> en caso de peticiones DELETE
	 * este parametro debe ser {@link Void}
	 * @param method {@link HttpMethod} Tipo de operación a ejecutar
	 * @return {@link ResponseEntity}
	 */
	@Loggable
	@SuppressWarnings("unchecked")
	private <T, E> ResponseEntity<T> doExecuteService(URI url, E document, Class<T> responseClass, HttpMethod method,
			Map<String, String> headers) {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

		RestTemplate restTemplate = new RestTemplate(requestFactory);
		ResponseEntity<T> result;
		logger.info("Inicia ejecucion servicio...");

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(X_VTEX_API_APPKEY, this.apiKey);
		httpHeaders.add(X_VTEX_API_APPTOKEN, this.secretKey);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		if (!CollectionUtils.isEmpty(headers)) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				httpHeaders.add(entry.getKey(), entry.getValue());
			}
		}
		HttpEntity<?> entity;
		if (method == HttpMethod.GET || method == HttpMethod.DELETE) {
			entity = new HttpEntity<>(httpHeaders);
		} else {
			entity = new HttpEntity<>(document, httpHeaders);
		}

		if (method == HttpMethod.DELETE) {
			//result = restTemplate.exchange(url, method, entity, res.);
			responseClass = (Class<T>) Void.class;
		}// else {
		result = restTemplate.exchange(url, method, entity, responseClass);
		return result;
	}

	/**
	 * Ejecuta un servicio usando la configuración de este cliente y la url provista. Permite convertir el resultado
	 * de la respuesta del servicio invocado en un objeto Java.
	 * @param url Url a consultar. Se agrega luego de la url base configurada en esta instancia del cliente.
	 * @param document {@link E} Documento enviado por en el RequestBody<br> debe ser nulo en el caso de ser GET
	 * @param responseClass {@link T} Tipo de respuesta para el {@link ResponseEntity}<br> en caso de peticiones DELETE
	 * este parametro debe ser {@link Void}
	 * @param method {@link HttpMethod} Tipo de operación a ejecutar
	 * @return Objeto resultado de parsear la respuesta del servicio invocado.
	 */
	public <T, E> T executeService(String url, E document, Class<T> responseClass, HttpMethod method) {
		return executeService(url, document, responseClass, method, null, null);
	}

	/**
	 * Ejecuta un servicio usando la configuración de este cliente y la url provista. Permite convertir el resultado
	 * de la respuesta del servicio invocado en un objeto Java.
	 * @param url Url a consultar. Se agrega luego de la url base configurada en esta instancia del cliente.
	 * @param document {@link E} Documento enviado por en el RequestBody<br> debe ser nulo en el caso de ser GET
	 * @param responseClass {@link T} Tipo de respuesta para el {@link ResponseEntity}<br> en caso de peticiones DELETE
	 * este parametro debe ser {@link Void}
	 * @param method {@link HttpMethod} Tipo de operación a ejecutar
	 * @param queryParams Parámetros para agregar en el query string de la url
	 * @return Objeto resultado de parsear la respuesta del servicio invocado.
	 */
	public <T, E> T executeService(String url, E document, Class<T> responseClass, HttpMethod method, Map<String, String> queryParams) {
		return executeService(url, document, responseClass, method, queryParams,null);
	}

	/**
	 * Ejecuta un servicio usando la configuración de este cliente y la url provista. Permite convertir el resultado
	 * de la respuesta del servicio invocado en un objeto Java.
	 * @param url Url a consultar. Se agrega luego de la url base configurada en esta instancia del cliente.
	 * @param document {@link E} Documento enviado por en el RequestBody<br> debe ser nulo en el caso de ser GET
	 * @param responseClass {@link T} Tipo de respuesta para el {@link ResponseEntity}<br> en caso de peticiones DELETE
	 * este parametro debe ser {@link Void}
	 * @param method {@link HttpMethod} Tipo de operación a ejecutar
	 * @param queryParams Parámetros para agregar en el query string de la url
	 * @param headers Cabeceras a agregar al request.
	 * @return Objeto resultado de parsear la respuesta del servicio invocado.
	 */
	public <T, E> T executeService(String url, E document, Class<T> responseClass, HttpMethod method,
			Map<String, String> queryParams, Map<String, String> headers) {
		return executeServiceAsValid(url, document, responseClass, method, queryParams, headers).getBody();
	}

	/**
	 * Ejecuta un servicio usando la configuración de este cliente y la url provista. Permite convertir el resultado
	 * de la respuesta del servicio invocado en un objeto Java. También se obtienen las cabeceras de la respuesta
	 * @param url Url a consultar. Se agrega luego de la url base configurada en esta instancia del cliente.
	 * @param document {@link E} Documento enviado por en el RequestBody<br> debe ser nulo en el caso de ser GET
	 * @param responseClass {@link T} Tipo de respuesta para el {@link ResponseEntity}<br> en caso de peticiones DELETE
	 * este parametro debe ser {@link Void}
	 * @param method {@link HttpMethod} Tipo de operación a ejecutar
	 * @param queryParams Parámetros para agregar en el query string de la url
	 * @param headers Cabeceras a agregar al request.
	 * @return Objeto resultado de parsear la respuesta del servicio invocado.
	 */
	public <T, E> ResponseEntity<T> executeServiceAsValid(String url, E document, Class<T> responseClass, HttpMethod method,
			Map<String, String> queryParams, Map<String, String> headers) {
		StringBuilder sb = new StringBuilder();
		sb.append(schema).append("://").append(connectionUrl).append(url);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(sb.toString());

		if (!CollectionUtils.isEmpty(queryParams)) {
			for(Map.Entry<String, String> entry : queryParams.entrySet()) {
				builder.queryParam(entry.getKey(), entry.getValue());
			}
		}

		return doExecuteService(builder.build(true).encode().toUri(), document, responseClass, method, headers);
	}
	
	
	
//	/**
//	 * 
//	 * @param url {@link URI} URL del servicio Vtex
//	 * @param document {@link E} Documento enviado por en el RequestBody<br> debe ser nulo en el caso de ser GET
//	 * @param responseClass {@link T} Tipo de respuesta para el {@link ResponseEntity}<br> en caso de peticiones DELETE
//	 * este parametro debe ser {@link Void}
//	 * @param method {@link HttpMethod} Tipo de operación a ejecutar
//	 * @return {@link ResponseEntity}
//	 */
//	@Loggable
//	@SuppressWarnings("unchecked")
//	private <T, E> ResponseEntity<T> doExecuteServiceHomolog(URI url, E document, Class<T> responseClass, HttpMethod method,
//			Map<String, String> headers) {
//		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//
//		RestTemplate restTemplate = new RestTemplate(requestFactory);
//		ResponseEntity<T> result;
//		logger.info("Inicia ejecucion servicio...");
//
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.add(X_VTEX_API_APPKEY, this.apiKeyHomolog);
//		httpHeaders.add(X_VTEX_API_APPTOKEN, this.secretKeyHomolog);
//		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//		if (!CollectionUtils.isEmpty(headers)) {
//			for (Map.Entry<String, String> entry : headers.entrySet()) {
//				httpHeaders.add(entry.getKey(), entry.getValue());
//			}
//		}
//		HttpEntity<?> entity;
//		if (method == HttpMethod.GET || method == HttpMethod.DELETE) {
//			entity = new HttpEntity<>(httpHeaders);
//		} else {
//			entity = new HttpEntity<>(document, httpHeaders);
//		}
//
//		if (method == HttpMethod.DELETE) {
//			//result = restTemplate.exchange(url, method, entity, res.);
//			responseClass = (Class<T>) Void.class;
//		}// else {
//		result = restTemplate.exchange(url, method, entity, responseClass);
//		return result;
//	}
//	
//	/**
//	 * Ejecuta llamada a Homolog
//	 * Ejecuta un servicio usando la configuración de este cliente y la url provista. Permite convertir el resultado
//	 * de la respuesta del servicio invocado en un objeto Java.
//	 * @param url Url a consultar. Se agrega luego de la url base configurada en esta instancia del cliente.
//	 * @param document {@link E} Documento enviado por en el RequestBody<br> debe ser nulo en el caso de ser GET
//	 * @param responseClass {@link T} Tipo de respuesta para el {@link ResponseEntity}<br> en caso de peticiones DELETE
//	 * este parametro debe ser {@link Void}
//	 * @param method {@link HttpMethod} Tipo de operación a ejecutar
//	 * @return Objeto resultado de parsear la respuesta del servicio invocado.
//	 */
//	public <T, E> T executeServiceHomolog(String url, E document, Class<T> responseClass, HttpMethod method) {
//		return executeServiceHomolog(url, document, responseClass, method, null, null);
//	}
//	
//	/**
//	 * Ejecuta la llamada a la URL de Homolog 
//	 * Ejecuta un servicio usando la configuración de este cliente y la url provista. Permite convertir el resultado
//	 * de la respuesta del servicio invocado en un objeto Java.
//	 * @param url Url a consultar. Se agrega luego de la url base configurada en esta instancia del cliente.
//	 * @param document {@link E} Documento enviado por en el RequestBody<br> debe ser nulo en el caso de ser GET
//	 * @param responseClass {@link T} Tipo de respuesta para el {@link ResponseEntity}<br> en caso de peticiones DELETE
//	 * este parametro debe ser {@link Void}
//	 * @param method {@link HttpMethod} Tipo de operación a ejecutar
//	 * @param queryParams Parámetros para agregar en el query string de la url
//	 * @param headers Cabeceras a agregar al request.
//	 * @return Objeto resultado de parsear la respuesta del servicio invocado.
//	 */
//	public <T, E> T executeServiceHomolog(String url, E document, Class<T> responseClass, HttpMethod method,
//			Map<String, String> queryParams, Map<String, String> headers) {
//		return executeServiceHomologAsValid(url, document, responseClass, method, queryParams, headers).getBody();
//	}
//	
//	/**
//	 * Ejecuta la llamada a la URL de Homolog 
//	 * Ejecuta un servicio usando la configuración de este cliente y la url provista. Permite convertir el resultado
//	 * de la respuesta del servicio invocado en un objeto Java. También se obtienen las cabeceras de la respuesta
//	 * @param url Url a consultar. Se agrega luego de la url base configurada en esta instancia del cliente.
//	 * @param document {@link E} Documento enviado por en el RequestBody<br> debe ser nulo en el caso de ser GET
//	 * @param responseClass {@link T} Tipo de respuesta para el {@link ResponseEntity}<br> en caso de peticiones DELETE
//	 * este parametro debe ser {@link Void}
//	 * @param method {@link HttpMethod} Tipo de operación a ejecutar
//	 * @param queryParams Parámetros para agregar en el query string de la url
//	 * @param headers Cabeceras a agregar al request.
//	 * @return Objeto resultado de parsear la respuesta del servicio invocado.
//	 */
//	public <T, E> ResponseEntity<T> executeServiceHomologAsValid(String url, E document, Class<T> responseClass, HttpMethod method,
//			Map<String, String> queryParams, Map<String, String> headers) {
//		StringBuilder sb = new StringBuilder();
//		sb.append(schema).append("://").append(connectionUrlHomolog).append(url);
//		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(sb.toString());
//
//		if (!CollectionUtils.isEmpty(queryParams)) {
//			for(Map.Entry<String, String> entry : queryParams.entrySet()) {
//				builder.queryParam(entry.getKey(), entry.getValue());
//			}
//		}
//
//		return doExecuteServiceHomolog(builder.build(true).encode().toUri(), document, responseClass, method, headers);
//	}

	/**
	 * @return the connectionUrl
	 */
	public String getConnectionUrl() {
		return connectionUrl;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @return the apiKey
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * @return the schema
	 */
	public String getSchema() {
		return schema;
	}

}