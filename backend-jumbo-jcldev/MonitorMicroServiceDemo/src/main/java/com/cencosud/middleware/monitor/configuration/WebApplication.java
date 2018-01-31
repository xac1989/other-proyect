package com.cencosud.middleware.monitor.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan("com.cencosud.middleware.monitor")
public class WebApplication extends SpringBootServletInitializer {
	
//	@Autowired
//	private ApplicationConfig applicationConfig;
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebApplication.class, args);
    }
	
}
