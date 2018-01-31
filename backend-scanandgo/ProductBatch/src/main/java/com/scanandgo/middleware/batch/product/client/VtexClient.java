package com.scanandgo.middleware.batch.product.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class VtexClient {

	private final static String X_VTEX_API_APPKEY = "x-vtex-api-appkey";
	private final static String X_VTEX_API_APPTOKEN = "x-vtex-api-apptoken";

	private final String connectionUrl;
	private final int port;
	private final String apiKey;
	private final String secretKey;
	private final String schema;

	private static final Logger logger = LoggerFactory.getLogger(VtexClient.class);

	public VtexClient(String connectionUrl, int port, String apiKey, String secretKey, String schema) {
		super();
		this.connectionUrl = connectionUrl;
		this.port = port;
		this.apiKey = apiKey;
		this.secretKey = secretKey;
		this.schema = schema;
	}

	public String getConnectionUrl() {
		return connectionUrl;
	}

	public String getApiKey() {
		return apiKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public String getSchema() {
		return schema;
	}

	public String executeAsString(String path, String ean) {
		RestTemplate restTemplate = new RestTemplate();
		
		UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
		builder.scheme(schema).host(connectionUrl).port(port).path(path+ean);

		HttpHeaders headers = new HttpHeaders();
		headers.add(X_VTEX_API_APPKEY, this.apiKey);
		headers.add(X_VTEX_API_APPTOKEN, this.secretKey);
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		ResponseEntity<String> result = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity,
				String.class);

		System.out.println("Id:" + result.getBody());
		logger.info("Id:" + result.getBody());
		
		return result.getBody();
	}

}