package com.cencosud.middleware.login.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.cencosud.middleware.login.repository.UserLoginRepository;
import com.cencosud.middleware.login.repository.impl.UserLoginR2RepositoryImpl;

@SpringBootApplication
@ComponentScan("com.cencosud.middleware.login")
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
	
	@Autowired
	@Bean(name = "userLoginR2Repository")
	public UserLoginRepository getUserLoginR2Repository() {
		UserLoginR2RepositoryImpl loginR2RepositoryImpl = new UserLoginR2RepositoryImpl();
		loginR2RepositoryImpl.setRegionProperties(applicationConfig.getVtex().getR2());
		loginR2RepositoryImpl.setJanis(applicationConfig.getJanisProperties());
		return loginR2RepositoryImpl;
	}
	
}
