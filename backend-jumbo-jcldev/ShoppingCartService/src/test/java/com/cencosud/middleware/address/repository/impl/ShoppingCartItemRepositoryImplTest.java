package com.cencosud.middleware.address.repository.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;

import com.cencosud.middleware.shoppingcart.dto.ShoppingCartItemDto;
import com.cencosud.middleware.shoppingcart.dto.ShoppingCartItemIndexDto;
import com.cencosud.middleware.shoppingcart.dto.ShoppingCartItemSkuDto;
import com.cencosud.middleware.shoppingcart.model.OrderFormCartItemsDocument;
import com.cencosud.middleware.shoppingcart.model.OrderItemDocument;
import com.cencosud.middleware.shoppingcart.model.VtexItem;
import com.cencosud.middleware.shoppingcart.model.VtexOrderForm;
import com.cencosud.middleware.shoppingcart.repository.impl.ShoppingCartItemRepositoryImpl;
import com.cencosud.middleware.shoppingcart.utils.VtexUtilClient;


@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartItemRepositoryImplTest {

	private static final int INDX_ZERO = 0;

	private static final String SHOPPING_CART_ID = "e69373ce972d4ac499a2b24f88146f24";

	@InjectMocks
	private ShoppingCartItemRepositoryImpl repo;
	
	@Mock
	private VtexUtilClient clientUtil;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDelete() {
		OrderFormCartItemsDocument document = builDeleteDocument(INDX_ZERO);
		VtexOrderForm value = buildVtexForm();
		when(clientUtil.executeService(anyString(), any(),any(Class.class) , any(HttpMethod.class))).thenReturn(value);
		
		repo.deleteItemInCartByIndex(INDX_ZERO, SHOPPING_CART_ID);
		
		verify(clientUtil, times(1)).executeService(anyString(), any(),any(Class.class) , any(HttpMethod.class));
	}

	private VtexOrderForm buildVtexForm() {
		VtexOrderForm vof = new VtexOrderForm();
		vof.setOrderFormId(SHOPPING_CART_ID);
		vof.setSalesChannel("1");
		LinkedList<VtexItem> items = new LinkedList<VtexItem>();
		items.add(new VtexItem("B57C701ABB70475D9027618D89EC37BD", "438", "436", "1544835", 2));
		items.add(new VtexItem("DC775A21C971474A81E6324A76A4F523", "436", "434", "1544834", 1));
		vof.setItems(items);
		return vof;
	}

	private OrderFormCartItemsDocument builDeleteDocument(Integer indx) {
		
		List<OrderItemDocument> orderItems = new ArrayList<>();
		orderItems.add(new OrderItemDocument(String.valueOf(indx),0));
		List<String> expectedOrderFormSections = Arrays.asList("items");
		Boolean noSplitItem = true;
		
		return new OrderFormCartItemsDocument(orderItems, expectedOrderFormSections, noSplitItem);
	}

	
	@Test
	public void testUpdate() {
		OrderFormCartItemsDocument document = builDeleteDocument(INDX_ZERO);
		VtexOrderForm value = buildVtexForm();
		when(clientUtil.executeService(anyString(), any(),any(Class.class) , any(HttpMethod.class))).thenReturn(value);
		
		ShoppingCartItemIndexDto item = new ShoppingCartItemIndexDto("438",INDX_ZERO,2);
		ShoppingCartItemDto result = repo.updateItemInCart(item, SHOPPING_CART_ID);
		
		verify(clientUtil, times(1)).executeService(anyString(), any(),any(Class.class) , any(HttpMethod.class));
		
		assertThat(result, is(not(nullValue())));
		assertThat(result.getQuantity(), is(2));
		assertThat(result.getSkuId(), is("438"));
	}
	
	@Test
	public void testAdd() {
		OrderFormCartItemsDocument document = builDeleteDocument(INDX_ZERO);
		VtexOrderForm value = buildVtexForm();
		when(clientUtil.executeService(anyString(), any(),any(Class.class) , any(HttpMethod.class))).thenReturn(value);
		
		List<ShoppingCartItemSkuDto> items = new ArrayList<>();
		items.add(new ShoppingCartItemSkuDto("438",INDX_ZERO,2));
		items.add(new ShoppingCartItemSkuDto("436",INDX_ZERO,1));
		
		List<ShoppingCartItemDto> result = repo.addItemInCart(items, SHOPPING_CART_ID);
		
		verify(clientUtil, times(1)).executeService(anyString(), any(),any(Class.class) , any(HttpMethod.class));
		
		assertThat(result, is(not(nullValue())));
		assertThat(result, is(not(empty())));
		assertThat(result.size(), is(2));

	}
	
}
