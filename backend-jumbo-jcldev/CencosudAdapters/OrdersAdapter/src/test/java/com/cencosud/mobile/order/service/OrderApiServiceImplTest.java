package com.cencosud.mobile.order.service;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.mobile.order.adapter.NotFoundException;
import com.cencosud.mobile.order.model.ItemDetailR2;
import com.cencosud.mobile.order.model.OrderDetailR2;
import com.cencosud.mobile.order.model.OrderDetailResponseR2;
import com.cencosud.mobile.order.model.Product;
import com.cencosud.mobile.order.util.RestServiceUtil;

@RunWith(MockitoJUnitRunner.class)
public class OrderApiServiceImplTest {

	@InjectMocks
	static OrderApiServiceImpl orderApiServiceImpl;

	@Mock
	RestServiceUtil restServiceUtil;

	@BeforeClass
	public static void initialSetup() {
		orderApiServiceImpl = new OrderApiServiceImpl();
	}

	@Test
	public void orderSearchGet() throws Exception {
		doReturn("orders").when(restServiceUtil).executeService(anyString(), Mockito.eq(String.class),
				Mockito.anyMapOf(String.class, Object.class));
		Response res = orderApiServiceImpl.findOrders("r2","v1", "luiggi0925@gmail.com");
		assertThat(res.getEntity().toString(), containsString("orders"));
	}

	@Test
	public void getOrderByIdR1() throws Exception {
		doReturn("detail").when(restServiceUtil).executeService(anyString(), Mockito.eq(String.class),
				Mockito.anyMapOf(String.class, Object.class));
		Response res = orderApiServiceImpl.getOrderById("r2","v1", "v10009544jmqa-01");
		assertThat(res.getEntity().toString(), containsString("detail"));
	}

	@Test
	public void getOrderById() throws NotFoundException {
		List<ItemDetailR2> items = new ArrayList<>();
		ItemDetailR2 itemDetail = new ItemDetailR2();
		itemDetail.setProductId("6659");
		itemDetail.setDescription("Leche Semidescremada sin Lactosa Svelty 1 L");
		itemDetail.setSkuId("6747");
		items.add(itemDetail);

		OrderDetailR2 order = new OrderDetailR2();
		order.setItems(items);

		OrderDetailResponseR2 orderDetailResponse = new OrderDetailResponseR2();
		orderDetailResponse.setOrder(order);

		
		Product product = new Product();
		product.setAddToCartLink(
				"https://jumbochilehomolog.vtexcommercestable.com.br/checkout/cart/add?sku=6747&qty=1&seller=1&sc=1&price=111900&cv=44b45da10d4de9e2d7516998d616756d_geral:14928EBFB66A237A646F232BA4D88119&sc=1");
		product.setSellerId("1");
		product.setSkuId("6747");
		

		Product [] products = {product};
		
		doReturn(orderDetailResponse).when(restServiceUtil).executeService(anyString(),
				Mockito.eq(OrderDetailResponseR2.class), Mockito.anyMapOf(String.class, Object.class));
		
		doReturn(products).when(restServiceUtil).executeService(anyString(), Mockito.eq(Product[].class), Mockito.anyMapOf(String.class, Object.class));
		
		Response res = orderApiServiceImpl.getOrderById("r2","v1", "v10009544jmqa-01", "1");
		assertThat(res.getEntity().toString(), containsString("OrdersResponse"));
	}
}
