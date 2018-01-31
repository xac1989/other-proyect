package com.cencosud.middleware.coupon.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.cencosud.middleware.coupon.annotation.Loggable;
import com.cencosud.middleware.coupon.exception.CouponServiceException;
import com.cencosud.middleware.coupon.model.enums.RequestProtocolEnum;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CencoCouponClient {

	private final HttpClient httpClient = HttpClientBuilder.create().build();

	private String schema;
	private String tokenSchema;
	private String url;    
	private int port;
	private int tokenPort;
	private String grantType;
	private String clientId;
	private String clientSecret;

	
	private final ObjectMapper mapper = new ObjectMapper();
	
	private static final Logger logger = LoggerFactory.getLogger(CencoCouponClient.class); 



	public CencoCouponClient(String schema, String tokenSchema, String url, int port, int tokenPort, String grantType, String clientId,
			String clientSecret) {
		super();
		this.schema = schema;
		this.tokenSchema = tokenSchema;
		this.url = url;
		this.port = port;
		this.tokenPort = tokenPort;
		this.grantType = grantType;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}


	public String getSchema() {
		return schema;
	}
	public String getUrl() {
		return url;
	}
	public int getPort() {
		return port;
	}
	public int getTokenPort() {
		return tokenPort;
	}
	public String getGrantType() {
		return grantType;
	}
	public String getClientId() {
		return clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}


	public String getTokenSchema() {
		return tokenSchema;
	}


	@Loggable
	public <T> T execute(String path, List<NameValuePair> nvps, RequestProtocolEnum protocol,
			TypeReference<T> reference, Map<String,String> headers) throws CouponServiceException {
		try {

			HttpResponse response = this.executeRequest(getSchema(), path, nvps, protocol, getPort(), headers);
			mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			if(response.getStatusLine().getStatusCode()>=200 && response.getStatusLine().getStatusCode()<300){
				return mapper.readValue(response.getEntity().getContent(), reference);
			}
			throw new CouponServiceException("Invalid server response", null);
			
		} catch (IOException | URISyntaxException e) {
			logger.error("Error: [" + this.getClass().getCanonicalName() + "]", e);
			throw new CouponServiceException("Error al intentar decodificar la informacion", e);
		}
	}

	@Loggable
	public <T> T executeOauth(String path, List<NameValuePair> nvps, RequestProtocolEnum protocol,
			TypeReference<T> reference) throws CouponServiceException {
		try {

			HttpResponse response = this.executeRequest(getTokenSchema(),path, nvps, protocol, getTokenPort(), null); 
			mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			return mapper.readValue(response.getEntity().getContent(), reference);
		} catch (IOException | URISyntaxException e) {
			logger.error("Error: [" + this.getClass().getCanonicalName() + "]", e);
			throw new CouponServiceException("Error al intentar decodificar la informacion", e);
		}
	}

	
	@Loggable
	public String executeAsString(String schema, String path, List<NameValuePair> nvps, RequestProtocolEnum protocol, int port, Map<String, String> headers) throws CouponServiceException {
		try {

			HttpResponse response = this.executeRequest(schema, path, nvps, protocol, port, headers);
			String content = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line;
			while ((line = in.readLine()) != null) {
				content += line + " ";
			}
			in.close();
			return content;
		} catch (IOException | URISyntaxException e) {
			logger.error("Error: [" + this.getClass().getCanonicalName() + "]", e);
			throw new CouponServiceException("Error al intentar decodificar la informacion", e);
		}
	}
	

	@Loggable
	private HttpResponse executeRequest(String schema, String path, List<NameValuePair> nvps, RequestProtocolEnum protocol, int port, Map<String,String> headers)
			throws ClientProtocolException, IOException, URISyntaxException {

		if (nvps == null) {
			nvps = new ArrayList<NameValuePair>(0);
		}
		URIBuilder builder = new URIBuilder();
		builder
			.setScheme(schema)
			.setHost(getUrl())
			.setPort(port)
			.setPath(path).addParameters(nvps);
		HttpRequestBase request;
		if (protocol == RequestProtocolEnum.GET) {
			request = new HttpGet(builder.build());
		} else if (protocol == RequestProtocolEnum.POST) {
			request = new HttpPost(builder.build());
		} else {
			throw new UnsupportedDataTypeException();
		}

		request.setHeader("Content-Type", "application/x-www-form-urlencoded");
		if(headers != null){
			for(String key: headers.keySet()){
				request.setHeader(key, headers.get(key));
			}
		}

		if(logger.isDebugEnabled()){
			logger.info("REQUEST--------->"+request.toString());
		}
		
		return httpClient.execute(request);
	}
}