package com.cencosud.middleware.recommendation.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.UnsupportedDataTypeException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.cencosud.middleware.recommendation.annotation.Loggable;
import com.cencosud.middleware.recommendation.enums.RequestProtocolEnum;
import com.cencosud.middleware.recommendation.exception.RecommendationServiceException;
import com.cencosud.middleware.recommendation.model.Pair;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpCustomClient {

	private final HttpClient httpClient = HttpClientBuilder.create().build();

	private final String connectionUrl;
	// private final int port;
	private final String apiKey;
	private final String secretKey;
	private final String schema;
	private boolean sendCredentialsAsHeader;

	private final ObjectMapper mapper = new ObjectMapper();

	private static final Logger logger = LoggerFactory.getLogger(HttpCustomClient.class);

	public HttpCustomClient(String connectionUrl, String apiKey, String secretKey, String schema) {
		this(connectionUrl, apiKey, secretKey, schema, true);
	}

	public HttpCustomClient(String connectionUrl, String apiKey, String secretKey, String schema, boolean sendCredentialsAsHeader) {
		this.connectionUrl = connectionUrl;
		// this.port = port;
		this.apiKey = apiKey;
		this.secretKey = secretKey;
		this.schema = schema;
		this.sendCredentialsAsHeader = sendCredentialsAsHeader;
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
			TypeReference<T> reference) throws RecommendationServiceException {
		try {

			HttpResponse response = this.executeRequest(path, nvps, protocol);
			mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			return mapper.readValue(response.getEntity().getContent(), reference);
		} catch (IOException | URISyntaxException e) {
			logger.error("Error: [" + this.getClass().getCanonicalName() + "]", e);
			throw new RecommendationServiceException("Error al intentar decodificar la informacion", e);
		}
	}

	@Loggable
	public String executeAsString(String path, List<NameValuePair> nvps, RequestProtocolEnum protocol)
			throws RecommendationServiceException {
		try {

			HttpResponse response = this.executeRequest(path, nvps, protocol);
			StringBuilder content = new StringBuilder();
			BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line;
			while ((line = in.readLine()) != null) {
				content.append(line);
			}
			in.close();
			return content.toString();
		} catch (IOException | URISyntaxException e) {
			logger.error("Error: [" + this.getClass().getCanonicalName() + "]", e);
			throw new RecommendationServiceException("Error al intentar decodificar la informacion", e);
		}
	}

	@Loggable
	public Pair<String, Map<String, String>> executeAndGetResultAsStringAndHeaders(String path,
			List<NameValuePair> nvps, RequestProtocolEnum protocol) throws RecommendationServiceException {
		try {

			HttpResponse response = this.executeRequest(path, nvps, protocol);
			StringBuilder content = new StringBuilder(350);
			BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line;
			while ((line = in.readLine()) != null) {
				content.append(line);
			}
			in.close();
			Map<String, String> headers = new HashMap<String, String>();
			for (Header currentH : response.getAllHeaders()) {
				headers.put(currentH.getName(), currentH.getValue());
			}
			return new Pair<String, Map<String, String>>(content.toString(), headers);
		} catch (IOException | URISyntaxException e) {
			logger.error("Error: [" + this.getClass().getCanonicalName() + "]", e);
			throw new RecommendationServiceException("Error al intentar decodificar la informacion", e);
		}
	}

	@Loggable
	private HttpResponse executeRequest(String path, List<NameValuePair> nvps, RequestProtocolEnum protocol)
			throws ClientProtocolException, IOException, URISyntaxException {

		if (nvps == null) {
			nvps = new ArrayList<NameValuePair>(0);
		}
		URIBuilder builder = new URIBuilder();
		builder.setScheme(schema).setHost(connectionUrl)
				// .setPort(port)
				.setPath(path).addParameters(nvps);
		if (!sendCredentialsAsHeader) {
			builder.addParameter("apiKey", apiKey)
				.addParameter("secretKey", secretKey);
		}
		HttpRequestBase request;
		if (protocol == RequestProtocolEnum.GET) {
			request = new HttpGet(builder.build());
		} else if (protocol == RequestProtocolEnum.POST) {
			request = new HttpPost(builder.build());
		} else {
			throw new UnsupportedDataTypeException();
		}

		request.setHeader("Content-Type", "application/json");
		if (sendCredentialsAsHeader) {
			request.setHeader("X-VTEX-API-AppKey", apiKey);
			request.setHeader("X-VTEX-API-AppToken", secretKey);
		}

		logger.debug("REQUEST--------->" + request.toString());

		return httpClient.execute(request);
	}

	public void addNameValuePair(String name, String value, List<NameValuePair> nvps) {
		if (StringUtils.isNotEmpty(value)) {
			nvps.add(new BasicNameValuePair(name, value));
		}
	}

	public void addNameValuePair(String name, List<String> valueList, List<NameValuePair> nvps) {
		if (!CollectionUtils.isEmpty(valueList)) {
			for (String value : valueList) {
				if (StringUtils.isNotEmpty(value)) {
					nvps.add(new BasicNameValuePair(name, value));
				}
			}
		}
	}
}
