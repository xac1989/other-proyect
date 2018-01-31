package com.cencosud.middleware.profile.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class ApplicationConfig {

	private String documentValidateRegex;

	private MongoProperties mongoConfig;

	private VTexProperties vtex;

	public MongoProperties getMongoConfig() {
		return mongoConfig;
	}

	public void setMongoConfig(MongoProperties mongoConfig) {
		this.mongoConfig = mongoConfig;
	}

	public String getDocumentValidateRegex() {
		return documentValidateRegex;
	}

	public void setDocumentValidateRegex(String documentValidateRegex) {
		this.documentValidateRegex = documentValidateRegex;
	}

	public VTexProperties getVtex() {
		return vtex;
	}

	public void setVtex(VTexProperties vtex) {
		this.vtex = vtex;
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

	public static class VTexProperties {
		private VTexClientProperties r2;

		public VTexClientProperties getR2() {
			return r2;
		}

		public void setR2(VTexClientProperties r2) {
			this.r2 = r2;
		}

	}

	public static class VTexClientProperties {

		private String urlVtexApiCrm;
		private String schema;
		private int port;
		private String apiKey;
		private String apiSecret;
		private String urlVtexApiEcom;

		public String getUrlVtexApiCrm() {
			return urlVtexApiCrm;
		}

		public void setUrlVtexApiCrm(String urlVtexApiCrm) {
			this.urlVtexApiCrm = urlVtexApiCrm;
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

		public String getUrlVtexApiEcom() {
			return urlVtexApiEcom;
		}

		public void setUrlVtexApiEcom(String urlVtexApiEcom) {
			this.urlVtexApiEcom = urlVtexApiEcom;
		}

	}

}