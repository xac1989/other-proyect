package com.cencosud.middleware.order.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.UnsupportedDataTypeException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cencosud.middleware.order.annotation.Loggable;
import com.cencosud.middleware.order.exception.OrderServiceException;
import com.cencosud.middleware.order.model.enums.RequestProtocolEnum;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VtexClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(VtexClient.class);
	
	private final HttpClient httpClient = HttpClientBuilder.create().build();
	private final ObjectMapper mapper = new ObjectMapper();
	private final String connectionUrl;
	private final int port;
	private final String apiKey;
	private final String secretKey;
	private final String schema;

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

	@Loggable
	public <T> T execute(String path, List<NameValuePair> nvps, RequestProtocolEnum protocol,
			TypeReference<T> reference) throws OrderServiceException {
		try {

			HttpResponse response = this.executeRequest(path, nvps, protocol);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(response.getEntity().getContent(), reference);
		} catch (IOException | URISyntaxException e) {
			LOGGER.error("Error: [" + this.getClass().getCanonicalName() + "]" , e);
			throw new OrderServiceException("Error al intentar decodificar la informacion", e);
		}
	}
	
	@Loggable
	private HttpResponse executeRequest(String path, List<NameValuePair> nvps, RequestProtocolEnum protocol)
			throws ClientProtocolException, IOException, URISyntaxException {

		if (nvps == null) {
			nvps = new ArrayList<NameValuePair>(0);
		}
		URIBuilder builder = new URIBuilder();
		builder.setScheme(schema).setHost(connectionUrl).setPort(port).setPath(path).addParameters(nvps);
		HttpRequestBase request;
		if (protocol == RequestProtocolEnum.GET) {
			request = new HttpGet(builder.build());
		} else if (protocol == RequestProtocolEnum.POST) {
			request = new HttpPost(builder.build());
		} else {
			throw new UnsupportedDataTypeException();
		}

		request.setHeader("Content-Type", "application/json");
		request.setHeader("X-VTEX-API-AppKey", apiKey);
		request.setHeader("X-VTEX-API-AppToken", secretKey);

		return httpClient.execute(request);
	}
}