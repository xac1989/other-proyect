package com.cencosud.middleware.profile.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cencosud.middleware.profile.configuration.ApplicationConfig.AwsProperties;

@SpringBootApplication
@ComponentScan("com.cencosud.middleware.profile")
@EnableScheduling
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

	@Bean(name = "awsProperties")
	public AwsProperties getAWSProperties() {
		AwsProperties awsProperties = applicationConfig.getAwsProperties();
		return awsProperties;
	}
}
