package com.cencosud.mobile.service;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.cencosud.mobile.recommendations.adapter.NotFoundException;
import com.cencosud.mobile.recommendations.adapter.RecommendationsApiService;
import com.ibm.json.java.JSONObject;

import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;

public class RecomendationsApiServiceImpl implements RecommendationsApiService{
	
	private static final String RECOMENDATIONS_PATH = "recommendations/r1/v1/recommendations/products";
	private static final String RECOMENDATIONS_RELEVANT_PATH = "recommendations/r1/v1/recommendation/relevant";
	//private static final String AUTH_HEADER = "Authorization";
	private static final String CLIENT_ID_HEADER = "X-IBM-Client-Id";
	private static final String CLIENT_SECRET_HEADER = "X-IBM-Client-Secret";
	
	private String apiBaseUrl;
	private String apiClientId;
	private String apiSecret;
	
	public String getApiBaseUrl() {
		return apiBaseUrl;
	}

	public void setApiBaseUrl(String apiBaseUrl) {
		this.apiBaseUrl = apiBaseUrl;
	}

	public String getApiClientId() {
		return apiClientId;
	}

	public void setApiClientId(String apiClientId) {
		this.apiClientId = apiClientId;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}


	public Response recommendationsGet(String type, List<String> productId, List<String> categoryId, List<String> tagId,
			String deviceId, String userId, String useBoughtProducts, String useCartProducts, String useVisitedProducts,
			Integer size, String email, AdapterSecurityContext securityContext) throws NotFoundException {
		Client searchClient = ClientBuilder.newClient();

		WebTarget fullTextTarget = searchClient.target(this.getApiBaseUrl()).path(RECOMENDATIONS_PATH)
				.queryParam("type", type)
//				.queryParam("productId", productId)
//				.queryParam("categoryId", categoryId)
//				.queryParam("tagId", tagId)
				.queryParam("deviceId", deviceId)
				.queryParam("userId", userId)
				.queryParam("useBoughtProducts", useBoughtProducts)
				.queryParam("useCartProducts", useCartProducts)
				.queryParam("useVisitedProducts", useVisitedProducts)
				.queryParam("size", size)
				.queryParam("email", email);
		
		for(String p: productId){
			fullTextTarget = fullTextTarget.queryParam("productId", p);
		}
		
		for(String p: categoryId){
			fullTextTarget = fullTextTarget.queryParam("categoryId", p);
		}
		
		System.out.println("---------------->"+fullTextTarget.getUri());
		// get the authorization header from mobile first and use it with api connect
		// Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON).header(AUTH_HEADER, headers.getHeaderString(AUTH_HEADER));
		Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON);
		
		// add api key and api secret for api connect
		invocationBuilder.header(CLIENT_ID_HEADER, this.getApiClientId());
		invocationBuilder.header(CLIENT_SECRET_HEADER, this.getApiSecret());
		
		Response response = invocationBuilder.get();
		String products = response.readEntity(String.class);
		
		return Response.status(response.getStatus()).entity(products).build();
		
//		JSONObject json = new JSONObject();
//	    
//        try {
//			json = JSONObject.parse("{\"recommendations\":[{\"productName\":\"Black & Decker Freidora de Aire PuriFry Blanco\",\"productId\":\"2000592\",\"image\":\"http://wong.vteximg.com.br/arquivos/ids/176884-1000-1000/Black-and-Decker-Freidora-de-Aire-PuriFry-Blanco-wong-544632.jpg\",\"price\":1799,\"listPrice\":2399,\"discountRate\":25,\"available\":true},{\"productName\":\"Samsung Lavaseca 9Kg/5Kg  WD90J6410AW/PE Blanco\",\"productId\":\"2000017\",\"image\":\"http://wong.vteximg.com.br/arquivos/ids/171658-1000-1000/Samsung-Lavaseca-9Kg-5Kg-WD90J6410AW-PE-Blanco-wong-506729.jpg\",\"price\":1799,\"listPrice\":2399,\"discountRate\":25,\"available\":true}],\"metadata\":{\"requestTime\":\"Thu, 22 Dec 2016 20:18:03 Z\",\"region\":\"r1\",\"version\":\"v1\"}}");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        return Response.ok(json.toString()).build();
	}
	
	public Response recommendationsRelevantProductGet(String email, AdapterSecurityContext securityContext) throws NotFoundException {
		Client searchClient = ClientBuilder.newClient();

		WebTarget fullTextTarget = searchClient.target(this.getApiBaseUrl()).path(RECOMENDATIONS_RELEVANT_PATH)
				.queryParam("email", email);
		
		System.out.println("---------------->"+fullTextTarget.getUri());
		// get the authorization header from mobile first and use it with api connect
		// Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON).header(AUTH_HEADER, headers.getHeaderString(AUTH_HEADER));
		Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON);
		
		// add api key and api secret for api connect
		invocationBuilder.header(CLIENT_ID_HEADER, this.getApiClientId());
		invocationBuilder.header(CLIENT_SECRET_HEADER, this.getApiSecret());
		
		Response response = invocationBuilder.get();
		String products = response.readEntity(String.class);
		
		return Response.status(response.getStatus()).entity(products).build();
		
//		JSONObject json = new JSONObject();
//	    
//        try {
//        	json = JSONObject.parse("{\"relevantProduct\":{\"productName\":\"Philips Multiprocesador HR7632 Negro\",\"productId\":\"524206\",\"image\":\"http://tienda.wong.com.pe/arquivos/ids/175611-1000-1000/Philips-Multiprocesador-HR7632-Negro-542937.jpg\",\"price\":319.2,\"listPrice\":399,\"discountRate\":20,\"available\":true},\"metadata\":{\"requestTime\":\"Thu, 22 Dec 2016 20:18:03 Z\",\"region\":\"r1\",\"version\":\"v1\",\"results\":\"1\"}}");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//        return Response.ok(json.toString()).build();

	}

}
