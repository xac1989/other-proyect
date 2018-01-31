package com.cencosud.middleware.cencosud.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class ApplicationConfig {
	
    private ServiceProperties serviceConfig;

	/**
	 * @return the serviceConfig
	 */
	public ServiceProperties getServiceConfig() {
		return serviceConfig;
	}

	/**
	 * @param serviceConfig the serviceConfig to set
	 */
	public void setServiceConfig(ServiceProperties serviceConfig) {
		this.serviceConfig = serviceConfig;
	}



	public static class ServiceProperties {
        private String urlService;
        private String urlInfoService;
        private String clientId;
		/**
		 * @return the urlService
		 */
		public String getUrlService() {
			return urlService;
		}
		/**
		 * @param urlService the urlService to set
		 */
		public void setUrlService(String urlService) {
			this.urlService = urlService;
		}
		/**
		 * @return the urlInfoService
		 */
		public String getUrlInfoService() {
			return urlInfoService;
		}
		/**
		 * @param urlInfoService the urlInfoService to set
		 */
		public void setUrlInfoService(String urlInfoService) {
			this.urlInfoService = urlInfoService;
		}
		/**
		 * @return the clientId
		 */
		public String getClientId() {
			return clientId;
		}
		/**
		 * @param clientId the clientId to set
		 */
		public void setClientId(String clientId) {
			this.clientId = clientId;
		}
    }
}