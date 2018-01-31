package com.cencosud.middleware.register.configuration;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class ApplicationConfig {

	private VTexProperties vtex;
	private VTexProperties vtexProfile;

	/**
	 * @return the vtexProfile
	 */
	public VTexProperties getVtexProfile() {
		return vtexProfile;
	}

	/**
	 * @param vtexProfile the vtexProfile to set
	 */
	public void setVtexProfile(VTexProperties vtexProfile) {
		this.vtexProfile = vtexProfile;
	}

	public VTexProperties getVtex() {
		return vtex;
	}

	public void setVtex(VTexProperties vtex) {
		this.vtex = vtex;
	}

	public static class VTexProperties {
		private VTexClientProperties util;

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
		private Integer port;
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

		public Integer getPort() {
			return port;
		}

		public void setPort(Integer port) {
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
		
        
	}
	

}