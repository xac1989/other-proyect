package com.cencosud.middleware.profile.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class ApplicationConfig {
	
	private String documentValidateRegex;

	private AwsProperties awsProperties;
	
	public String getDocumentValidateRegex() {
		return documentValidateRegex;
	}

	public void setDocumentValidateRegex(String documentValidateRegex) {
		this.documentValidateRegex = documentValidateRegex;
	}

	public AwsProperties getAwsProperties() {
		return awsProperties;
	}

	public void setAwsProperties(AwsProperties awsProperties) {
		this.awsProperties = awsProperties;
	}

	public static class AwsProperties {
		private String secretKey;

		private String accessKey;
		
		private String region;

		public String getSecretKey() {
			return secretKey;
		}

		public void setSecretKey(String secretKey) {
			this.secretKey = secretKey;
		}

		public String getAccessKey() {
			return accessKey;
		}

		public void setAccessKey(String accessKey) {
			this.accessKey = accessKey;
		}

		public String getRegion() {
			return region;
		}

		public void setRegion(String region) {
			this.region = region;
		}

		@Override
		public String toString() {
			return "AWSProperties [secretKey=" + secretKey + ", accessKey=" + accessKey + "]";
		}
		
		
	}
}