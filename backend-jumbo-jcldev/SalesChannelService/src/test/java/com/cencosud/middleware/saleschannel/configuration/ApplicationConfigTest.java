package com.cencosud.middleware.saleschannel.configuration;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import com.cencosud.middleware.saleschannel.configuration.ApplicationConfig.SalesChannelInfo;
import com.cencosud.middleware.saleschannel.configuration.ApplicationConfig.VTexProperties;

public class ApplicationConfigTest {

	ApplicationConfig applicationConfig = new ApplicationConfig();

	@Test
	public void getSalesChannelInfo() {
		Map<Integer, SalesChannelInfo> salesChannelInfoMap = new HashMap<>();

		SalesChannelInfo salesChannelInfo = new SalesChannelInfo();
		salesChannelInfo.setAddress("Av Argentina");
		salesChannelInfo.setId(1);
		salesChannelInfoMap.put(1, salesChannelInfo);
		applicationConfig.setSalesChannelInfo(salesChannelInfoMap);
		Assert.assertThat(applicationConfig.getSalesChannelInfo().get(1).getAddress(),
				Matchers.equalTo("Av Argentina"));
		Assert.assertThat(applicationConfig.getSalesChannelInfo().get(1).getId(),
				Matchers.equalTo(salesChannelInfo.getId()));
	}

	@Test
	public void getVtexEntities() {
		VTexProperties vtexEntities = new VTexProperties();
		String schema = "http";
		int port = 80;
		String url = "api.vtex.com/jumbochilehomolog";
		String apiKey = "ignacio.contreras.cencosud@gmail.com";
		String apiSecret = "8P9HJ8Sr";

		vtexEntities.setSchema(schema);
		vtexEntities.setPort(port);
		vtexEntities.setUrl(url);
		vtexEntities.setApiKey(apiKey);
		vtexEntities.setApiSecret(apiSecret);
		applicationConfig.setVtexEntities(vtexEntities);
		Assert.assertThat(applicationConfig.getVtexEntities().getUrl(), Matchers.equalTo(vtexEntities.getUrl()));
		Assert.assertThat(applicationConfig.getVtexEntities().getSchema(), Matchers.equalTo(vtexEntities.getSchema()));
		Assert.assertThat(applicationConfig.getVtexEntities().getPort(), Matchers.equalTo(vtexEntities.getPort()));
		Assert.assertThat(applicationConfig.getVtexEntities().getApiKey(), Matchers.equalTo(vtexEntities.getApiKey()));
		Assert.assertThat(applicationConfig.getVtexEntities().getApiSecret(),
				Matchers.equalTo(vtexEntities.getApiSecret()));
	}
}
