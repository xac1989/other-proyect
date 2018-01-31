package com.cencosud.middleware.catalog.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.UnsupportedDataTypeException;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cencosud.middleware.catalog.annotation.Loggable;
import com.cencosud.middleware.catalog.exception.CatalogServiceException;
import com.cencosud.middleware.catalog.model.Pair;
import com.cencosud.middleware.catalog.model.enums.RequestProtocolEnum;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VtexClient {

	private final HttpClient httpClient = HttpClientBuilder.create().build();

	private final String connectionUrl;
	private final int port;
	private final String apiKey;
	private final String secretKey;
	private final String schema;

	private final ObjectMapper mapper = new ObjectMapper();

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

	@Loggable
	public <T> T execute(String path, List<NameValuePair> nvps, RequestProtocolEnum protocol,
			TypeReference<T> reference) throws CatalogServiceException {
		try {
			HttpResponse response = this.executeRequest(path, nvps, protocol);
			mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			return mapper.readValue(response.getEntity().getContent(), reference);
		} catch (IOException | URISyntaxException e) {
			logger.error("Error: [" + this.getClass().getCanonicalName() + "]", e);
			throw new CatalogServiceException("Error al intentar decodificar la informacion", e);
		}
	}

	@Loggable
	public String executeAsString(String path, List<NameValuePair> nvps, RequestProtocolEnum protocol)
			throws CatalogServiceException {
		try {
			HttpResponse response = this.executeRequest(path, nvps, protocol);
			StringBuilder content = new StringBuilder();
			BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				content.append(line);
			}
			in.close();
			logger.debug("Response from service: {}", content);
			return content.toString();
		} catch (IOException | URISyntaxException e) {
			logger.error("Error: [" + this.getClass().getCanonicalName() + "]", e);
			throw new CatalogServiceException("Error al intentar decodificar la informacion", e);
		}
	}
	
	@Loggable
	public Pair<String, Map<String, String>> executeAndGetResultAsStringAndHeaders(String path,
			List<NameValuePair> nvps, RequestProtocolEnum protocol) throws CatalogServiceException {
		try {
			HttpResponse response = this.executeRequest(path, nvps, protocol);
			StringBuilder content = new StringBuilder();
			BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line;
			while ((line = in.readLine()) != null) {
				content.append(line);
			}
			in.close();
			logger.debug("Response from service: {}", content);
			Map<String, String> headers = new HashMap<String, String>();
			for (Header currentH : response.getAllHeaders()) {
				headers.put(currentH.getName(), currentH.getValue());
			}

			return new Pair<String, Map<String, String>>(content.toString(), headers);
		} catch (IOException | URISyntaxException e) {
			logger.error("Error: [" + this.getClass().getCanonicalName() + "]", e);
			throw new CatalogServiceException("Error al intentar decodificar la informacion", e);
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

		logger.debug("REQUEST---------> {}", request);

		return httpClient.execute(request);
	}

}