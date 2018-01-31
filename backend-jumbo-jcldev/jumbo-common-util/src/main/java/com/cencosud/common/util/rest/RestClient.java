package com.cencosud.common.util.rest;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestOperations;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cencosud.common.util.annotation.Loggable;

/**
 * 
 * 
 * <h1>RestClient</h1>
 * <p>
 * Cliente para utilizar los servicios rest HTTP
 * </p>
 * 
 * @author fernando.castro
 * @version 1.0
 * @since Jun 26, 2017
 */

@Loggable
public class RestClient {

	private final String connectionUrl;
	private final Integer port;
	private final String schema;
	private final Map<String, String> principalHeaders;
	private final AsyncRestOperations asyncRestTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);

	/**
	 * 
	 * @param connectionUrl
	 *            {@link String}
	 * @param port
	 *            {@link Integer}
	 * @param schema
	 *            {@link String}
	 * @param principalHeaders
	 *            {@link Map}<{@link String}, {@link String}>
	 */
	public RestClient(String connectionUrl, Integer port, String schema, Map<String, String> principalHeaders) {
		super();
		this.connectionUrl = connectionUrl;
		this.port = port;
		this.schema = schema;
		this.principalHeaders = Collections.unmodifiableMap(principalHeaders);
		this.asyncRestTemplate = null;
	}

	/**
	 * 
	 * @param connectionUrl
	 * @param port
	 * @param schema
	 * @param principalHeaders
	 * @param asyncRestTemplate
	 */
	public RestClient(String connectionUrl, Integer port, String schema, Map<String, String> principalHeaders,
			AsyncRestOperations asyncRestTemplate) {
		super();
		this.connectionUrl = connectionUrl;
		this.port = port;
		this.schema = schema;
		this.principalHeaders = Collections.unmodifiableMap(principalHeaders);
		this.asyncRestTemplate = asyncRestTemplate;
	}

	/**
	 * 
	 * @param url
	 *            {@link URI} URL del servicio Vtex
	 * @param document
	 *            {@link E} Documento enviado por en el RequestBody<br>
	 *            debe ser nulo en el caso de ser GET
	 * @param responseClass
	 *            {@link T} Tipo de respuesta para el {@link ResponseEntity}<br>
	 *            en caso de peticiones DELETE este parametro debe ser {@link Void}
	 * @param method
	 *            {@link HttpMethod} Tipo de operación a ejecutar
	 * @return {@link RestClientResponse}
	 * @throws RestClientException
	 */
	@SuppressWarnings("unchecked")
	private <T, E> RestClientResponse<T> doExecuteService(URI url, E document, Class<T> responseClass,
			HttpMethod method, Map<String, String> headers) {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

		RestTemplate restTemplate = new RestTemplate(requestFactory);
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		ResponseEntity<T> result = null;
		LOGGER.info("Inicia ejecucion servicio...");

		HttpHeaders httpHeaders = new HttpHeaders();
		if (!CollectionUtils.isEmpty(principalHeaders)) {
			for (Map.Entry<String, String> entry : principalHeaders.entrySet()) {
				httpHeaders.add(entry.getKey(), entry.getValue());
			}
		}

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
			responseClass = (Class<T>) Void.class;
		}

		try {
			LOGGER.info("Ejecunatndo servicio a la URL: {}", url);
			result = restTemplate.exchange(url, method, entity, responseClass);
			RestClientResponse<T> restResponse = new RestClientResponse<>(result.getStatusCodeValue(),
					result.getHeaders(), "");
			restResponse = restResponse.setResponse(result.getBody());
			return restResponse;
		} catch (HttpClientErrorException ex) {
			LOGGER.error("Error al ejecutar el servicio: ", ex);

			RestClientResponse<T> restResponse = new RestClientResponse<>(ex.getRawStatusCode(),
					ex.getResponseHeaders(), ex.getResponseBodyAsString());
			return restResponse;
		}
	}

	/**
	 * 
	 * @param url
	 *            {@link URI} URL del servicio Vtex
	 * @param document
	 *            {@link E} Documento enviado por en el RequestBody<br>
	 *            debe ser nulo en el caso de ser GET
	 * @param responseClass
	 *            {@link T} Tipo de respuesta para el {@link ResponseEntity}<br>
	 *            en caso de peticiones DELETE este parametro debe ser {@link Void}
	 * @param method
	 *            {@link HttpMethod} Tipo de operación a ejecutar
	 * @return {@link RestClientResponse}
	 * @throws RestClientException
	 */
	@SuppressWarnings("unchecked")
	private <T, E> List<RestClientResponse<T>> doExecuteService(List<RestAsyncRequest<E>> urlList, Class<T> responseClass,
			HttpMethod method, Map<String, String> headers) {

		List<RestClientResponse<T>> listRestClienResponse;
		LOGGER.info("Inicia ejecucion servicio...");

		HttpHeaders httpHeaders = new HttpHeaders();
		if (!CollectionUtils.isEmpty(principalHeaders)) {
			for (Map.Entry<String, String> entry : principalHeaders.entrySet()) {
				httpHeaders.add(entry.getKey(), entry.getValue());
			}
		}

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		if (!CollectionUtils.isEmpty(headers)) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				httpHeaders.add(entry.getKey(), entry.getValue());
			}
		}
		
		if (method == HttpMethod.DELETE) {
			responseClass = (Class<T>) Void.class;
		}

		final BlockingQueue<T> fullListOfRecords = new LinkedBlockingQueue<>();
		List<Future<ResponseEntity<T>>> futures = asyncFutureExecution(urlList, responseClass, method, httpHeaders, fullListOfRecords);

		listRestClienResponse = asyncRecoveryResponse(futures);
		return listRestClienResponse;
	}
	
	/**
	 * Metodo encargado de recuperar las respuestas asyncronas enviadas.
	 * @param futures
	 * @return
	 */
	private <T> List<RestClientResponse<T>> asyncRecoveryResponse(List<Future<ResponseEntity<T>>> futures) {
		List<RestClientResponse<T>> listRestClienResponse;
		listRestClienResponse = new ArrayList<>(futures.size());
		for (Future<ResponseEntity<T>> future : futures) {
			try {
				ResponseEntity<T> result = future.get();
				RestClientResponse<T> restResponse = new RestClientResponse<>(result.getStatusCodeValue(),
						result.getHeaders(), "");
				restResponse = restResponse.setResponse(result.getBody());
				listRestClienResponse.add(restResponse);
			} catch (InterruptedException | ExecutionException e) {
				LOGGER.debug("Problems to execute the service: {}", e);
				listRestClienResponse = new ArrayList<>();
				RestClientResponse<T> restResponse = new
				RestClientResponse<>(500, null,
				"");
				listRestClienResponse.add(restResponse);
			}
		}
		return listRestClienResponse;
	}

	/**
	 * Envia las respuestas asyncronas en difrentes hilos
	 * @param urlList
	 * @param responseClass
	 * @param method
	 * @param httpHeaders
	 * @param fullListOfRecords
	 * @return
	 */
	private <T, E> List<Future<ResponseEntity<T>>> asyncFutureExecution(List<RestAsyncRequest<E>> urlList, Class<T> responseClass,
			HttpMethod method, HttpHeaders httpHeaders, final BlockingQueue<T> fullListOfRecords) {
		List<Future<ResponseEntity<T>>> futures = new ArrayList<>(urlList.size());
		for (RestAsyncRequest<E> url : urlList) {
			HttpEntity<?> entity;
			if (method == HttpMethod.GET || method == HttpMethod.DELETE) {
				entity = new HttpEntity<>(httpHeaders);
			} else {
				entity = new HttpEntity<>(url.getDocument(), httpHeaders);
			}

			LOGGER.info("Ejecunatndo servicio a la URL: {}", url);
			ListenableFuture<ResponseEntity<T>> future = asyncRestTemplate.exchange(url.getUrl(), method, entity, responseClass);
			future.addCallback(new ListenableFutureCallback<ResponseEntity<T>>() {

				@Override
				public void onFailure(Throwable t) {
					LOGGER.warn("Error: {}", t);
				}

				@Override
				public void onSuccess(ResponseEntity<T> result) {
					fullListOfRecords.add(result.getBody());
					LOGGER.info("Success: {}", result.getBody());
				}
			});
			futures.add(future);
		}
		return futures;
	}

	/**
	 * Ejecuta un servicio usando la configuración de este cliente y la url
	 * provista. Permite convertir el resultado de la respuesta del servicio
	 * invocado en un objeto Java.
	 * 
	 * @param url
	 *            Url a consultar. Se agrega luego de la url base configurada en
	 *            esta instancia del cliente.
	 * @param document
	 *            {@link E} Documento enviado por en el RequestBody<br>
	 *            debe ser nulo en el caso de ser GET
	 * @param responseClass
	 *            {@link RestClientResponse} Tipo de respuesta para el
	 *            {@link ResponseEntity}<br>
	 *            en caso de peticiones DELETE este parametro debe ser {@link Void}
	 * @param method
	 *            {@link HttpMethod} Tipo de operación a ejecutar
	 * @return Objeto resultado de parsear la respuesta del servicio invocado.
	 */
	public <T, E> RestClientResponse<T> executeService(String url, E document, Class<T> responseClass,
			HttpMethod method) {
		return executeService(url, document, responseClass, method, null, null);
	}

	/**
	 * Ejecuta un servicio usando la configuración de este cliente y la url
	 * provista. Permite convertir el resultado de la respuesta del servicio
	 * invocado en un objeto Java.
	 * 
	 * @param url
	 *            Url a consultar. Se agrega luego de la url base configurada en
	 *            esta instancia del cliente.
	 * @param document
	 *            {@link E} Documento enviado por en el RequestBody<br>
	 *            debe ser nulo en el caso de ser GET
	 * @param responseClass
	 *            {@link RestClientResponse} Tipo de respuesta para el
	 *            {@link ResponseEntity}<br>
	 *            en caso de peticiones DELETE este parametro debe ser {@link Void}
	 * @param method
	 *            {@link HttpMethod} Tipo de operación a ejecutar
	 * @param queryParams
	 *            Parámetros para agregar en el query string de la url
	 * @return Objeto resultado de parsear la respuesta del servicio invocado.
	 */
	public <T, E> RestClientResponse<T> executeService(String url, E document, Class<T> responseClass,
			HttpMethod method, Map<String, String> queryParams) {
		return executeService(url, document, responseClass, method, queryParams, null);
	}

	/**
	 * Ejecuta un servicio usando la configuración de este cliente y la url
	 * provista. Permite convertir el resultado de la respuesta del servicio
	 * invocado en un objeto Java.
	 * 
	 * @param url
	 *            Url a consultar. Se agrega luego de la url base configurada en
	 *            esta instancia del cliente.
	 * @param document
	 *            {@link E} Documento enviado por en el RequestBody<br>
	 *            debe ser nulo en el caso de ser GET
	 * @param responseClass
	 *            {@link TVtexServiceResponse} Tipo de respuesta para el
	 *            {@link ResponseEntity}<br>
	 *            en caso de peticiones DELETE este parametro debe ser {@link Void}
	 * @param method
	 *            {@link HttpMethod} Tipo de operación a ejecutar
	 * @param queryParams
	 *            Parámetros para agregar en el query string de la url
	 * @param headers
	 *            Cabeceras a agregar al request.
	 * @return Objeto resultado de parsear la respuesta del servicio invocado.
	 */
	public <T, E> RestClientResponse<T> executeService(String url, E document, Class<T> responseClass,
			HttpMethod method, Map<String, String> queryParams, Map<String, String> headers) {
		RestClientResponse<T> response = executeServiceAsValid(url, document, responseClass, method, queryParams,
				headers);

		return response;
	}

	/**
	 * Ejecuta un servicio usando la configuración de este cliente y la url
	 * provista. Permite convertir el resultado de la respuesta del servicio
	 * invocado en un objeto Java. También se obtienen las cabeceras de la respuesta
	 * 
	 * @param url
	 *            Url a consultar. Se agrega luego de la url base configurada en
	 *            esta instancia del cliente.
	 * @param document
	 *            {@link E} Documento enviado por en el RequestBody<br>
	 *            debe ser nulo en el caso de ser GET
	 * @param responseClass
	 *            {@link T} Tipo de respuesta para el {@link ResponseEntity}<br>
	 *            en caso de peticiones DELETE este parametro debe ser {@link Void}
	 * @param method
	 *            {@link HttpMethod} Tipo de operación a ejecutar
	 * @param queryParams
	 *            Parámetros para agregar en el query string de la url
	 * @param headers
	 *            Cabeceras a agregar al request.
	 * @return {@link RestClientResponse} Objeto resultado de parsear la respuesta
	 *         del servicio invocado.
	 */
	public <T, E> RestClientResponse<T> executeServiceAsValid(String url, E document, Class<T> responseClass,
			HttpMethod method, Map<String, String> queryParams, Map<String, String> headers) {
		StringBuilder sb = new StringBuilder();
		sb.append(schema).append("://").append(connectionUrl).append(url);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(sb.toString());

		if (!CollectionUtils.isEmpty(queryParams)) {
			for (Map.Entry<String, String> entry : queryParams.entrySet()) {
				builder.queryParam(entry.getKey(), entry.getValue());
			}
		}

		return doExecuteService(builder.build(true).encode().toUri(), document, responseClass, method, headers);
	}
	
	/**
	 * Ejecuta una lista de servicios usando la configuración de este cliente y las url
	 * provista. Permite convertir el resultado de la respuesta del servicio
	 * invocado en un objeto Java.
	 * 
	 * @param urls
	 * @param responseClass
	 * @param method
	 * @param headers
	 * @return
	 */
	public <T, E> List<RestClientResponse<T>> executeAsyncServices(List<RestAsyncValues<E>> urls, Class<T> responseClass,
			HttpMethod method, Map<String, String> headers) {
		List<RestAsyncRequest<E>> urlList = new ArrayList<>(urls.size());
		for(RestAsyncValues<E> tempRestValue:urls) {
			StringBuilder sb = new StringBuilder();
			sb.append(schema).append("://").append(connectionUrl).append(tempRestValue.getUrl());
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(sb.toString());
	
			if (!CollectionUtils.isEmpty(tempRestValue.getQueryParams())) {
				for (Map.Entry<String, String> entry : tempRestValue.getQueryParams().entrySet()) {
					builder.queryParam(entry.getKey(), entry.getValue());
				}
			}
			urlList.add(new RestAsyncRequest<E>(builder.build(true).encode().toUri(), tempRestValue.getDocument()));
		}
		return doExecuteService(urlList, responseClass, method, headers);
	}

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
	 * @return the schema
	 */
	public String getSchema() {
		return schema;
	}

	/**
	 * @return the principalHeaders
	 */
	public Map<String, String> getPrincipalHeaders() {
		return principalHeaders;
	}
	
	private class RestAsyncRequest<E>{
		private URI url; 
		private E document;
		
		
		/**
		 * @param url
		 * @param document
		 */
		public RestAsyncRequest(URI url, E document) {
			super();
			this.url = url;
			this.document = document;
		}

		/**
		 * @return the url
		 */
		public URI getUrl() {
			return url;
		}

		/**
		 * @return the document
		 */
		public E getDocument() {
			return document;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("RestAsyncRequest [url=");
			builder.append(url);
			builder.append(", document=");
			builder.append(document);
			builder.append("]");
			return builder.toString();
		}
	}

}