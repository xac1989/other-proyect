package com.cencosud.middleware.shoppingcart.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.cencosud.middleware.shoppingcart.configuration.ApplicationConfig.VTexProperties;
import com.cencosud.middleware.shoppingcart.utils.VtexUtilClient;

@SpringBootApplication
@ComponentScan("com.cencosud.middleware.shoppingcart")

public class WebApplication extends SpringBootServletInitializer {
	
	@Autowired
	private ApplicationConfig applicationConfig;
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {      
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
