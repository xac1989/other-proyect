package com.cencosud.middleware.catalog.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.cencosud.middleware.catalog.client.VtexClient;
import com.cencosud.middleware.catalog.client.VtexHighlight;
import com.cencosud.middleware.catalog.client.VtexProduct;
import com.cencosud.middleware.catalog.client.VtexPromotion;
import com.cencosud.middleware.catalog.component.PromotionLoader;
import com.cencosud.middleware.catalog.configuration.ApplicationConfig;
import com.cencosud.middleware.catalog.configuration.ApplicationConfig.BusinessProperties;
import com.cencosud.middleware.catalog.exception.CatalogServiceException;
import com.cencosud.middleware.catalog.model.Pair;
import com.cencosud.middleware.catalog.model.Product;
import com.cencosud.middleware.catalog.model.SearchInfo;
import com.cencosud.middleware.catalog.model.SearchResult;
import com.cencosud.middleware.catalog.model.enums.RequestProtocolEnum;
import com.cencosud.middleware.catalog.repository.CatalogRepository;

public class CatalogVtexR2RepositoryImpl implements CatalogRepository {

	Logger logger = LoggerFactory.getLogger(CatalogVtexR2RepositoryImpl.class);
	private static final String FLAG = "flag_";

	@Autowired
	private ApplicationConfig applicationConfig;
	
	@Autowired
	private PromotionLoader promotionLoader;
	
	VtexClient client;
	private final String SEARCH_PRODUCTS_URL = "/api/catalog_system/pub/products/search";
	
	private static final Map<String, String> ID_PARAM_NAMES = new HashMap<String, String>();
	 static {
		 ID_PARAM_NAMES.put("SKU", "skuId");
		 ID_PARAM_NAMES.put("PID", "productId");
	    }

	public void setClient(VtexClient client) {
		this.client = client;
	}

	@Override
	public List<Product> getAllProducts() throws CatalogServiceException {
		String responseStr = client.executeAsString(SEARCH_PRODUCTS_URL, null, RequestProtocolEnum.GET);
		JSONArray jsonArr = new JSONArray(responseStr);

		List<Product> products = new ArrayList<Product>(jsonArr.length());
		for (int i = 0; i < jsonArr.length(); i++) {
			VtexProduct vtexProduct = new VtexProduct(jsonArr.getJSONObject(i));			
			products.add(vtexProduct.toModelProduct());
		}
		return products;

	}

	@Override
	public SearchResult searchProducts(String path, List<NameValuePair> parameters,String salesChannel) throws CatalogServiceException {
		StringBuilder searchPath = new StringBuilder();
		searchPath.append(SEARCH_PRODUCTS_URL);
		if(!StringUtils.isEmpty(path)){
			searchPath.append(path);
		}
		Pair<String, Map<String, String>> pair = client.executeAndGetResultAsStringAndHeaders(searchPath.toString(),
				parameters, RequestProtocolEnum.GET);
		String jsonResponse = pair.getObj1();
		if (jsonResponse.startsWith("[")) {
			JSONArray jsonArr = new JSONArray(jsonResponse);
			List<VtexProduct> vtexProducts = new ArrayList<VtexProduct>();
			for (int i = 0; i < jsonArr.length(); i++) {
				VtexProduct vtexProduct = new VtexProduct(jsonArr.getJSONObject(i));
				vtexProduct.setSalesChannel(salesChannel);
				vtexProduct.setPromotions(promotionLoader.loadPromotions(vtexProduct, salesChannel));
				vtexProduct.setHighlights(mergeHighlights(vtexProduct, vtexProduct.getPromotions(),2));
				vtexProducts.add(vtexProduct);
			}

			String resources = pair.getObj2().get("resources");
			int totalResult = Integer.parseInt(resources.split("/")[1]);

			SearchInfo si = new SearchInfo(vtexProducts.size(), totalResult);

			return new SearchResult(vtexProducts, si);
		} else {
			logger.info(jsonResponse);
		}

		return new SearchResult(new ArrayList<VtexProduct>(0), new SearchInfo(0, 0));
	}

	private List<VtexHighlight> mergeHighlights(VtexProduct vtexProduct, List<VtexPromotion> promotions,Integer maxHighlights) {
		List<VtexHighlight> attributeHighlights = new ArrayList<>();
		List<VtexHighlight> highlightsOrdered = new ArrayList<>();
		BusinessProperties business = applicationConfig.getVtex().getR2().getBusiness();
		List<VtexHighlight> vtexHighlightList = new ArrayList<>(); 
		
		promotions.forEach(promotion -> {
			if(promotion.getHighlight() != null) {
				vtexHighlightList.add(promotion.getHighlight());
			}
		});

				
		for(VtexHighlight vtexHighlight: vtexProduct.getHighlights()){
			String highlightId = normalizeName(vtexHighlight.getName());

			VtexHighlight vtexHighlightNew = new VtexHighlight();
			Boolean isBusiness = business.getHighlights().indexOf(highlightId)> -1;
			
			if(isBusiness){
				vtexHighlightNew.setId(highlightId);
				vtexHighlightNew.setName(vtexHighlight.getName());
				vtexHighlightNew.setImageAvailable(true);
				vtexHighlightNew.setPng(business.getSourcesBaseURL()+"png/"+highlightId+".png");
				vtexHighlightNew.setSvg(business.getSourcesBaseURL()+"svg/"+highlightId+".svg");
				
				Boolean isDiscountCencosud = highlightId.matches("descuento_cencosud$");

				if(isBusiness && isDiscountCencosud){
					highlightsOrdered.add(vtexHighlightNew);
				} else {
					attributeHighlights.add(vtexHighlightNew);
				}
			}
		}

		highlightsOrdered.addAll(attributeHighlights);
		vtexHighlightList.addAll(highlightsOrdered);
		
		if(vtexHighlightList.size()>maxHighlights){
			return vtexHighlightList.subList(0, maxHighlights);
		}
		return vtexHighlightList;
	}


