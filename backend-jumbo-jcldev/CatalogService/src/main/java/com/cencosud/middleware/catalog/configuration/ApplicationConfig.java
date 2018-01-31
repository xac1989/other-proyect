package com.cencosud.middleware.catalog.configuration;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class ApplicationConfig {

	private VTexProperties vtex;

	public VTexProperties getVtex() {
		return vtex;
	}

	public void setVtex(VTexProperties vtex) {
		this.vtex = vtex;
	}

	public static class VTexProperties {
		private VTexClientProperties r1;
		private VTexClientProperties r2;
		private VTexClientProperties util;


		public VTexClientProperties getR1() {
			return r1;
		}

		public void setR1(VTexClientProperties r1) {
			this.r1 = r1;
		}

		public VTexClientProperties getR2() {
			return r2;
		}

		public void setR2(VTexClientProperties r2) {
			this.r2 = r2;
		}

		public VTexClientProperties getUtil() {
			return util;
		}

		public void setUtil(VTexClientProperties util) {
			this.util = util;
		}
	}

	public static class VTexClientProperties {

		private String url;
		private String schema;
		private int port;
		private String apiKey;
		private String apiSecret;
		private BusinessProperties business;
		
		
		public BusinessProperties getBusiness() {
			return business;
		}

		public void setBusiness(BusinessProperties business) {
			this.business = business;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getSchema() {
			return schema;
		}

		public void setSchema(String schema) {
			this.schema = schema;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public String getApiKey() {
			return apiKey;
		}

		public void setApiKey(String apiKey) {
			this.apiKey = apiKey;
		}

		public String getApiSecret() {
			return apiSecret;
		}

		public void setApiSecret(String apiSecret) {
			this.apiSecret = apiSecret;
		}
	}
	
	
	public static class BusinessProperties{
		private String sourcesBaseURL;
		private List<String> highlights;
		private Map<String,String> promotionKeys;
		private Map<String,String> promotionTypes;
		private Map<String,String> promotionTitles;
		private Map<String,String> promotionColors;
		
		private String dealsParameter;

		public String getSourcesBaseURL() {
			return sourcesBaseURL;
		}
		public void setSourcesBaseURL(String sourcesBaseURL) {
			this.sourcesBaseURL = sourcesBaseURL;
		}
		public List<String> getHighlights() {
			return highlights;
		}
		public void setHighlights(List<String> highlights) {
			this.highlights = highlights;
		}
		public Map<String, String> getPromotionKeys() {
			return promotionKeys;
		}
		public void setPromotionKeys(Map<String, String> promotionKeys) {
			this.promotionKeys = promotionKeys;
		}
        public String getDealsParameter() {
            return dealsParameter;
        }
        public void setDealsParameter(String dealsParameter) {
            this.dealsParameter = dealsParameter;
        }
		public Map<String, String> getPromotionTypes() {
			return promotionTypes;
		}
		public void setPromotionTypes(Map<String, String> promotionTypes) {
			this.promotionTypes = promotionTypes;
		}
		public Map<String, String> getPromotionTitles() {
			return promotionTitles;
		}
		public void setPromotionTitles(Map<String, String> promotionTitles) {
			this.promotionTitles = promotionTitles;
		}
		public Map<String, String> getPromotionColors() {
			return promotionColors;
		}
		public void setPromotionColors(Map<String, String> promotionColors) {
			this.promotionColors = promotionColors;
		}
        
	}
	

}