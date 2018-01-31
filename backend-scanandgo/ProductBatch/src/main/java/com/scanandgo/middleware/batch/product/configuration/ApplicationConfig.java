package com.scanandgo.middleware.batch.product.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class ApplicationConfig {

	private VTexProperties vtex;
	private MongoProperties mongoConfig;
	private SFTPProperties sftpConfig;
	private SelloProperties selloConfig;

    public VTexProperties getVtex() {
        return vtex;
    }

    public void setVtex(VTexProperties vtex) {
        this.vtex = vtex;
    }
    
	public MongoProperties getMongoConfig() {
		return mongoConfig;
	}

	public void setMongoConfig(MongoProperties mongoConfig) {
		this.mongoConfig = mongoConfig;
	}

	public SFTPProperties getSftpConfig() {
		return sftpConfig;
	}

	public void setSftpConfig(SFTPProperties sftpConfig) {
		this.sftpConfig = sftpConfig;
	}

	public SelloProperties getSelloConfig() {
		return selloConfig;
	}

	public void setSelloConfig(SelloProperties selloConfig) {
		this.selloConfig = selloConfig;
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

	public static class MongoProperties {
		private String database;

		public String getDatabase() {
			return database;
		}

		public void setDatabase(String database) {
			this.database = database;
		}
	}
	
	public static class SFTPProperties {
		private String url;
		private String username;
		private String password;
		private String pathEntrada;
		private String pathSalida;
		private String pathError;
		private Integer port;
		
		public Integer getPort() {
			return port;
		}

		public void setPort(Integer port) {
			this.port = port;
		}

		public String getUrl() {
			return url;
		}
		
		public void setUrl(String url) {
			this.url = url;
		}
		
		public String getUsername() {
			return username;
		}
		
		public void setUsername(String username) {
			this.username = username;
		}
		
		public String getPassword() {
			return password;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
 
		public String getPathEntrada() {
			return pathEntrada;
		}

		public void setPathEntrada(String pathEntrada) {
			this.pathEntrada = pathEntrada;
		}

		public String getPathSalida() {
			return pathSalida;
		}

		public void setPathSalida(String pathSalida) {
			this.pathSalida = pathSalida;
		}

		public String getPathError() {
			return pathError;
		}

		public void setPathError(String pathError) {
			this.pathError = pathError;
		}
		
	}
	
	public static class SelloProperties {
		String pathArchivo;

		public String getPathArchivo() {
			return pathArchivo;
		}

		public void setPathArchivo(String pathArchivo) {
			this.pathArchivo = pathArchivo;
		}
	}
}