package com.cencosud.middleware.category.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cencosud.middleware.category.client.VtexCategory;
import com.cencosud.middleware.category.client.VtexClient;
import com.cencosud.middleware.category.client.VtexFilters;
import com.cencosud.middleware.category.exception.CategoryServiceException;
import com.cencosud.middleware.category.model.enums.RequestProtocolEnum;
import com.cencosud.middleware.category.repository.VtexRepository;
import com.fasterxml.jackson.core.type.TypeReference;

@Repository
public class VtexRepositoryImpl implements VtexRepository{

	Logger logger = LoggerFactory.getLogger(VtexRepositoryImpl.class);
	
	@Autowired
	VtexClient client;
	
	private final String ALL_CATEGORIES_URL = "/api/catalog_system/pvt/category/tree/20";
	private final String FIND_CATEGORY_URL = "/api/catalog_system/pvt/category/";
	private final String GET_CATEGORY_FILTERS = "/api/catalog_system/pub/facets/search";

	private final TypeReference<List<VtexCategory>> CATEGORY_LIST_REF = new TypeReference<List<VtexCategory>>(){};
	private final TypeReference<VtexCategory> CATEGORY_REF = new TypeReference<VtexCategory>(){};
	private final TypeReference<VtexFilters> CATEGORY_FILTER_REF = new TypeReference<VtexFilters>(){};
	
	@Override
	public List<VtexCategory> getAllCategories() throws CategoryServiceException {
		logger.debug("Getting all categories from Vtex");
		List<VtexCategory> categories = client.execute(ALL_CATEGORIES_URL, null, RequestProtocolEnum.GET, this.CATEGORY_LIST_REF);
		logger.debug("Results all categories from Vtex service: {}", categories);
		return categories;
	    	
	}
	
	@Override
	public VtexCategory findCategory(int categoryId) throws CategoryServiceException {
		logger.debug("Getting category from Vtex : ",categoryId);
		VtexCategory category = client.execute(this.FIND_CATEGORY_URL+categoryId, null, RequestProtocolEnum.GET, this.CATEGORY_REF);
		logger.debug("Result category from Vtex service: {}", category);
		return category;
	}

	@Override
	public VtexFilters getFilters(String filter, String q) throws CategoryServiceException {
		
		logger.debug("Getting filters : " + filter + " q: "+ q);
		
		StringBuilder mapValue = new StringBuilder(250);
		StringBuilder path = new StringBuilder(250);
		
		if(filter != null && !"".equals(filter)){
			path.append(filter);
			mapValue.append(getMapParam(filter));
		}
		
		if(q != null && !"".equals(q)){
			if(mapValue.length()!=0){
				mapValue.append(",");	
			}
			path.append("/");
			path.append(q);
			mapValue.append("ft");
		}
				
		List<NameValuePair> nvps = new ArrayList<>();
		NameValuePair map = new BasicNameValuePair("map", mapValue.toString());
		nvps.add(map);
				
		VtexFilters vtexFilters = client.execute(this.GET_CATEGORY_FILTERS+path, nvps, RequestProtocolEnum.GET, this.CATEGORY_FILTER_REF);
		if(vtexFilters == null){
			logger.warn("Respuesta de VTex vino vacio. Filter:{}. Query:{}", filter, q); 
			vtexFilters = new VtexFilters();
		}
		return  vtexFilters;
	}
	
	public String getMapParam(String filter) {
		String mapParam = "";
		String[] filters = filter.split("/");
		
		for(int i=0; i < filters.length; i++){
			System.out.println(filters[i]);
			if(!"".equals(filters[i])){
				if(i == 1){
					mapParam = "c";
				}else{
					mapParam += ",c";
				}
			}
			
		}	
		return mapParam;
	}

}
