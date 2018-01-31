package com.cencosud.middleware.coupon.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.cencosud.middleware.coupon.client.CencoCouponClient;
import com.cencosud.middleware.coupon.configuration.ApplicationConfig.VTexProperties;

@SpringBootApplication()
@ComponentScan("com.cencosud.middleware.coupon")
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
	
	@Bean
	public CencoCouponClient getVTexClient(){
		VTexProperties vTex = applicationConfig.getVtex();
		CencoCouponClient client = new CencoCouponClient(vTex.getSchema(), vTex.getTokenSchema(), vTex.getUrl(), vTex.getPort(), vTex.getTokenPort(), vTex.getGrantType(), vTex.getClientId(), vTex.getClientSecret());
		return client;
	}

}
