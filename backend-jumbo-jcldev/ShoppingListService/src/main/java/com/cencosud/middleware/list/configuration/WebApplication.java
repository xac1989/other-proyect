package com.cencosud.middleware.list.configuration;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.AsyncRestOperations;
import org.springframework.web.client.AsyncRestTemplate;

import com.cencosud.common.util.rest.RestClient;
import com.cencosud.middleware.list.configuration.ApplicationConfig.VTexProperties;

@SpringBootApplication(exclude = { MessageSourceAutoConfiguration.class })
@ComponentScan("com.cencosud.middleware.list")
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

	@Bean(name = "vTexClient")
	public RestClient getVTexClient(@Autowired AsyncRestOperations asyncRestTemplate) {
		VTexProperties vTex = applicationConfig.getVtex();
		Map<String, String> principalHeaders = new HashMap<>(2);
		principalHeaders.put("X-VTEX-API-AppKey", vTex.getApiKey());
		principalHeaders.put("X-VTEX-API-AppToken", vTex.getApiSecret());
		RestClient client = new RestClient(vTex.getUrl(), vTex.getPort(), vTex.getSchema(), principalHeaders,
				asyncRestTemplate);
		return client;
	}
	
	@Bean(name = "asyncRestTemplate")
	public AsyncRestTemplate getAsyncRestTemplate() {
		AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>(3);
		messageConverters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		messageConverters.add(new SourceHttpMessageConverter<>());
		asyncRestTemplate.setMessageConverters(messageConverters);
		return asyncRestTemplate;
	}
}
