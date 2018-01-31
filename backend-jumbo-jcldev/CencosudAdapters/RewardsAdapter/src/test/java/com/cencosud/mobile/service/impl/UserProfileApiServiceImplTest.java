package com.cencosud.mobile.service.impl;

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

import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.service.UserProfileApiService;
import com.cencosud.mobile.service.model.UserProfileResponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ClientBuilder.class, Response.class })
public class UserProfileApiServiceImplTest {

	@InjectMocks
	private UserProfileApiService userProfileApiService = new UserProfileApiServiceImpl();

	@Test
	public void rewardsGet() throws NotFoundException {
		UserProfileResponse userProfileResponse = new UserProfileResponse();
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
		Mockito.doReturn(userProfileResponse).when(response).readEntity(String.class);
		userProfileApiService.profileGet("1", "r1", "v2");
	}

}
