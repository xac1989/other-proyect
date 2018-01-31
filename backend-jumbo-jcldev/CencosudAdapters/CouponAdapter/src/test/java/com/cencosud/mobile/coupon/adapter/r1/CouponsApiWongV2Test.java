package com.cencosud.mobile.coupon.adapter.r1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.mobile.coupon.adapter.CouponsApiService;
import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.service.UserProfileApiService;
import com.cencosud.mobile.service.model.UserProfile;
import com.ibm.mfp.server.registration.external.model.AuthenticatedUser;
import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;

@RunWith(MockitoJUnitRunner.class)
public class CouponsApiWongV2Test {

	@InjectMocks
	private CouponsApiWongV2 couponsApiWongV2;

	@Mock
	private CouponsApiService couponsApiService;

	@Mock
	private UserProfileApiService userProfileApiService;

	@Mock
	private AdapterSecurityContext adapterSecurityContext;

	String email = "test@test.com";

	@Test
	public void getCouponsByDni() throws NotFoundException, IOException {
		Response response = Mockito.mock(Response.class);
		Mockito.doReturn(response).when(couponsApiService).couponsGet(Mockito.anyString(), Mockito.anyString(),
				Mockito.anyString());
		couponsApiWongV2.getCouponsByDni("12345678");
	}

	@Test
	public void getCouponsByUserWithSession() throws NotFoundException, IOException {

		AuthenticatedUser authenticatedUser = Mockito.mock(AuthenticatedUser.class);
		Response response = Mockito.mock(Response.class);
		Mockito.doReturn(authenticatedUser).when(adapterSecurityContext).getAuthenticatedUser();
		Map<String, Object> atributes = new HashMap<String, Object>();
		atributes.put("email", email);
		Mockito.doReturn(atributes).when(authenticatedUser).getAttributes();
		Mockito.doReturn(response).when(couponsApiService).couponsGet(Mockito.anyString(), Mockito.anyString(),
				Mockito.anyString());
		UserProfile userProfile = new UserProfile();
		userProfile.setDocument("12345678");
		Mockito.doReturn(userProfile).when(userProfileApiService).profileGet(Mockito.anyString());

		couponsApiWongV2.getCouponsByUser();

	}

	@Test
	public void getCouponsByUserSuccess() throws NotFoundException, IOException {

		AuthenticatedUser authenticatedUser = Mockito.mock(AuthenticatedUser.class);
		Response response = Mockito.mock(Response.class);
		Mockito.doReturn(authenticatedUser).when(adapterSecurityContext).getAuthenticatedUser();
		Map<String, Object> atributes = new HashMap<String, Object>();
		atributes.put("email", email);
		Mockito.doReturn(atributes).when(authenticatedUser).getAttributes();
		Mockito.doReturn(response).when(couponsApiService).couponsGet(Mockito.anyString(), Mockito.anyString(),
				Mockito.anyString());
		UserProfile userProfile = new UserProfile();
		userProfile.setDocument("12345678");
		Mockito.doReturn(userProfile).when(userProfileApiService).profileGet(Mockito.anyString());

		couponsApiWongV2.getCouponsByUser();

	}

	@Test(expected = NotFoundException.class)
	public void getExceptionCouponsByUserWithEmailNull() throws NotFoundException, IOException {

		AuthenticatedUser authenticatedUser = Mockito.mock(AuthenticatedUser.class);
		Response response = Mockito.mock(Response.class);
		Mockito.doReturn(authenticatedUser).when(adapterSecurityContext).getAuthenticatedUser();
		Map<String, Object> atributes = new HashMap<String, Object>();
		atributes.put("email", null);
		Mockito.doReturn(atributes).when(authenticatedUser).getAttributes();
		Mockito.doReturn(response).when(couponsApiService).couponsGet(Mockito.anyString(), Mockito.anyString(),
				Mockito.anyString());
		UserProfile userProfile = new UserProfile();
		userProfile.setDocument("12345678");
		Mockito.doReturn(userProfile).when(userProfileApiService).profileGet(Mockito.anyString());

		couponsApiWongV2.getCouponsByUser();

	}
	
	@Test(expected = NotFoundException.class)
	public void getExceptionCouponsByUserWithEmailEmpty() throws NotFoundException, IOException {

		AuthenticatedUser authenticatedUser = Mockito.mock(AuthenticatedUser.class);
		Response response = Mockito.mock(Response.class);
		Mockito.doReturn(authenticatedUser).when(adapterSecurityContext).getAuthenticatedUser();
		Map<String, Object> atributes = new HashMap<String, Object>();
		atributes.put("email", "");
		Mockito.doReturn(atributes).when(authenticatedUser).getAttributes();
		Mockito.doReturn(response).when(couponsApiService).couponsGet(Mockito.anyString(), Mockito.anyString(),
				Mockito.anyString());
		UserProfile userProfile = new UserProfile();
		userProfile.setDocument("12345678");
		Mockito.doReturn(userProfile).when(userProfileApiService).profileGet(Mockito.anyString());

		couponsApiWongV2.getCouponsByUser();

	}
}
