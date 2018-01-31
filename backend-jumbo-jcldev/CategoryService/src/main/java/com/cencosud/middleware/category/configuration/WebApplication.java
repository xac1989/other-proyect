package com.cencosud.middleware.category.configuration;

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

import com.cencosud.middleware.category.client.VtexClient;
import com.cencosud.middleware.category.configuration.ApplicationConfig.VTexProperties;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ComponentScan("com.cencosud.middleware.category")
@EnableMongoRepositories({"com.cencosud.middleware.category.repository"})
@EnableScheduling
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
	public VtexClient getVTexClient(){
		VTexProperties vTex = applicationConfig.getVtex();
		VtexClient client = new VtexClient(vTex.getUrl(), vTex.getPort(), vTex.getApiKey(), vTex.getApiSecret(), vTex.getSchema());
		return client;
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoDbFactory mongoDbFactory = cloudConfig.connectionFactory().mongoDbFactory();
		return new MongoTemplate(mongoDbFactory.getDb().getMongo(), applicationConfig.getMongoConfig().getDatabase());
	}
}
