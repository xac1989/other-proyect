package com.scanandgo.middleware.batch.product.repository.impl;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cencosud.middleware.product.model.Product;
import com.scanandgo.middleware.batch.product.client.VtexClient;
import com.scanandgo.middleware.batch.product.exception.ProductServiceException;
import com.scanandgo.middleware.batch.product.repository.VtexRepository;

@Repository
public class VtexRepositoryImpl implements VtexRepository{

	Logger logger = LoggerFactory.getLogger(VtexRepositoryImpl.class);
	
	@Autowired
	VtexClient client;
	
	private final String SEARCH_PRODUCTS_URL = "/api/catalog_system/pvt/sku/stockkeepingunitbyean/";


	@Override
	public Product searchProduct(String ean) throws ProductServiceException {
		
		try {
			String strJdonResponse = client.executeAsString(SEARCH_PRODUCTS_URL, ean);
			Product product = new Product();
			JSONObject jsonObject = new JSONObject(strJdonResponse);
			product.setEan(ean);
			product.setProductId(jsonObject.getInt("ProductId"));
			product.setProductName(jsonObject.getString("ProductName"));
			product.setNameComplete(jsonObject.getString("NameComplete"));
			product.setProductDescription(jsonObject.getString("ProductDescription"));
			product.setSkuName(jsonObject.getString("SkuName"));
			product.setImageUrl(jsonObject.getString("ImageUrl"));
			product.setDetailUrl(jsonObject.getString("DetailUrl"));
			product.setBrandId(jsonObject.getString("BrandId"));
			product.setBrandName(jsonObject.getString("BrandName"));
			System.out.println("********************------------------>"+product.toString());
			return product;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductServiceException("Error al intentar decodificar la informacion", e);
		}
	}


}
