package com.cencosud.mobile.order.util;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.cencosud.mobile.order.adapter.NotFoundException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ClientBuilder.class })
public class RestServiceUtilTest {

	RestServiceUtil restServiceUtil = new RestServiceUtil();

	@Before
	public void setUp() {
		restServiceUtil.setApiBaseUrl("");
		restServiceUtil.setApiClientId("");
		restServiceUtil.setApiSecret("");
	}

	@Test
	public void sucessResponse() throws NotFoundException {

		String responseExample = "orders";
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
		Mockito.doReturn(responseExample).when(response).readEntity(String.class);

		Invocation.Builder x = Mockito.mock(Invocation.Builder.class);
		Mockito.doReturn(response).when(x).get();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("email", "test@gmail.com");
		String orders = restServiceUtil.executeService("", String.class, parameters);
		Assert.assertThat(orders, containsString("orders"));
	}
	
	@Test
	public void notContentResponse() throws NotFoundException {
		PowerMockito.mockStatic(ClientBuilder.class);
		Client client = Mockito.mock(Client.class);
		WebTarget webTarget = Mockito.mock(WebTarget.class);
		Invocation.Builder invocationBuilder = Mockito.mock(Invocation.Builder.class);
		Response response = Mockito.mock(Response.class);

		Mockito.when(ClientBuilder.newClient()).thenReturn(client);
		Mockito.doReturn(webTarget).when(client).target(anyString());
		Mockito.doReturn(webTarget).when(webTarget).path(anyString());
		Mockito.doReturn(invocationBuilder).when(webTarget).request(anyString());
		Mockito.doReturn(response).when(invocationBuilder).get();
		Mockito.doReturn(204).when(response).getStatus();
		String orders = restServiceUtil.executeService("", String.class, new HashMap<String, Object>());
		Assert.assertThat(orders, nullValue());
		
	}
}
