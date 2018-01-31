package com.cencosud.mobile.rewards.adapter.r1;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.rewards.adapter.RewardsApiService;
import com.cencosud.mobile.service.UserProfileApiService;
import com.cencosud.mobile.service.impl.RewardsApiServiceImpl;
import com.cencosud.mobile.service.impl.UserProfileApiServiceImpl;
import com.cencosud.mobile.service.model.UserProfile;
import com.ibm.mfp.server.registration.external.model.AuthenticatedUser;
import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;

@RunWith(MockitoJUnitRunner.class)
public class RewardsApiWongV2Test {

	@InjectMocks
	RewardsApiWongV2 rewardsApiWongV2 = new RewardsApiWongV2();

	@Mock
	UserProfileApiService userProfileApiService = new UserProfileApiServiceImpl();

	@Mock
	RewardsApiService rewardsApiService = new RewardsApiServiceImpl();

	String email = "test@test.com";

	@Test
	public void rewardsGetWithEmailSession() throws NotFoundException {
		AdapterSecurityContext adapterSecurityContext = Mockito.mock(AdapterSecurityContext.class);
		AuthenticatedUser authenticatedUser = Mockito.mock(AuthenticatedUser.class);
		Response response = Mockito.mock(Response.class);
		Mockito.doReturn(authenticatedUser).when(adapterSecurityContext).getAuthenticatedUser();
		Map<String, Object> atributes = new HashMap<String, Object>();
		atributes.put("email", email);
		Mockito.doReturn(atributes).when(authenticatedUser).getAttributes();

		UserProfile userProfile = new UserProfile();
		userProfile.setDocument("12345678");
		Mockito.doReturn(userProfile).when(userProfileApiService).profileGet(Mockito.anyString(), Mockito.anyString(),
				Mockito.anyString());
		Mockito.doReturn(response).when(rewardsApiService).rewardsGet(Mockito.anyString(), Mockito.anyInt());

		rewardsApiWongV2.rewardsGet(email, adapterSecurityContext);
	}
	
	
	@Test
	public void rewardsGetWithOutEmailSession() throws NotFoundException {
		AdapterSecurityContext adapterSecurityContext = Mockito.mock(AdapterSecurityContext.class);
		AuthenticatedUser authenticatedUser = Mockito.mock(AuthenticatedUser.class);
		Response response = Mockito.mock(Response.class);
		Mockito.doReturn(authenticatedUser).when(adapterSecurityContext).getAuthenticatedUser();
		Map<String, Object> atributes = new HashMap<String, Object>();
		atributes.put("email", email);
		Mockito.doReturn(atributes).when(authenticatedUser).getAttributes();

		UserProfile userProfile = new UserProfile();
		userProfile.setDocument("12345678");
		Mockito.doReturn(userProfile).when(userProfileApiService).profileGet(Mockito.anyString(), Mockito.anyString(),
				Mockito.anyString());
		Mockito.doReturn(response).when(rewardsApiService).rewardsGet(Mockito.anyString(), Mockito.anyInt());

		rewardsApiWongV2.rewardsGet("", adapterSecurityContext);
	}
	
}
