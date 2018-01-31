package com.cencosud.middleware.coupon.configuration;

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
		private String schema;
		private String tokenSchema;
		private String url;    
		private int port;
		private int tokenPort;
		private String grantType;
		private String clientId;
		private String clientSecret;
		
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
		public int getPort() {
			return port;
		}
		public void setPort(int port) {
			this.port = port;
		}
		public int getTokenPort() {
			return tokenPort;
		}
		public void setTokenPort(int tokenPort) {
			this.tokenPort = tokenPort;
		}
		public String getGrantType() {
			return grantType;
		}
		public void setGrantType(String grantType) {
			this.grantType = grantType;
		}
		public String getClientId() {
			return clientId;
		}
		public void setClientId(String clientId) {
			this.clientId = clientId;
		}
		public String getClientSecret() {
			return clientSecret;
		}
		public void setClientSecret(String clientSecret) {
			this.clientSecret = clientSecret;
		}
		public String getTokenSchema() {
			return tokenSchema;
		}
		public void setTokenSchema(String tokenSchema) {
			this.tokenSchema = tokenSchema;
		}
		
		
		
    }
	
  
}