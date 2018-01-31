package com.cencosud.middleware.saleschannel.repository.impl;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.middleware.saleschannel.client.VtexClient;
import com.cencosud.middleware.saleschannel.model.StoresInfo;

@RunWith(MockitoJUnitRunner.class)
public class SalesChannelRepositoryImplTest {

	@InjectMocks
	SalesChannelRepositoryImpl salesChannelRepository;

	@Mock(name = "entitiesClient")
	VtexClient entitiesClient;

	@Mock(name = "client")
	VtexClient client;

	@Test
	public void getStoresInfo() {
		Mockito.doReturn(new StoresInfo()).when(entitiesClient).executeService(Mockito.anyString(), Mockito.anyObject(),
				Mockito.anyObject(), Mockito.anyObject());

		StoresInfo storesInfo = salesChannelRepository.getStoresInfo();
		Assert.assertThat(storesInfo, Matchers.notNullValue());
	}

}
