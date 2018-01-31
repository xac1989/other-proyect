package com.cencosud.middleware.profile.configuration;

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

import com.cencosud.middleware.profile.client.VtexClient;
import com.cencosud.middleware.profile.configuration.ApplicationConfig.VTexClientProperties;
import com.cencosud.middleware.profile.configuration.ApplicationConfig.VTexProperties;
import com.cencosud.middleware.profile.utils.VtexUtilClient;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ComponentScan("com.cencosud.middleware.profile")
@EnableMongoRepositories({"com.cencosud.middleware.profile.repository"})
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
	public MongoTemplate mongoTemplate() throws Exception {
		MongoDbFactory mongoDbFactory = cloudConfig.connectionFactory().mongoDbFactory();
		return new MongoTemplate(mongoDbFactory.getDb().getMongo(), applicationConfig.getMongoConfig().getDatabase());
	}
	
	@Bean(name="vtexClientJumbo")
	public VtexClient vtexClientJumbo() throws Exception {
		
		VTexProperties vtex = applicationConfig.getVtex();
		VTexClientProperties r2 = vtex.getR2();
		VtexClient vtexClient = new VtexClient(r2.getUrlVtexApiCrm(), r2.getPort(), r2.getApiKey(), r2.getApiSecret(), r2.getSchema());
		
		return vtexClient;
	}
	
	@Bean(name="vTexUtilClient")
	public VtexUtilClient getVTexClient() {
		VTexProperties vTex = applicationConfig.getVtex();
		VTexClientProperties r2 = vTex.getR2();
		VtexUtilClient client = new VtexUtilClient(r2.getUrlVtexApiCrm(), r2.getPort(), r2.getApiKey(), r2.getApiSecret(), r2.getSchema());
		return client;
	}
	
	@Bean(name="vTexUtilClientEcom")
	public VtexUtilClient getVTexClientEcom() {
		VTexProperties vTex = applicationConfig.getVtex();
		VTexClientProperties r2 = vTex.getR2();
		VtexUtilClient client = new VtexUtilClient(r2.getUrlVtexApiEcom(), r2.getPort(), r2.getApiKey(), r2.getApiSecret(), r2.getSchema());
		return client;
	}
}
