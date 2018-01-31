package com.cencosud.middleware.order.service.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.middleware.order.client.dto.VtexFullOrder;
import com.cencosud.middleware.order.client.dto.VtexOrder;
import com.cencosud.middleware.order.client.dto.VtexOrderList;
import com.cencosud.middleware.order.exception.OrderServiceException;
import com.cencosud.middleware.order.repository.OrderVtexRepository;
import com.cencosud.middleware.order.service.OrderService;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceR2ImplTest {

	@InjectMocks
	OrderService orderService = new OrderR2ServiceImpl();

	@Mock
	private OrderVtexRepository vtexRepo;

	VtexOrderList vtexOrderList;
	VtexFullOrder vtexFullOrder;

	final static String EMAIL = "jumbotesting@gmail.com";
	final static String ORDER_ID = "v10009544jmqa-01";

	@Before
	public void setUp() {
		vtexOrderList = createVtexOrderList();
		vtexFullOrder = createVtexFullOrder();
	}

	@Test
	public void getOrder() throws OrderServiceException {

		getOrderPreconditions();
		VtexFullOrder orderResponse = orderService.getVtexFullOrder(ORDER_ID);
		assertTrue(orderResponse != null);
	}

	@Test
	public void getOrdersDetails() throws OrderServiceException {

		getOrderDetailsPreconditions();
		List<VtexOrder> ordersResponse = orderService.getVtexOrderList(EMAIL);
		assertTrue(ordersResponse != null);
	}

	@Test
	public void getRegion() {
		String region = orderService.getRegionId();
		assertTrue(region.equals("r2"));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void getAllUserOrders() throws OrderServiceException {
		orderService.getAllUserOrders(EMAIL);

	}

	@Test(expected = UnsupportedOperationException.class)
	public void getOrderException() throws OrderServiceException {
		orderService.getOrder(ORDER_ID);

	}

	private void getOrderDetailsPreconditions() throws OrderServiceException {
		given(vtexRepo.getVtexOrderList(EMAIL)).willReturn(vtexOrderList);
	}

	private void getOrderPreconditions() throws OrderServiceException {
		given(vtexRepo.getVtexFullOrder(ORDER_ID)).willReturn(vtexFullOrder);
	}

	private VtexOrderList createVtexOrderList() {
		List<VtexOrder> vtexOrders = new ArrayList<VtexOrder>(0);

		VtexOrderList vtexOrderList = new VtexOrderList();
		vtexOrderList.setList(vtexOrders);
		return vtexOrderList;
	}

	private VtexFullOrder createVtexFullOrder() {
		VtexFullOrder vtexFullOrder = new VtexFullOrder();
		return vtexFullOrder;
	}

}
