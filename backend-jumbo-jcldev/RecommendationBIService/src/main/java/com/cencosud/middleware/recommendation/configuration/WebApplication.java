package com.cencosud.middleware.recommendation.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cencosud.middleware.recommendation.client.HttpCustomClient;
import com.cencosud.middleware.recommendation.configuration.ApplicationConfig.ChaordicProperties;

@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
@ComponentScan("com.cencosud.middleware.recommendation")
@EnableMongoRepositories({ "com.cencosud.middleware.recommendation.repository" })
public class WebApplication extends SpringBootServletInitializer {

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

	@Bean
	public HttpCustomClient getHttpCustomClient(){
		ChaordicProperties chaordic = applicationConfig.getChaordic();
		HttpCustomClient client = new HttpCustomClient(chaordic.getUrl(), chaordic.getApiKey(), chaordic.getApiSecret(), chaordic.getSchema(), false);
		return client;
	}
}
