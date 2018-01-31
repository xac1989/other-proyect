package com.cencosud.middleware.catalog.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cencosud.middleware.catalog.client.VtexClient;
import com.cencosud.middleware.catalog.client.VtexProduct;
import com.cencosud.middleware.catalog.exception.CatalogServiceException;
import com.cencosud.middleware.catalog.model.Pair;
import com.cencosud.middleware.catalog.model.Product;
import com.cencosud.middleware.catalog.model.SearchInfo;
import com.cencosud.middleware.catalog.model.SearchResult;
import com.cencosud.middleware.catalog.model.enums.RequestProtocolEnum;
import com.cencosud.middleware.catalog.repository.CatalogRepository;

public class CatalogVtexR1RepositoryImpl implements CatalogRepository {

	Logger logger = LoggerFactory.getLogger(CatalogVtexR1RepositoryImpl.class);

	VtexClient client;

	public void setClient(VtexClient client) {
		this.client = client;
	}

	private static final String SEARCH_PRODUCTS_URL = "/api/catalog_system/pub/products/search";
	private static final String PATH_DELIMITER = "/";
	private static final String ENCODED_PATH_DELIMITER = "%2F";
	private static final String SPECIFICATION_FILTER_PREFIX = "specificationFilter_";
	private static final String FULL_TEXT = "ft";

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
	public SearchResult searchProducts(String filter, String brands, String spec, String q, String o, int offset,
			int limit) throws CatalogServiceException {

		StringBuilder path = new StringBuilder();
		StringBuilder map = new StringBuilder();
		List<NameValuePair> nvps = new ArrayList<NameValuePair>(8);

		path.append(SEARCH_PRODUCTS_URL);
		boolean needsToAppendPathDelimiter = true;
		if (StringUtils.isNotEmpty(filter)) {
			path.append(filter);
			if (!filter.endsWith("/")) {
			    path.append(PATH_DELIMITER);
			}
			needsToAppendPathDelimiter = false;
			String[] filterArr = filter.split("/");
			for (String currentFilter : filterArr) {
				if (!currentFilter.isEmpty()) {
					map.append("c,");
				}
			}
		}

		if (StringUtils.isNotEmpty(brands)) {
		    if (needsToAppendPathDelimiter) {
		        path.append(PATH_DELIMITER);
		    }
		    String[] brandArray = brands.split(",");
		    for (String brand : brandArray) {
		        if (!brand.isEmpty()) {
        			path.append(brand).append(PATH_DELIMITER);
        			map.append("b,");
		        }
		    }
		    needsToAppendPathDelimiter = false;
		}

		if (StringUtils.isNotEmpty(spec)) {
		    if (needsToAppendPathDelimiter) {
                path.append(PATH_DELIMITER);
            }
		    String delimiter = spec.contains(PATH_DELIMITER) ? PATH_DELIMITER : ENCODED_PATH_DELIMITER;
			for (String currentSpec : spec.split(delimiter)) {
				String[] specArr = currentSpec.split(",");

				for (int i = 1; i < specArr.length; i++) {
				    if (!FULL_TEXT.equals(specArr[i])) {
    					map.append(SPECIFICATION_FILTER_PREFIX);
    					map.append(specArr[0]);
    					map.append(",");
    					path.append(specArr[i]);
                        path.append(PATH_DELIMITER);
				    }
				}
			}
			needsToAppendPathDelimiter = false;
		}

		if (map.length() > 0) {
			map.setLength(map.length() - 1);
			nvps.add(new BasicNameValuePair("map", map.toString()));
		}
		if (StringUtils.isNotEmpty(q)) {
			nvps.add(new BasicNameValuePair(FULL_TEXT, q));
		}

		if (StringUtils.isNotEmpty(o)) {
			nvps.add(new BasicNameValuePair("O", o));
		}
		if (offset >= 0) {
			nvps.add(new BasicNameValuePair("_from", String.valueOf(offset)));
		}
		if (limit > 0) {
			nvps.add(new BasicNameValuePair("_to", String.valueOf(offset + limit - 1)));
		}
		
		Pair<String, Map<String, String>> pair = client.executeAndGetResultAsStringAndHeaders(path.toString(), nvps,
				RequestProtocolEnum.GET);
		String responseStr = pair.getObj1();
		if (responseStr.startsWith("[")) {
			JSONArray jsonArr = new JSONArray(responseStr);
			List<VtexProduct> products = new ArrayList<VtexProduct>(jsonArr.length());
			for (int i = 0; i < jsonArr.length(); i++) {
				VtexProduct vtexProduct = new VtexProduct(jsonArr.getJSONObject(i));
				products.add(vtexProduct);
			}

			String resources = pair.getObj2().get("resources");
			int totalResult = Integer.parseInt(resources.split("/")[1]);

			SearchInfo si = new SearchInfo(products.size(), totalResult);

			return new SearchResult(products, si);
		} else {
			logger.info(responseStr);
		}
		return new SearchResult(new ArrayList<VtexProduct>(0), new SearchInfo(0, 0));

	}

	@Override
	public VtexProduct getProductDetail(String id, String salesChannel) throws CatalogServiceException {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("fq", "productId:".concat(id)));
		if (!StringUtils.isEmpty(salesChannel))
			nvps.add(new BasicNameValuePair("sc", salesChannel));

		String result = client.executeAsString(SEARCH_PRODUCTS_URL, nvps, RequestProtocolEnum.GET);
		VtexProduct vtexProduct = null;
		if (!StringUtils.isEmpty(result)) {
			JSONArray jsonArray = new JSONArray(result);
			if (jsonArray.length() > 0) {
				vtexProduct = new VtexProduct(jsonArray.getJSONObject(0));
			}

		}

		return vtexProduct;
	}

	@Override
	public SearchResult searchProducts(String path, List<NameValuePair> parameters,String salesChannel) throws CatalogServiceException {
		return null;
	}

	@Override
	public VtexProduct getProductDetail(String searchId, String id, String salesChannel)
			throws CatalogServiceException {
		// TODO: Search with different searchId, not yet implemented for region 1
		return null;
	}

	@Override
	public List<VtexProduct> getProductsDetail(String searchId, String[] id, String salesChannel)
			throws CatalogServiceException {
		throw new UnsupportedOperationException("Unsupported method");
	}

}
