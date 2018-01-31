package com.cencosud.middleware.rewards.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class ApplicationConfig {
	
    private RewardsProperties rewards;

    public RewardsProperties getRewards() {
        return rewards;
    }

    public void setRewards(RewardsProperties rewards) {
        this.rewards = rewards;
    }

	public static class RewardsProperties {
		private String schema;
        private String url;
        private int port;
        
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
    }
}