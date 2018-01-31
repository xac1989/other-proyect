package com.cencosud.middleware.feedback.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * 
 * <h1>ApplicationConfig</h1>
 * <p>
 * Clase que contiene la configuración de los archivos yml.
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Mar 22, 2017
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class ApplicationConfig {
	
    private MongoProperties mongoConfig;

	/**
	 * @return the mongoConfig
	 */
	public MongoProperties getMongoConfig() {
		return mongoConfig;
	}



	/**
	 * @param mongoConfig the mongoConfig to set
	 */
	public void setMongoConfig(MongoProperties mongoConfig) {
		this.mongoConfig = mongoConfig;
	}



	/**
	 * 
	 * 
	 * <h1>MongoProperties</h1>
	 * <p>
	 * Clase estática con la información de la conexión a base de datos.
	 * </p>
	 * @author fernando.castro
	 * @version 1.0
	 * @since Mar 22, 2017
	 */
	public static class MongoProperties {
        private String database;

		public String getDatabase() {
			return database;
		}

		public void setDatabase(String database) {
			this.database = database;
		}
    }
}