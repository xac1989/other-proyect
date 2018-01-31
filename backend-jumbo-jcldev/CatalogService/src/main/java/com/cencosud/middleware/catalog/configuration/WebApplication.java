package com.cencosud.middleware.catalog.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.cencosud.middleware.catalog.client.VtexClient;
import com.cencosud.middleware.catalog.configuration.ApplicationConfig.VTexProperties;
import com.cencosud.middleware.catalog.repository.CatalogRepository;
import com.cencosud.middleware.catalog.repository.impl.CatalogVtexR1RepositoryImpl;
import com.cencosud.middleware.catalog.repository.impl.CatalogVtexR2RepositoryImpl;
import com.cencosud.middleware.catalog.util.VtexServiceUtil;

@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
@ComponentScan("com.cencosud.middleware.catalog")
@EnableCaching
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
		VtexClient clientR1 = new VtexClient(vTex.getR1().getUrl(), vTex.getR1().getPort(), vTex.getR1().getApiKey(),
				vTex.getR1().getApiSecret(), vTex.getR1().getSchema());
		return clientR1;
	}

	@Bean(name = "vTexClientR2")
	public VtexClient getVTexClientR2() {
		VTexProperties vTex = applicationConfig.getVtex();
		VtexClient clientR2 = new VtexClient(vTex.getR2().getUrl(), vTex.getR2().getPort(), vTex.getR2().getApiKey(),
				vTex.getR2().getApiSecret(), vTex.getR2().getSchema());
		return clientR2;
	}

	@Autowired
	@Bean(name = "catalogVtexRepositoryR1")
	public CatalogRepository getCatalogVtexRepositoryR1(@Qualifier("vTexClientR1") VtexClient vtexClient) {
		CatalogVtexR1RepositoryImpl repoR1 = new CatalogVtexR1RepositoryImpl();
		repoR1.setClient(vtexClient);
		return repoR1;
	}

	@Autowired
	@Bean(name = "catalogVtexRepositoryR2")
	public CatalogRepository getCatalogVtexRepositoryR2(@Qualifier("vTexClientR2") VtexClient vtexClient) {
		CatalogVtexR2RepositoryImpl repoR2 = new CatalogVtexR2RepositoryImpl();
		repoR2.setClient(vtexClient);
		return repoR2;
	}
	
	@Bean(name = "vTexService")
	public VtexServiceUtil getVTexService() {
		VTexProperties vTex = applicationConfig.getVtex();
		VtexServiceUtil clientService = new VtexServiceUtil(vTex.getUtil().getUrl(), vTex.getUtil().getPort(), vTex.getUtil().getApiKey(),
				vTex.getUtil().getApiSecret(), vTex.getUtil().getSchema());
		return clientService;
	}
}
