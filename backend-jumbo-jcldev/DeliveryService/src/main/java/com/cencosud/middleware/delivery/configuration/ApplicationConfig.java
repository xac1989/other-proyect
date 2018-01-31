package com.cencosud.middleware.delivery.configuration;

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

        private String url;
        private String schema;
        private int port;
        private String apiKey;
        private String apiSecret;

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

}