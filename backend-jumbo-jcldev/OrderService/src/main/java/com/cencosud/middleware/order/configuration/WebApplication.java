package com.cencosud.middleware.order.configuration;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

import com.cencosud.middleware.order.client.VtexClient;
import com.cencosud.middleware.order.configuration.ApplicationConfig.VTexProperties;
import com.cencosud.middleware.order.repository.OrderVtexRepository;
import com.cencosud.middleware.order.repository.impl.OrderVtexR1RepositoryImpl;
import com.cencosud.middleware.order.repository.impl.OrderVtexR2RepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@SpringBootApplication
@ComponentScan("com.cencosud.middleware.order")
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
	
	@Bean(name = "vTexClientR1")
	public VtexClient getVTexClientR1() {
		VTexProperties vTex = applicationConfig.getVtex();
		return new VtexClient(vTex.getR1().getUrl(), vTex.getR1().getPort(), vTex.getR1().getApiKey(),
				vTex.getR1().getApiSecret(), vTex.getR1().getSchema());
	}

	@Bean(name = "vTexClientR2")
	public VtexClient getVTexClientR2() {
		VTexProperties vTex = applicationConfig.getVtex();
		return new VtexClient(vTex.getR2().getUrl(), vTex.getR2().getPort(), vTex.getR2().getApiKey(),
				vTex.getR2().getApiSecret(), vTex.getR2().getSchema());
	}
	
	@Autowired
	@Bean(name = "orderVtexRepositoryR1")
	public OrderVtexRepository getOrderVtexRepositoryR1(@Qualifier("vTexClientR1") VtexClient vtexClient) {
		OrderVtexR1RepositoryImpl repoR1 = new OrderVtexR1RepositoryImpl();
		repoR1.setClient(vtexClient);
		return repoR1;
	}

	@Autowired
	@Bean(name = "orderVtexRepositoryR2")
	public OrderVtexRepository getOrderVtexRepositoryR2(@Qualifier("vTexClientR2") VtexClient vtexClient) {
		OrderVtexR2RepositoryImpl repoR2 = new OrderVtexR2RepositoryImpl();
		repoR2.setClient(vtexClient);
		return repoR2;
	}

	@Bean
	@Primary
	public ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();

		SimpleModule module = new SimpleModule();
		module.addSerializer(BigDecimal.class, ToStringSerializer.instance);
		mapper.registerModule(module);
		return mapper;
	}
}
