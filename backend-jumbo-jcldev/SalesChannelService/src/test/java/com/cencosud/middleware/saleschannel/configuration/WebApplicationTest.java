package com.cencosud.middleware.saleschannel.configuration;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.middleware.saleschannel.client.VtexClient;
import com.cencosud.middleware.saleschannel.configuration.ApplicationConfig.VTexProperties;

@RunWith(MockitoJUnitRunner.class)
public class WebApplicationTest {

	@InjectMocks
	private WebApplication webApplication;

	@Mock
	private ApplicationConfig applicationConfig;

	@Test
	public void getVTexEntitiesClient() {
		VTexProperties vTexProperties = new VTexProperties();
		String schema = "http";
		int port = 80;
		String url = "api.vtex.com/jumbochilehomolog";
		String apiKey = "ignacio.contreras.cencosud@gmail.com";
		String apiSecret = "8P9HJ8Sr";

		vTexProperties.setSchema(schema);
		vTexProperties.setPort(port);
		vTexProperties.setUrl(url);
		vTexProperties.setApiKey(apiKey);
		vTexProperties.setApiSecret(apiSecret);

		Mockito.doReturn(vTexProperties).when(applicationConfig).getVtexEntities();
		VtexClient vtexClient = webApplication.vTexEntitiesClient();
		Assert.assertThat(vtexClient.getConnectionUrl(), Matchers.equalTo("api.vtex.com/jumbochilehomolog"));
		Assert.assertThat(vtexClient.getSchema(), Matchers.equalTo(schema));
		Assert.assertThat(vtexClient.getPort(), Matchers.equalTo(port));
		Assert.assertThat(vtexClient.getApiKey(), Matchers.equalTo(apiKey));
		Assert.assertThat(vtexClient.getSecretKey(), Matchers.equalTo(apiSecret));

	}
}
