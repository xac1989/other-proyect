package com.cencosud.middleware.order.configuration;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import com.cencosud.middleware.order.configuration.ApplicationConfig.VTexClientProperties;
import com.cencosud.middleware.order.configuration.ApplicationConfig.VTexProperties;

public class ApplicationConfigR2Test {

	private static final String PAYMENT_TICKET_URL = "https://escritorio.acepta.com/portalboletas/buscarboletaindex.php?empresa=81201000-K";
	private static final String API_KEY = "ignacio.contreras.cencosud@gmail.com";
	private static final String API_SECRET = "8P9HJ8Sr";
	private static final int PORT = 80;
	private static final String URL = "jumbochilehomolog.vtexcommercestable.com.br";
	private static final String SCHEMA = "http";
	private VTexProperties vtexProperties;

	@Before
	public void setUp() {
		vtexProperties = createVtexProperties();
	}

	@Test
	public void getVTexPropertiesR2() {
		VTexClientProperties vTexClientProperties = vtexProperties.getR2();
		assertThat(vTexClientProperties.getSchema(), is(SCHEMA));
		assertThat(vTexClientProperties.getUrl(), is(URL));
		assertThat(vTexClientProperties.getPort(), is(PORT));
		assertThat(vTexClientProperties.getApiKey(), is(API_KEY));
		assertThat(vTexClientProperties.getApiSecret(), is(API_SECRET));
		assertThat(vTexClientProperties.getPaymentTicketUrl(), is(PAYMENT_TICKET_URL));
	}

	private VTexProperties createVtexProperties() {
		VTexProperties vtexProperties = new VTexProperties();
		VTexClientProperties vtexClientR2 = new VTexClientProperties();
		vtexClientR2.setSchema(SCHEMA);
		vtexClientR2.setUrl(URL);
		vtexClientR2.setPort(PORT);
		vtexClientR2.setApiKey(API_KEY);
		vtexClientR2.setApiSecret(API_SECRET);
		vtexClientR2.setPaymentTicketUrl(PAYMENT_TICKET_URL);
		vtexProperties = new VTexProperties();

		vtexProperties.setR2(vtexClientR2);

		return vtexProperties;
	}

}