	private String normalizeName(String name) {
		String normalized= name;
		int separatorIndx = normalized.indexOf(" - ");
		if(separatorIndx > -1){
			normalized = normalized.substring(0, separatorIndx);
		}
		normalized = normalized
				.toLowerCase()
				.replace(" ", "_")
				.replace("ñ", "n")
				.replace("%", "pc")
				.replace("á", "a")
				.replace("é", "e")
				.replace("í", "i")
				.replace("ó", "o")
				.replace("ú", "u");
		return normalized;
	}

	@Override
	public VtexProduct getProductDetail(String id, String salesChannel) throws CatalogServiceException {
		return getVtexProduct("PID", id, salesChannel);
	}
	
	@Override
	public List<VtexProduct> getProductsDetail(String searchId, String[] ids, String salesChannel)
			throws CatalogServiceException{
		return getVtexProducts(searchId,ids,salesChannel);
		
	}
	@Override
	public VtexProduct getProductDetail(String searchId, String id, String salesChannel) throws CatalogServiceException {
		return getVtexProduct(searchId, id, salesChannel);
	}

	private VtexProduct getVtexProduct(String searchId, String id, String salesChannel) throws CatalogServiceException {
		
		List<VtexProduct> results = getVtexProducts(searchId,new String[]{id},salesChannel);
		if(results.isEmpty()) {
			return null;
		}
		
		VtexProduct vtexProduct =  getVtexProducts(searchId,new String[]{id},salesChannel).get(0);
		if(vtexProduct == null){
			return null;
		}
		
		vtexProduct.setPromotions(promotionLoader.loadPromotions(vtexProduct, salesChannel));
		vtexProduct.setHighlights(mergeHighlights(vtexProduct, vtexProduct.getPromotions(), 4));
		
		return vtexProduct;
	}
	
	private List<VtexProduct> getVtexProducts(String searchId, String[] ids, String salesChannel) throws CatalogServiceException {
		
		if (ids == null || ids.length <= 0) {
			logger.debug("Ids is empty");
			return new ArrayList<VtexProduct>(0);
		}
		
		int page = ids.length / 50;
		if (ids.length % 50 > 0) {
			page++;
		}
		
		List<VtexProduct> listVtexProduct = new ArrayList<>(ids.length);
		for (int i = 0; i < page; i++) {
			listVtexProduct.addAll(getVtexProductPages(searchId, ids, salesChannel, i * 50, i * 50 + 49));
		}		
		logger.debug("Cantidad total : " + listVtexProduct.size());
		return listVtexProduct;
	}
	
	private List<VtexProduct> getVtexProductPages(String searchId, String[] ids, String salesChannel, int offset ,int limit) throws CatalogServiceException {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		String idParamName = ID_PARAM_NAMES.get(searchId);
		if(StringUtils.isEmpty(idParamName)){
			logger.error("Invalid param name defined for search");
			return new ArrayList<VtexProduct>(0);
		}
		for(String id: ids){
			parameters.add(new BasicNameValuePair("fq", idParamName.concat(":").concat(id)));
		}
		if (!StringUtils.isEmpty(salesChannel))
			parameters.add(new BasicNameValuePair("sc", salesChannel));
		if (offset >= 0) {
			parameters.add(new BasicNameValuePair("_from", String.valueOf(offset)));
		}
		if (limit >= 0) {
			parameters.add(new BasicNameValuePair("_to", String.valueOf(limit)));
		}
				
		String result = executeQuery(SEARCH_PRODUCTS_URL, parameters, RequestProtocolEnum.GET);
		List<VtexProduct> vtexProducts = new ArrayList<VtexProduct>();
		VtexProduct vtexProduct;
		if (!StringUtils.isEmpty(result)) {
			JSONArray jsonArray = new JSONArray(result);
				for(Object json : jsonArray){
					vtexProduct = new VtexProduct((JSONObject) json);
					vtexProduct.setSalesChannel(salesChannel);
					vtexProduct.setPromotions(promotionLoader.loadPromotions(vtexProduct, salesChannel));	
					vtexProducts.add(vtexProduct);
				}
		
		}
		return vtexProducts;
	}

	private String executeQuery (String path, List<NameValuePair> parameters, RequestProtocolEnum protocol) throws CatalogServiceException{
		 return client.executeAsString(path, parameters, protocol);
	}

	@Override
	public SearchResult searchProducts(String filter, String brand, String spec, String q, String o, int offset,
			int limit) throws CatalogServiceException {
		return null;
	}


}
