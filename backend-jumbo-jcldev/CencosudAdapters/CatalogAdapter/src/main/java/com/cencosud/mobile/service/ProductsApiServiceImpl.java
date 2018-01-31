package com.cencosud.mobile.service;

import static com.cencosud.mobile.util.RequestProtocolEnum.GET;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cencosud.mobile.search.adapter.NotFoundException;
import com.cencosud.mobile.search.adapter.ProductsApiService;
import com.cencosud.mobile.search.model.ProductSearchParameter;
import com.cencosud.mobile.util.RestServiceResponse;
import com.cencosud.mobile.util.RestServiceUtil;

@Service
public class ProductsApiServiceImpl implements ProductsApiService {

    private static final Logger logger = Logger.getLogger(ProductsApiServiceImpl.class.getName());

	private static final String ROOT = "catalog/";
	private static final String SEARCH_PATH = "/products/search";
	private static final String DEALS_PATH = "/products/deals";

    @Value("${api_base_url}")
    private String apiBaseUrl;
    @Value("${api_client_id}")
    private String apiClientId;
    @Value("${api_secret}")
    private String apiSecret;

    @Autowired
    private RestServiceUtil restServiceUtil;
    
    

	public void setRestServiceUtil(RestServiceUtil restServiceUtil) {
		this.restServiceUtil = restServiceUtil;
	}

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

    @Override
	public RestServiceResponse<String> productsSearchGet(ProductSearchParameter productSearchParameter) {
        return execute(SEARCH_PATH, productSearchParameter);
	}

    @Override
	public RestServiceResponse<String> getDeals(ProductSearchParameter productSearchParameter) {
        return execute(DEALS_PATH, productSearchParameter);
    }

    private RestServiceResponse<String> execute(String path, ProductSearchParameter productSearchParameter) {
        StringBuilder searchPath = new StringBuilder();
        searchPath.append(ROOT);
        searchPath.append(productSearchParameter.getRegion());
        searchPath.append("/");
        searchPath.append(productSearchParameter.getVersion());
        searchPath.append(path);
        RestServiceResponse<String> serviceResponse = null;
        try {
            Map<String, Object> parameters = productSearchParameter.toMap();
            logger.finer("Parameters: " + parameters);
            serviceResponse = restServiceUtil.executeService(searchPath.toString(), null, String.class, parameters, GET);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
        return serviceResponse;
    }
}
