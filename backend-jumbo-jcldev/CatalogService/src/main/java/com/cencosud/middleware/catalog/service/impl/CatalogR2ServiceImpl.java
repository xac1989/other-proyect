package com.cencosud.middleware.catalog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cencosud.middleware.catalog.client.VtexProduct;
import com.cencosud.middleware.catalog.configuration.ApplicationConfig;
import com.cencosud.middleware.catalog.dto.mapper.ProductJumboMapper;
import com.cencosud.middleware.catalog.dto.mapper.ProductMapper;
import com.cencosud.middleware.catalog.dto.mapper.SearchProductJumboMapper;
import com.cencosud.middleware.catalog.dto.mapper.SearchProductMapper;
import com.cencosud.middleware.catalog.exception.CatalogServiceException;
import com.cencosud.middleware.catalog.model.Product;
import com.cencosud.middleware.catalog.model.SearchResult;
import com.cencosud.middleware.catalog.repository.CatalogRepository;
import com.cencosud.middleware.catalog.service.CatalogService;

@Service
public class CatalogR2ServiceImpl implements CatalogService {

	// Region2 = JUMBO CHILE
	private static final String REGION_ID = "r2";

	CatalogRepository repo;

    private ApplicationConfig applicationConfig;

    private static final String PATH_DELIMITER = "/";
    private static final String MAP_SPECIFICATION_FILTER = "specificationFilter_";
    private static final String MAP_PRODUCT_CLUSTER_IDS = "productClusterIds";
    private static final String MAP_CATEGORY = "c";
    private static final String MAP_PARAMETER = "map";

	@Autowired
	@Qualifier(value = "catalogVtexRepositoryR2")
	public void setRepo(CatalogRepository repo) {
		this.repo = repo;
	}

    @Autowired
	public void setApplicationConfig(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Override
	public String getRegionId() {
		return REGION_ID;
	}

	@Override
	public List<Product> getAllProducts() throws CatalogServiceException {
		return repo.getAllProducts();
	}

	@Override
	public SearchResult searchProducts(String filter, String brand, String spec, String freeText, String o, int offset,
			int limit, String salesChannel) throws CatalogServiceException {
		return execute(filter, brand, spec, freeText, o, offset, limit, salesChannel, false);
	}

	private List<NameValuePair> defineParameterList(String freeText, String o, int offset, int limit,
			String salesChannel) {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		
		if (!StringUtils.isEmpty(freeText)) {
			parameters.add(new BasicNameValuePair("ft", freeText));
		}

		if (!StringUtils.isEmpty(o)) {
			parameters.add(new BasicNameValuePair("O", o));
		}
		if (offset >= 0) {
			parameters.add(new BasicNameValuePair("_from", String.valueOf(offset)));
		}
		if (limit > 0) {
			parameters.add(new BasicNameValuePair("_to", String.valueOf(offset + limit - 1)));
		}
		if (!StringUtils.isEmpty(salesChannel)) {
			parameters.add(new BasicNameValuePair("sc", salesChannel));
		}
		return parameters;
	}

	@Override
	public VtexProduct getProductDetail(String searchId, String productId,String salesChannel) throws CatalogServiceException {
		return repo.getProductDetail(searchId, productId,salesChannel);
	}
	
	
	@Override
	public List<VtexProduct> getProductsDetail(String searchId, String[] productId,String salesChannel) throws CatalogServiceException {
		return repo.getProductsDetail(searchId, productId,salesChannel);
	}

	public ProductMapper getProductMapper() {
		return Mappers.getMapper(ProductJumboMapper.class);
	}

	public SearchProductMapper getSearchProductMapper(){
		return Mappers.getMapper(SearchProductJumboMapper.class);
	}

	@Override
	public VtexProduct getProductDetail(String productId, String salesChannel) throws CatalogServiceException {
		return repo.getProductDetail(productId,salesChannel);
	}

	@Override
	public SearchResult searchDealsProducts(String o, int offset, int limit, String filter, String brand, String spec, String freeText, String salesChannel)
            throws CatalogServiceException {
	    return execute(filter, brand, spec, freeText, o, offset, limit, salesChannel, true);
	}

    private SearchResult execute(String filter, String brand, String spec, String freeText, String o, int offset,
            int limit, String salesChannel, boolean deals) {
        List<NameValuePair> parameters = defineParameterList(freeText, o, offset, limit, salesChannel);
        StringBuilder path = new StringBuilder();
        StringBuilder map = new StringBuilder();

        if (deals) {
            path.append('/').append(applicationConfig.getVtex().getR2().getBusiness().getDealsParameter());
            map.append(MAP_PRODUCT_CLUSTER_IDS).append(',');
        }
        if (!StringUtils.isEmpty(filter)) {
            path.append(filter);
            String[] filterArr = filter.split("/");
            for (String currentFilter : filterArr) {
                if (!currentFilter.isEmpty()) {
                    map.append(MAP_CATEGORY).append(',');
                }
            }
        }
        if (!StringUtils.isEmpty(spec)) {
            for (String currentSpec : spec.split(PATH_DELIMITER)) {
                String[] specArr = currentSpec.split(",");
                for (int i = 1; i < specArr.length; i++) {
                    map.append(MAP_SPECIFICATION_FILTER);
                    map.append(specArr[0]);
                    map.append(",");
                    path.append(PATH_DELIMITER);
                    path.append(specArr[i]);
                }
            }
        }
        if (!StringUtils.isEmpty(brand)) {
            path.append(PATH_DELIMITER);
            path.append(brand.replaceAll(",", "/"));
            String[] brandArr = brand.split(",");
            for (String currentBrand : brandArr) {
                if (!currentBrand.isEmpty()) {
                    map.append("b,");
                }
            }
        }
        if (map.length() > 0) {
            map.setLength(map.length() - 1);
            parameters.add(new BasicNameValuePair(MAP_PARAMETER, map.toString()));
        }

        return repo.searchProducts(path.toString(), parameters, salesChannel);        
    }
}
