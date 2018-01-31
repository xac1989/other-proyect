package com.cencosud.middleware.register.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cencosud.middleware.register.configuration.ApplicationConfig.VTexProperties;
import com.cencosud.middleware.register.util.VtexServiceUtil;

/**
 * 
 * 
 * <h1>WebApplication</h1>
 * <p>
 * Configuración inicial de springboot y de la aplicación.
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Mar 22, 2017
 */
@SpringBootApplication()
@ComponentScan("com.cencosud.middleware.register")
@EnableCaching
@EnableScheduling
public class WebApplication extends SpringBootServletInitializer{
	@Autowired
	private ApplicationConfig applicationConfig;


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		return application.sources(WebApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(WebApplication.class, args);
	}

	@Bean(name = "vTexService")
	public VtexServiceUtil getVTexService() {
		VTexProperties vTex = applicationConfig.getVtex();
		VtexServiceUtil clientService = new VtexServiceUtil(vTex.getUtil().getUrl(), vTex.getUtil().getPort(), vTex.getUtil().getApiKey(),
				vTex.getUtil().getApiSecret(), vTex.getUtil().getSchema());
		return clientService;
	}
	
	@Bean(name = "vTexProfile")
	public VtexServiceUtil getVTexProfile() {
		VTexProperties vTex = applicationConfig.getVtexProfile();
		VtexServiceUtil clientService = new VtexServiceUtil(vTex.getUtil().getUrl(), vTex.getUtil().getPort(), vTex.getUtil().getApiKey(),
				vTex.getUtil().getApiSecret(), vTex.getUtil().getSchema());
		return clientService;
	}
}
