package com.cencosud.middleware.rewards.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { MessageSourceAutoConfiguration.class })
@ComponentScan("com.cencosud.middleware.rewards")
public class WebApplication extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		System.setProperty("http.proxyHost", "proxy.corp.globant.com");
		System.setProperty("http.proxyPort", "3128");
		System.setProperty("jsse.enableSNIExtension", "false");
		System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1");
        return application.sources(WebApplication.class);
    }

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebApplication.class, args);
    }
}
