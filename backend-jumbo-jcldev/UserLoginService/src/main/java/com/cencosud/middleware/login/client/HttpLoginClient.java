package com.cencosud.middleware.login.client;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cencosud.middleware.login.annotation.Loggable;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpLoginClient {

	private static final Logger logger = LoggerFactory.getLogger(HttpLoginClient.class);

	@Loggable
	public static <T> T executeAsObject(String schema, String connectionUrl, String path, Class<T> classResult,
			MultiValueMap<String, String> parameters, int port, Map<String, String> headerMap) {
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
		builder.scheme(schema).port(port).host(connectionUrl).path(path).queryParams(parameters);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			headers.add(entry.getKey(), entry.getValue());
		}
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<T> result = null;
		try {
			result = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, classResult);
		} catch (final HttpClientErrorException httpClientError) {
			ObjectMapper om = new ObjectMapper();
			try {
				return om.readValue(httpClientError.getResponseBodyAsString(), classResult);
			} catch (JsonParseException e) {
				logger.error("Parse Error", e);
			} catch (JsonMappingException e) {
				logger.error("Json mapping error", e);
			} catch (IOException e) {
				logger.error("Error reading response", e);
			}
		}

		logger.info("result :" + result.getBody());

		return result.getBody();
	}

	@Loggable
	public static <T> T executeAsObject(String schema, String connectionUrl, String path, Class<T> classResult,
			MultiValueMap<String, String> parameters) {
		RestTemplate restTemplate = new RestTemplate();

		UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
		builder.scheme(schema).host(connectionUrl).path(path).queryParams(parameters);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		ResponseEntity<T> result = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity,
				classResult);

		logger.info("result :" + result.getBody());

		return result.getBody();
	}

}