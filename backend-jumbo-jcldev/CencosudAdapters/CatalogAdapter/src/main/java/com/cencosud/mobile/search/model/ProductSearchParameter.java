package com.cencosud.mobile.search.model;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.logging.Level.SEVERE;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriUtils;

public class ProductSearchParameter {

    private static final Logger logger = Logger.getLogger(ProductSearchParameter.class.getName()); 

    private String region;
    private String version;
    private String textSearch;
    private String order;
    private Integer offset;
    private Integer limit;
    private String filter;
    private String brand;
    private List<String> specifications;
    private String salesChannel;

    public String getRegion() {
        return region;
    }

    public ProductSearchParameter setRegion(String region) {
        this.region = region;
        return this;
    }
    
    public String getVersion() {
		return version;
	}

	public ProductSearchParameter setVersion(String version) {
		this.version = version;
		return this;
	}

	public String getTextSearch() {
        return textSearch;
    }

    public ProductSearchParameter setTextSearch(String textSearch) {
        this.textSearch = textSearch;
        return this;
    }

    public String getOrder() {
        return order;
    }

    public ProductSearchParameter setOrder(String order) {
        this.order = order;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public ProductSearchParameter setOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public ProductSearchParameter setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public String getFilter() {
        return filter;
    }

    public ProductSearchParameter setFilter(String filter) {
        this.filter = filter;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public ProductSearchParameter setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public List<String> getSpec() {
        return specifications;
    }

    public ProductSearchParameter setSpec(List<String> spec) {
        this.specifications = spec;
        return this;
    }

    public String getSalesChannel() {
        return salesChannel;
    }

    public ProductSearchParameter setSalesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
        return this;
    }

    private String parseSpecifications() {
        StringBuilder specs = new StringBuilder();
        if (!CollectionUtils.isEmpty(specifications)) {
            for (String specification : specifications) {
                try {
                    specs.append(UriUtils.encodePath(specification, UTF_8.toString())).append('/');
                } catch (UnsupportedEncodingException e) {
                    logger.log(SEVERE, "ERROR : Specifications " + specification + " no pudo ser codificado", e);
                }
            }
        }
        if (specs.length() > 0) {
            specs.deleteCharAt(specs.length() - 1);
        }
        return specs.toString();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("region", region);
        map.put("q", textSearch);
        map.put("o", order);
        map.put("offset", offset);
        map.put("limit", limit);
        map.put("filter", filter);
        map.put("brand", brand);
        map.put("spec", parseSpecifications());
        map.put("region", region);
        if (salesChannel != null) {
            map.put("sc", salesChannel);
        }
        return map;
    }
}
