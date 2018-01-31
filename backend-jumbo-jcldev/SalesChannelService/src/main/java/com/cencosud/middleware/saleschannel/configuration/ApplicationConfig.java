package com.cencosud.middleware.saleschannel.configuration;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class ApplicationConfig {

	private VTexProperties vtex;
	private VTexProperties vtexEntities;
	private Map<Integer,SalesChannelInfo> salesChannelInfo;

    public VTexProperties getVtex() {
        return vtex;
    }

    public void setVtex(VTexProperties vtex) {
        this.vtex = vtex;
    }
    
	
	public VTexProperties getVtexEntities() {
		return vtexEntities;
	}

	public void setVtexEntities(VTexProperties vtexEntities) {
		this.vtexEntities = vtexEntities;
	}


	public Map<Integer,SalesChannelInfo> getSalesChannelInfo() {
		return salesChannelInfo;
	}

	public void setSalesChannelInfo(Map<Integer,SalesChannelInfo> salesChannelInfo) {
		this.salesChannelInfo = salesChannelInfo;
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
	
	public static class SalesChannelInfo {
		private Integer id;
		private String address;
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		
	}
}