package com.scanandgo.middleware.batch.product.configuration;

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
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.scanandgo.middleware.batch.product.client.VtexClient;
import com.scanandgo.middleware.batch.product.configuration.ApplicationConfig.VTexProperties;

@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class,
		MessageSourceAutoConfiguration.class })
@EnableMongoRepositories({ "com.scanandgo.middleware.batch.product.repository" })
@ComponentScan("com.scanandgo.middleware.batch")
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

		// Datos agregar proxy a la jvm
//		System.setProperty("http.proxyHost", "proxy.corp.globant.com");
//		System.setProperty("http.proxyPort", "3128");
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
	public VtexClient getVTexClient() {
		VTexProperties vTex = applicationConfig.getVtex();
		VtexClient client = new VtexClient(vTex.getUrl(), vTex.getPort(), vTex.getApiKey(), vTex.getApiSecret(),
				vTex.getSchema());
		return client;
	}

	@Bean
	public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setPoolSize(3);
		threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
		return threadPoolTaskScheduler;
	}

}