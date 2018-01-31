package com.cencosud.middleware.feedback.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 
 * 
 * <h1>WebApplication</h1>
 * <p>
 * Configuración inicial de springboot y de la aplicación.
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Mar 22, 2017
 */
@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class,
		MessageSourceAutoConfiguration.class })
@ComponentScan("com.cencosud.middleware.feedback")
@EnableMongoRepositories({ "com.cencosud.middleware.feedback.repository" })
@EnableScheduling
public class WebApplication extends SpringBootServletInitializer{
	@Autowired
	private ApplicationConfig applicationConfig;

	@Autowired
	private CloudConfig cloudConfig;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		System.setProperty("javax.net.ssl.trustStore", "/home/vcap/app/.compose_mongo/compose_keystore.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "liberty-buildpack-keystore-password");

		return application.sources(WebApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebApplication.class, args);
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoDbFactory mongoDbFactory = cloudConfig.connectionFactory().mongoDbFactory();
		return new MongoTemplate(mongoDbFactory.getDb().getMongo(), applicationConfig.getMongoConfig().getDatabase());
	}
}
