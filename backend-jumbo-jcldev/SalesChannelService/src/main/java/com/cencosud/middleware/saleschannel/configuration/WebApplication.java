package com.cencosud.middleware.saleschannel.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cencosud.middleware.saleschannel.client.VtexClient;
import com.cencosud.middleware.saleschannel.configuration.ApplicationConfig.VTexProperties;

@SpringBootApplication(exclude = { MessageSourceAutoConfiguration.class })
@ComponentScan("com.cencosud.middleware.saleschannel")
@EnableScheduling
public class WebApplication extends SpringBootServletInitializer {

	@Autowired
	private ApplicationConfig applicationConfig;
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		// Datos agregar proxy a la jvm
		return application.sources(WebApplication.class);
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebApplication.class, args);
	}

	@Bean(name="vTexClient")
	public VtexClient getVTexClient() {
		VTexProperties vTex = applicationConfig.getVtex();
		VtexClient client = new VtexClient(vTex.getUrl(), vTex.getPort(), vTex.getApiKey(), vTex.getApiSecret(),
				vTex.getSchema());
		return client;
	}
	
	@Bean
	public VtexClient vTexEntitiesClient() {
		VTexProperties vTex = applicationConfig.getVtexEntities();
		VtexClient client = new VtexClient(vTex.getUrl(), vTex.getPort(), vTex.getApiKey(), vTex.getApiSecret(),
				vTex.getSchema());
		return client;
	}
}
