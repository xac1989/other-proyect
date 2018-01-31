package com.cencosud.mobile.order.adapter;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.anyString;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.mobile.order.adapter.r1.OrderApiWongV2;
import com.cencosud.mobile.order.service.OrderApiServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class OrderApiTest {

	@InjectMocks
	OrderApiWongV2 orderApi = new OrderApiWongV2();

	@Mock
	OrderApiServiceImpl orderApiService = new OrderApiServiceImpl();

	@Test
	public void getOrderByIdR1() throws Exception {
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Response response = Mockito.mock(Response.class);
		Mockito.doReturn(response).when(orderApiService).getOrderById(anyString(), anyString(),anyString());

		Response finalResponse = orderApi.getOrderById("orderId", securityContext);
		Assert.assertThat(finalResponse, equalTo(response));
	}

	@Test
	public void getOrderByIdR2() throws Exception {
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Response response = Mockito.mock(Response.class);
		Mockito.doReturn(response).when(orderApiService).getOrderById(anyString(), anyString(), anyString(), anyString());

		Response finalResponse = orderApi.getOrderById("orderId", "1", securityContext);
		Assert.assertThat(finalResponse, equalTo(response));
	}

	@Test
	public void findOrders() throws Exception {
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Response response = Mockito.mock(Response.class);
		Mockito.doReturn(response).when(orderApiService).findOrders(anyString(), anyString(),anyString());

		Response finalResponse = orderApi.orderSearchGet("orderId", securityContext);
		Assert.assertThat(finalResponse, equalTo(response));
	}

}
