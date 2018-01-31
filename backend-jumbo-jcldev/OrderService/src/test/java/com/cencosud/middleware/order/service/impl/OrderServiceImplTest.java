package com.cencosud.middleware.order.service.impl;


import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.middleware.order.exception.OrderServiceException;
import com.cencosud.middleware.order.model.Item;
import com.cencosud.middleware.order.model.ItemDetail;
import com.cencosud.middleware.order.model.Order;
import com.cencosud.middleware.order.model.OrderDetail;
import com.cencosud.middleware.order.model.PaymentDetail;
import com.cencosud.middleware.order.model.PaymentTotal;
import com.cencosud.middleware.order.model.ShippingDetail;
import com.cencosud.middleware.order.repository.OrderVtexRepository;
import com.cencosud.middleware.order.service.OrderService;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

	@InjectMocks
	OrderService orderService = new OrderR1ServiceImpl();

	@Mock
	private OrderVtexRepository vtexRepo;

	List<Order> orders;
	OrderDetail order;
	
	final static String EMAIL = "wongtesting123@gmail.com";
	final static String ORDER_ID = "v503488wgqa-01";
	

	@Before
	public void setUp() {
		orders = createOrders();
		order = createOrder();
	}
	
	@Test
	public void getOrder() throws OrderServiceException{
		
		getOrderPreconditions();
		OrderDetail orderResponse = orderService.getOrder(ORDER_ID);
		assertThat(orderResponse , notNullValue());
	}
	
	@Test
	public void getAllUsersOrders() throws OrderServiceException{
		
		getAllUsersOrdersPreconditions();
		List<Order> ordersResponse = orderService.getAllUserOrders(EMAIL);
		assertThat(ordersResponse , notNullValue());
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void getVtexOrderList() throws OrderServiceException {
		orderService.getVtexOrderList(EMAIL);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void getVtexFullOrder() throws OrderServiceException {
		orderService.getVtexFullOrder(ORDER_ID);
	}
	
	@Test
	public void getRegion() {
		String region = orderService.getRegionId();
		assertThat(region, is("r1"));
	}

	private void getAllUsersOrdersPreconditions() throws OrderServiceException{
		given(vtexRepo.getAllUserOrders(EMAIL)).willReturn(orders);
	}
	
	private void getOrderPreconditions() throws OrderServiceException{
		given(vtexRepo.getOrder(ORDER_ID)).willReturn(order);
	}
	
	private OrderDetail createOrder(){
		
		List<Item> items = new ArrayList<>();
		Item item = new Item();
		item.setProductId("2000110");
		item.setName("Thomas Licuadora TH-740V 1.3 L");
		item.setPrice(new BigDecimal(200));
		item.setImage("http://wongqa.vteximg.com.br/arquivos/ids/156644-55-55/licuadora-thomas-th-740V-1-3-litros-vaso-vidrio-6-velocidades-wong-392782.jpg");
		items.add(item);
		
		ShippingDetail shippingDetail = new ShippingDetail("Wong Testing", "150101", "Lima", "LIMA", "PER", "Necochea", "826");
		
		List<PaymentDetail> paymentDetails = new ArrayList<>();
		PaymentDetail payment = new PaymentDetail("Cash", new BigDecimal(217));
		paymentDetails.add(payment); 
		
		List<PaymentTotal> totals = new ArrayList<>();
		PaymentTotal paymentTot = new PaymentTotal("Items", "Total de los itens", new BigDecimal(200));
		totals.add(paymentTot);
		paymentTot = new PaymentTotal("Discounts", "Total de descuentos", new BigDecimal(0));
		totals.add(paymentTot);
		paymentTot = new PaymentTotal("Shipping", "Costo total del envío", new BigDecimal(17));
		totals.add(paymentTot);
		paymentTot = new PaymentTotal("Tax", "Costo total del cambio", new BigDecimal(0));
		totals.add(paymentTot);
		
		List<ItemDetail> itemsDetail = new ArrayList<>();
		ItemDetail itemDetail = new ItemDetail("2000110","Thomas Licuadora TH-740V 1.3 L",
				"http://wongqa.vteximg.com.br/arquivos/ids/156644-55-55/licuadora-thomas-th-740V-1-3-litros-vaso-vidrio-6-velocidades-wong-392782.jpg",
				new BigDecimal(200), 1);
		itemsDetail.add(itemDetail);
		
		OrderDetail order = new OrderDetail("v503488wgqa-01","2017-03-05T00:02:28.3323732+00:00", shippingDetail, paymentDetails, totals, itemsDetail);
		
		return order;
	}
	
	private List<Order> createOrders(){
		List<Item> items = new ArrayList<>();
		Item item = new Item();
		item.setProductId("2000589");
		item.setName("PARRILLA CAMPING/ARAGUAIA MOR 3007");
		item.setPrice(new BigDecimal(49));
		item.setImage("null");
		items.add(item);
		
		item = new Item();
		item.setProductId("2000590");
		item.setName("ALMOHADA 10X10  PINK");
		item.setPrice(new BigDecimal(14));
		item.setImage("null");
		items.add(item);
		
		List<Order> orders = new ArrayList<>();
		Order order = new Order();
		order.setId("v504353wgqa-01");
		order.setDate("2017-05-02T15:26:59.0000000+00:00");
		order.setItems(items);
		
		return orders;
	}
}
