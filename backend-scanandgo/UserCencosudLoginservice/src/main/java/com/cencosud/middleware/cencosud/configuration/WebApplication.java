package com.cencosud.middleware.cencosud.configuration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = { MessageSourceAutoConfiguration.class })
@ComponentScan("com.cencosud.middleware.cencosud")
@EnableScheduling
public class WebApplication extends SpringBootServletInitializer {


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		
		//Datos agregar proxy a la jvm
		System.setProperty("http.proxyHost", "proxy.corp.globant.com");
		System.setProperty("http.proxyPort", "3128");
		System.setProperty("jsse.enableSNIExtension", "false");

		return application.sources(WebApplication.class);
	}

}
