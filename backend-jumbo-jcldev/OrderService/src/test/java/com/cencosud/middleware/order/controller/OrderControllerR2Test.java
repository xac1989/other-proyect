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

import com.cencosud.middleware.order.client.dto.VtexFullOrder;
import com.cencosud.middleware.order.client.dto.VtexOrder;
import com.cencosud.middleware.order.exception.OrderServiceException;
import com.cencosud.middleware.order.factory.OrderServiceFactory;
import com.cencosud.middleware.order.mapper.OrderMapper;
import com.cencosud.middleware.order.model.JumboOrder;
import com.cencosud.middleware.order.model.JumboOrderDetail;
import com.cencosud.middleware.order.service.OrderService;
import com.cencosud.middleware.order.service.impl.OrderR2ServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerR2Test {

	@InjectMocks
	OrderControllerR2 orderControllerR2 = new OrderControllerR2();

	@Mock
	OrderServiceFactory orderServiceFactory = new OrderServiceFactory();

	@Mock
	OrderService orderService = new OrderR2ServiceImpl();

	@Mock
	OrderMapper orderMapper;

	final static String EMAIL = "jumbotesting@gmail.com";
	final static String ORDER_ID = "v10009544jmqa-01";

	@Test
	public void getOrderListWithEmail() throws OrderServiceException {
		getOrderListPreConditions();

		List<JumboOrder> orderList = orderControllerR2.getOrders(EMAIL);
		Assert.assertThat(orderList, Matchers.notNullValue());
	}

	@Test(expected = OrderServiceException.class)
	public void getOrderListWithoutEmail() throws OrderServiceException {
		getOrderListPreConditions();
		orderControllerR2.getOrders(null);
	}

	@Test
	public void getOrderDetail() throws OrderServiceException {
		getOrderDetailPreConditions();

		JumboOrderDetail jumboOrderDetail = orderControllerR2.getOrder(ORDER_ID);
		Assert.assertThat(jumboOrderDetail, Matchers.notNullValue());
	}

	private void getOrderDetailPreConditions() throws OrderServiceException {
		VtexFullOrder vtexFullOrder = new VtexFullOrder();
		Mockito.doReturn(vtexFullOrder).when(orderService).getVtexFullOrder(ORDER_ID);
		Mockito.doReturn(orderService).when(orderServiceFactory).getService(Mockito.anyString());

		JumboOrderDetail jumboOrderDetail = new JumboOrderDetail();
		Mockito.doReturn(jumboOrderDetail).when(orderMapper).getOrderDetail(Mockito.any(VtexFullOrder.class));
	}

	private void getOrderListPreConditions() throws OrderServiceException {
		List<VtexOrder> vtexOrderList = new ArrayList<>();
		Mockito.doReturn(vtexOrderList).when(orderService).getVtexOrderList(Mockito.anyString());
		Mockito.doReturn(orderService).when(orderServiceFactory).getService(Mockito.anyString());
		List<JumboOrder> orderList = new ArrayList<>();
		Mockito.doReturn(orderList).when(orderMapper).getOrderList(Mockito.anyListOf(VtexOrder.class));

	}

}
