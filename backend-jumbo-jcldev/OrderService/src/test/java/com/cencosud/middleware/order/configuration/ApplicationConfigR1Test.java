package com.cencosud.middleware.order.configuration;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import com.cencosud.middleware.order.configuration.ApplicationConfig.VTexClientProperties;
import com.cencosud.middleware.order.configuration.ApplicationConfig.VTexProperties;

public class ApplicationConfigR1Test {

	private static final String API_KEY = "gabriel.rodriguez@globant.com";
	private static final String API_SECRET = "Cencodev1";
	private static final int PORT = 80;
	private static final String URL = "wongqa.vtexcommercestable.com.br";
	private static final String SCHEMA = "http";
	private VTexProperties vtexProperties;

	@Before
	public void setUp() {
		vtexProperties = createVtexProperties();
	}

	@Test
	public void getVTexPropertiesR1() {
		VTexClientProperties vTexClientProperties = vtexProperties.getR1();
		assertThat(vTexClientProperties.getSchema(), is(SCHEMA));
		assertThat(vTexClientProperties.getUrl(), is(URL));
		assertThat(vTexClientProperties.getPort(), is(PORT));
		assertThat(vTexClientProperties.getApiKey(), is(API_KEY));
		assertThat(vTexClientProperties.getApiSecret(), is(API_SECRET));
	}

	private VTexProperties createVtexProperties() {
		VTexProperties vtexProperties = new VTexProperties();
		VTexClientProperties vtexClientR1 = new VTexClientProperties();
		vtexClientR1.setSchema(SCHEMA);
		vtexClientR1.setUrl(URL);
		vtexClientR1.setPort(PORT);
		vtexClientR1.setApiKey(API_KEY);
		vtexClientR1.setApiSecret(API_SECRET);
		vtexProperties = new VTexProperties();

		vtexProperties.setR1(vtexClientR1);

		return vtexProperties;
	}

}