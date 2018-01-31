package com.cencosud.middleware.order.repository.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;

import org.apache.http.NameValuePair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.middleware.order.client.VtexClient;
import com.cencosud.middleware.order.client.dto.VtexFullOrder;
import com.cencosud.middleware.order.client.dto.VtexOrderList;
import com.cencosud.middleware.order.exception.OrderServiceException;
import com.cencosud.middleware.order.model.enums.RequestProtocolEnum;
import com.cencosud.middleware.order.repository.OrderVtexRepository;
import com.fasterxml.jackson.core.type.TypeReference;

@RunWith(MockitoJUnitRunner.class)
public class OrderVtexR2RepositoryImplTest {

	@InjectMocks
	OrderVtexRepository orderVtexRepository = new OrderVtexR2RepositoryImpl();

	@Mock
	private VtexClient client;

	final static String EMAIL = "jumbotesting@gmail.com";
	final static String ORDER_ID = "v10009544jmqa-01";

	private VtexOrderList vtexOrderList;
	private VtexFullOrder vtexFullOrder;

	@Before
	public void setUp() {
		vtexOrderList = createVtexOrderList();
		vtexFullOrder = createVtexFullOrder();
	}

	private VtexFullOrder createVtexFullOrder() {
		VtexFullOrder vtexFullOrder = new VtexFullOrder();
		return vtexFullOrder;
	}

	private VtexOrderList createVtexOrderList() {
		VtexOrderList vtexOrderList = new VtexOrderList();

		return vtexOrderList;
	}

	@Test
	public void getVtexOrderList() throws OrderServiceException {
		getVtextOrderListPreconditions();
		VtexOrderList vtexOrderList = orderVtexRepository.getVtexOrderList(EMAIL);
		assertTrue(vtexOrderList != null);
	}

	@Test
	public void getVtexFullOrder() throws OrderServiceException {
		getVtexFullOrderPreconditions();
		VtexFullOrder vtexFullOrder = orderVtexRepository.getVtexFullOrder(ORDER_ID);
		assertTrue(vtexFullOrder != null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void getAllUserOrders() throws OrderServiceException {
		orderVtexRepository.getAllUserOrders(EMAIL);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void getOrder() throws OrderServiceException {
		orderVtexRepository.getOrder(ORDER_ID);
	}

	private void getVtexFullOrderPreconditions() throws OrderServiceException {
		doReturn(vtexFullOrder).when(client).execute(anyString(), Mockito.anyListOf(NameValuePair.class), Mockito.any(RequestProtocolEnum.class), Matchers.<TypeReference<VtexOrderList>>any());
	}

	private void getVtextOrderListPreconditions() throws OrderServiceException {
		doReturn(vtexOrderList).when(client).execute(anyString(), Mockito.anyListOf(NameValuePair.class), Mockito.any(RequestProtocolEnum.class), Matchers.<TypeReference<VtexFullOrder>>any());
	}
}
