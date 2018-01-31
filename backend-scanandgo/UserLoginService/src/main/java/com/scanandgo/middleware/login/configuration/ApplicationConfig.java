package com.scanandgo.middleware.login.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class ApplicationConfig {
	
	public static final String APP_IOS = "ios";
	public static final String APP_ANDROID = "android";

	private TwitterProperties twitter;

	public TwitterProperties getTwitter() {
		return twitter;
	}

	public void setTwitter(TwitterProperties twitter) {
		this.twitter = twitter;
	}

	public static class TwitterProperties {
		private TwitterApiProperties android;
		private TwitterApiProperties ios;

		public TwitterApiProperties getAndroid() {
			return android;
		}

		public void setAndroid(TwitterApiProperties android) {
			this.android = android;
		}

		public TwitterApiProperties getIos() {
			return ios;
		}

		public void setIos(TwitterApiProperties ios) {
			this.ios = ios;
		}
	}

	public static class TwitterApiProperties {
		private String consumerKey;
		private String consumerSecretKey;

		public String getConsumerKey() {
			return consumerKey;
		}

		public void setConsumerKey(String consumerKey) {
			this.consumerKey = consumerKey;
		}

		public String getConsumerSecretKey() {
			return consumerSecretKey;
		}

		public void setConsumerSecretKey(String consumerSecretKey) {
			this.consumerSecretKey = consumerSecretKey;
		}
	}
}