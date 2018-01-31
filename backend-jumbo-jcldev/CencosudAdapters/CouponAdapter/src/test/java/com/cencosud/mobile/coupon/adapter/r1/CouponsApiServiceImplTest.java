package com.cencosud.mobile.coupon.adapter.r1;


import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.cencosud.mobile.coupon.adapter.CouponsApiService;
import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.service.impl.CouponsApiServiceImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ClientBuilder.class, Response.class })
public class CouponsApiServiceImplTest {

	@InjectMocks
	private CouponsApiService rewardsApiService = new CouponsApiServiceImpl();

	@Test
	public void couponsGet() throws NotFoundException {
		String couponsResponse = new String();
		PowerMockito.mockStatic(ClientBuilder.class);
		Client client = Mockito.mock(Client.class);
		WebTarget webTarget = Mockito.mock(WebTarget.class);
		Invocation.Builder invocationBuilder = Mockito.mock(Invocation.Builder.class);
		Response response = Mockito.mock(Response.class);

		Mockito.when(ClientBuilder.newClient()).thenReturn(client);
		Mockito.doReturn(webTarget).when(client).target(anyString());
		Mockito.doReturn(webTarget).when(webTarget).path(anyString());
		Mockito.doReturn(webTarget).when(webTarget).queryParam(anyString(), anyObject());
		Mockito.doReturn(invocationBuilder).when(webTarget).request(anyString());
		Mockito.doReturn(response).when(invocationBuilder).get();
		Mockito.doReturn(couponsResponse).when(response).readEntity(String.class);
		rewardsApiService.couponsGet("12345678", "r1", "v2");
	}

}
