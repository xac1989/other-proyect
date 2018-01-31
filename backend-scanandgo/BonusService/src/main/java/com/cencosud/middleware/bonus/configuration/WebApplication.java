package com.cencosud.middleware.bonus.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * 
 * <h1>WebApplication</h1>
 * <p>
 * Configuración inicial de springboot y de la aplicación.
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since May 31, 2017
 */
@SpringBootApplication(exclude = { MessageSourceAutoConfiguration.class })
@ComponentScan("com.cencosud.middleware.bonus")
public class WebApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		System.setProperty("javax.net.ssl.trustStore", "src/main/resources/cert/key.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
		
		//Datos agregar proxy a la jvm
		System.setProperty("http.proxyHost", "proxy.corp.globant.com");
		System.setProperty("http.proxyPort", "3128");

		return application.sources(WebApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebApplication.class, args);
	}
}
