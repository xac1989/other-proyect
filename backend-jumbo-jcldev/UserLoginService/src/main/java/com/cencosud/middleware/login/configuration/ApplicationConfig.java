package com.cencosud.middleware.login.configuration;

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
	private JanisProperties janisProperties;
	

	public TwitterProperties getTwitter() {
		return twitter;
	}

	public void setTwitter(TwitterProperties twitter) {
		this.twitter = twitter;
	}


	public JanisProperties getJanisProperties() {
		return janisProperties;
	}

	public void setJanisProperties(JanisProperties janisProperties) {
		this.janisProperties = janisProperties;
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
	
	private VtexProperties vtex;

	public VtexProperties getVtex() {
		return vtex;
	}

	public void setVtex(VtexProperties vtex) {
		this.vtex = vtex;
	}

	public static class VtexProperties {
		private RegionProperties r2;

		public RegionProperties getR2() {
			return r2;
		}

		public void setR2(RegionProperties r2) {
			this.r2 = r2;
		}

	}


	public static class RegionProperties {
		private VTexTokenProperties vtexToken;
		private VTexLoginProperties vtexLogin;
		private VTexCookie vtexCookie;

		public VTexTokenProperties getVtexToken() {
			return vtexToken;
		}

		public void setVtexToken(VTexTokenProperties vtexToken) {
			this.vtexToken = vtexToken;
		}

		public VTexLoginProperties getVtexLogin() {
			return vtexLogin;
		}

		public void setVtexLogin(VTexLoginProperties vtexLogin) {
			this.vtexLogin = vtexLogin;
		}

		public VTexCookie getVtexCookie() {
			return vtexCookie;
		}

		public void setVtexCookie(VTexCookie vtexCookie) {
			this.vtexCookie = vtexCookie;
		}
		
	}

	public static class VTexTokenProperties {
		private String host;
		private String schema;
		private String path;
		private String scope;

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public String getSchema() {
			return schema;
		}

		public void setSchema(String schema) {
			this.schema = schema;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getScope() {
			return scope;
		}

		public void setScope(String scope) {
			this.scope = scope;
		}

	}

	public static class VTexLoginProperties {
		private String host;
		private String schema;
		private String path;

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public String getSchema() {
			return schema;
		}

		public void setSchema(String schema) {
			this.schema = schema;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

	}
	
	public static class VTexCookie {
		private String domain;
		private String path;

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getDomain() {
			return domain;
		}

		public void setDomain(String domain) {
			this.domain = domain;
		}

	}
	
	public static class JanisProperties {
		private String schema;
		private String host;
		private String path;
		private int port;
		private String headerKey;
		private String headerValue;

		public String getSchema() {
			return schema;
		}

		public void setSchema(String schema) {
			this.schema = schema;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public String getHeaderKey() {
			return headerKey;
		}

		public void setHeaderKey(String headerKey) {
			this.headerKey = headerKey;
		}

		public String getHeaderValue() {
			return headerValue;
		}

		public void setHeaderValue(String headerValue) {
			this.headerValue = headerValue;
		}

	}
}