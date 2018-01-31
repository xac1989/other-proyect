package com.cencosud.middleware.order.repository.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.middleware.order.client.VtexClient;
import com.cencosud.middleware.order.exception.OrderServiceException;
import com.cencosud.middleware.order.repository.OrderVtexRepository;

@RunWith(MockitoJUnitRunner.class)
public class OrderVtexR1RepositoryImplTest {

	@InjectMocks
	OrderVtexRepository orderVtexRepository = new OrderVtexR1RepositoryImpl();

	@Mock
	private VtexClient client;

	final static String EMAIL = "jumbotesting@gmail.com";
	final static String ORDER_ID = "v10009544jmqa-01";

	@Test(expected = UnsupportedOperationException.class)
	public void getVtexOrderList() throws OrderServiceException {
		orderVtexRepository.getVtexOrderList(EMAIL);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void getVtexFullOrder() throws OrderServiceException {
		orderVtexRepository.getVtexFullOrder(ORDER_ID);
	}

}
