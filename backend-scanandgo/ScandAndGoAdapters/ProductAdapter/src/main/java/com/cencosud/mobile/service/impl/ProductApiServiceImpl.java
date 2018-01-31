package com.cencosud.mobile.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.product.model.Product;
import com.cencosud.middleware.product.model.Store;
import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.product.model.ProductResponse;
import com.cencosud.mobile.service.ProductApiService;

@Service
public class ProductApiServiceImpl implements ProductApiService{

	private static final String PRODUCT_PATH = "/product";
	private static final String CLIENT_ID_HEADER = "X-IBM-Client-Id";
	private static final String CLIENT_SECRET_HEADER = "X-IBM-Client-Secret";
	private static final String LEFTH_ZERO = "00000";
	
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


	public ProductResponse  productGet(String ean, String storeId) throws NotFoundException {
		Product product = getProduct(ean, storeId);
		ProductResponse productResponse = null;
		
		if(product != null){
			productResponse = new ProductResponse();
			BeanUtils.copyProperties(product, productResponse);
			if(product.getStores() != null && !product.getStores().isEmpty()){
				Store tmpStore = product.getStores().get(0);
				productResponse.setStoreId(tmpStore.getStoreId());
				productResponse.setPrice(tmpStore.getPrice());
				productResponse.setPum(tmpStore.getPum());
				productResponse.setUm(tmpStore.getUm());
				productResponse.setEan(ean);
			}
		}else{
			product = getProduct(LEFTH_ZERO.concat(ean.substring(0,7)), storeId);
			if(product != null){
				productResponse = new ProductResponse();
				BeanUtils.copyProperties(product, productResponse);
				String amount = ean.substring(7,12);
				System.out.println(amount);
				BigDecimal amountPrice = new BigDecimal(amount);
				productResponse.setEan(ean);
				if(product.getStores() != null && !product.getStores().isEmpty()){
					Store tmpStore = product.getStores().get(0);
					productResponse.setStoreId(tmpStore.getStoreId());
					productResponse.setPrice(tmpStore.getPrice());
					productResponse.setPum(tmpStore.getPum());
					productResponse.setUm(tmpStore.getUm());
					productResponse.setCantidad(amountPrice.divide(tmpStore.getPum(), 3, RoundingMode.HALF_UP));
					productResponse.setPrice(amountPrice);
				}
			}
		}

		return productResponse;
	}
	
	private Product getProduct(String ean, String storeId){
		Client searchClient = ClientBuilder.newClient();

		WebTarget fullTextTarget = searchClient	
				.target(apiBaseUrl)
				.path(PRODUCT_PATH)
				.queryParam("ean", ean)
				.queryParam("storeId", storeId);

		System.out.println("---------------->"+fullTextTarget.getUri());
		Invocation.Builder invocationBuilder = fullTextTarget.request(MediaType.APPLICATION_JSON);
		
		// add api key and api secret for api connect
		invocationBuilder.header(CLIENT_ID_HEADER, this.getApiClientId());
		invocationBuilder.header(CLIENT_SECRET_HEADER, this.getApiSecret());
		
		Response response = invocationBuilder.get();
		Product product = null;
		try{
			product = response.readEntity(Product.class);
		}catch(Exception e){
			return product;
		}
		return product;
	}
}
