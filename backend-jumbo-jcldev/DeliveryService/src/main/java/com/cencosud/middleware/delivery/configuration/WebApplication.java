package com.cencosud.middleware.delivery.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.cencosud.middleware.delivery.configuration.ApplicationConfig.VTexProperties;
import com.cencosud.middleware.profile.utils.VtexUtilClient;

@SpringBootApplication
@ComponentScan("com.cencosud.middleware.delivery")

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
			
	@Bean(name="vTexUtilClient")
	public VtexUtilClient getVTexClient() {
		VTexProperties vTex = applicationConfig.getVtex();
		VtexUtilClient client = new VtexUtilClient(vTex.getUrl(), vTex.getPort(), vTex.getApiKey(), vTex.getApiSecret(), vTex.getSchema());
		return client;
	}
}
