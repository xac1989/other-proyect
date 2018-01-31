package com.cencosud.middleware.order.controller;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.middleware.order.exception.OrderServiceException;
import com.cencosud.middleware.order.factory.OrderServiceFactory;
import com.cencosud.middleware.order.mapper.OrderMapper;
import com.cencosud.middleware.order.model.Order;
import com.cencosud.middleware.order.model.OrderDetail;
import com.cencosud.middleware.order.service.OrderService;
import com.cencosud.middleware.order.service.impl.OrderR1ServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerR1Test {

	@InjectMocks
	OrderController orderControllerR1 = new OrderController();

	@Mock
	OrderServiceFactory orderServiceFactory = new OrderServiceFactory();

	@Mock
	OrderService orderService = new OrderR1ServiceImpl();

	@Mock
	OrderMapper orderMapper;

	final static String EMAIL = "wongtesting@gmail.com";
	final static String ORDER_ID = "v10009544jmqa-01";

	@Test
	public void getOrderListWithEmail() throws OrderServiceException {
		getOrderListPreConditions();

		List<Order> orderList = orderControllerR1.getAllUserOrders(EMAIL);
		Assert.assertThat(orderList, Matchers.notNullValue());
	}

	@Test(expected = OrderServiceException.class)
	public void getOrderListWithoutEmail() throws OrderServiceException {
		getOrderListPreConditions();
		orderControllerR1.getAllUserOrders(null);
	}

	@Test
	public void getOrderDetail() throws OrderServiceException {
		getOrderDetailPreConditions();

		OrderDetail orderDetail = orderControllerR1.getOrder(ORDER_ID);
		Assert.assertThat(orderDetail, Matchers.notNullValue());
	}

	private void getOrderDetailPreConditions() throws OrderServiceException {
		OrderDetail orderDetail = new OrderDetail();
		Mockito.doReturn(orderDetail).when(orderService).getOrder(ORDER_ID);
		Mockito.doReturn(orderService).when(orderServiceFactory).getService(Mockito.anyString());

	}

	private void getOrderListPreConditions() throws OrderServiceException {
		List<Order> orderList = new ArrayList<>();
		Mockito.doReturn(orderList).when(orderService).getAllUserOrders(Mockito.anyString());
		Mockito.doReturn(orderService).when(orderServiceFactory).getService(Mockito.anyString());

	}

}
