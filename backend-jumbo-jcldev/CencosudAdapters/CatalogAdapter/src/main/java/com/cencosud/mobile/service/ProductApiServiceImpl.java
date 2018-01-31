package com.cencosud.mobile.service;

import static com.cencosud.mobile.util.RequestProtocolEnum.GET;
import static java.util.Collections.emptyMap;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cencosud.mobile.search.adapter.NotFoundException;
import com.cencosud.mobile.search.adapter.ProductApiService;
import com.cencosud.mobile.util.RestServiceResponse;
import com.cencosud.mobile.util.RestServiceUtil;

@Service
public class ProductApiServiceImpl implements ProductApiService {

    private static final String ROOT = "catalog/";
    private static final String SEARCH_PATH = "/product/";

//    private static final String CLIENT_ID_HEADER = "X-IBM-Client-Id";
//    private static final String CLIENT_SECRET_HEADER = "X-IBM-Client-Secret";

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
    public Response productProductIdGet(String productId, String region, String version) {
        return productProductIdGet(productId, region, version, "");
    }

    @Override
    public Response productProductIdGet(String productId, String region, String version, String sc) {
    	
        StringBuilder searchPath = new StringBuilder();
        searchPath.append(ROOT);
        searchPath.append(region);
        searchPath.append("/");
        searchPath.append(version);
        searchPath.append(SEARCH_PATH);
        searchPath.append(productId);

        Map<String, Object> parameters = null;
        if (StringUtils.isEmpty(sc)) {
            parameters = emptyMap();
        } else {
            parameters = new HashMap<>();
            parameters.put("sc", sc);
        }
        RestServiceResponse<String> serviceResponse = null;
        try {
            serviceResponse = restServiceUtil.executeService(searchPath.toString(), null, String.class, parameters, GET);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
        return serviceResponse.toResponse();
    }
}
