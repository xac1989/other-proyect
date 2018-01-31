package com.cencosud.middleware.recommendation.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class ApplicationConfig {

	private MongoProperties mongoConfig;
	private ChaordicProperties chaordic;

	public ChaordicProperties getChaordic() {
		return chaordic;
	}

	public void setChaordic(ChaordicProperties chaordic) {
		this.chaordic = chaordic;
	}

	public MongoProperties getMongoConfig() {
		return mongoConfig;
	}

	public void setMongoConfig(MongoProperties mongoConfig) {
		this.mongoConfig = mongoConfig;
	}

	public static class MongoProperties {
		private String database;

		public String getDatabase() {
			return database;
		}

		public void setDatabase(String database) {
			this.database = database;
		}
	}
	
	public static class ChaordicProperties {
		private String schema;
		private String url;
//		private int port;
		private String apiKey;
		private String apiSecret;
		private String defaultProductId;
		private String defaultProductSearchType;
		private String defaultCategorySearchType;
		private String baseUrlImages;
		
		public String getSchema() {
			return schema;
		}
		public void setSchema(String schema) {
			this.schema = schema;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
//		public int getPort() {
//			return port;
//		}
//		public void setPort(int port) {
//			this.port = port;
//		}
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
		public String getDefaultProductId() {
			return defaultProductId;
		}
		public void setDefaultProductId(String defaultProductId) {
			this.defaultProductId = defaultProductId;
		}
		public String getDefaultProductSearchType() {
			return defaultProductSearchType;
		}
		public void setDefaultProductSearchType(String defaultProductSearchType) {
			this.defaultProductSearchType = defaultProductSearchType;
		}
		public String getDefaultCategorySearchType() {
			return defaultCategorySearchType;
		}
		public void setDefaultCategorySearchType(String defaultCategorySearchType) {
			this.defaultCategorySearchType = defaultCategorySearchType;
		}
		public String getBaseUrlImages() {
			return baseUrlImages;
		}
		public void setBaseUrlImages(String baseUrlImages) {
			this.baseUrlImages = baseUrlImages;
		}
	}
}