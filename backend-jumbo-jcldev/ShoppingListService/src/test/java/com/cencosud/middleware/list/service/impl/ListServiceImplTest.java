package com.cencosud.middleware.list.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.middleware.list.dto.ListProductUpdateRequest;
import com.cencosud.middleware.list.dto.ListProductUpdateResponse;
import com.cencosud.middleware.list.dto.ListResponse;
import com.cencosud.middleware.list.dto.ListUpdateRequest;
import com.cencosud.middleware.list.model.ListDocument;
import com.cencosud.middleware.list.model.ListUpdate;
import com.cencosud.middleware.list.model.Product;
import com.cencosud.middleware.list.model.ProductList;
import com.cencosud.middleware.list.model.UserList;
import com.cencosud.middleware.list.repository.ListRepository;
import com.cencosud.middleware.list.repository.UserListRepository;

/**
 * 
 * <h1>ListServiceImplTest</h1>
 * <p>
 * Test de servicio de listas
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jul 6, 2017
 */
@RunWith(MockitoJUnitRunner.class)
public class ListServiceImplTest {

	@InjectMocks
	private ListServiceImpl listService;
	
	@Mock
	private ListRepository listRepository;
	
	@Mock
	private UserListRepository userListRepository;
	
	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getListByUserId(){
		String userId = "ebcddb68-9cd6-4de4-9ae2-23d1e78d0718";
		String shoppingList = "[\"55e9faea-5d14-11e7-9538-0ad3911cfea0\",\"923fde2d-625b-11e7-9538-0aa38ef95ab6\",\"fabc5c11-625b-11e7-9538-0aa38ef95ab6\"]";
		List<String> lstShoppingList = new ArrayList<>();
		lstShoppingList.add("55e9faea-5d14-11e7-9538-0ad3911cfea0");
		lstShoppingList.add("923fde2d-625b-11e7-9538-0aa38ef95ab6");
		lstShoppingList.add("fabc5c11-625b-11e7-9538-0aa38ef95ab6");
		UserList userList = new UserList(userId, shoppingList, lstShoppingList);
		
		ListDocument listDocument1 = new ListDocument();
		listDocument1.setActive(true);
		listDocument1.setAdded(false);
		listDocument1.setId("55e9faea-5d14-11e7-9538-0ad3911cfea0");
		listDocument1.setIsStoreList(false);
		listDocument1.setName("Prueba modificar lista");
		listDocument1.setQuantity(2);
		listDocument1.setSkus("{\"174\":{\"quantity\":10},\"2\":{\"quantity\":2}}");
		listDocument1.setType("client");
		listDocument1.setUserId("ebcddb68-9cd6-4de4-9ae2-23d1e78d0718");
		
		ListDocument listDocument2 = new ListDocument();
		listDocument2.setActive(null);
		listDocument2.setAdded(null);
		listDocument2.setId("923fde2d-625b-11e7-9538-0aa38ef95ab6");
		listDocument2.setIsStoreList(false);
		listDocument2.setName("Prueba Tiempo respuesta");
		listDocument2.setQuantity(null);
		listDocument2.setSkus(null);
		listDocument2.setType("client");
		listDocument2.setUserId("ebcddb68-9cd6-4de4-9ae2-23d1e78d0718");
		
		ListDocument listDocument3 = new ListDocument();
		listDocument3.setActive(null);
		listDocument3.setAdded(null);
		listDocument3.setId("fabc5c11-625b-11e7-9538-0aa38ef95ab6");
		listDocument3.setIsStoreList(false);
		listDocument3.setName("Prueba Tiempo respuesta 2");
		listDocument3.setQuantity(null);
		listDocument3.setSkus(null);
		listDocument3.setType("client");
		listDocument3.setUserId("ebcddb68-9cd6-4de4-9ae2-23d1e78d0718");
		
		List<ListDocument> lstDocmuent = new ArrayList<>();
		lstDocmuent.add(listDocument1);
		lstDocmuent.add(listDocument2);
		lstDocmuent.add(listDocument3);
		
		when(userListRepository.findByUserId(anyString())).thenReturn(userList);
		when(listRepository.getByListId(anyList())).thenReturn(lstDocmuent);
		when(listRepository.getByUserId(anyString())).thenReturn(lstDocmuent);
		
		ListResponse listResponse = listService.getListByUserId(userId);
		
		verify(userListRepository, times(1)).findByUserId(anyString());
		verify(listRepository, times(0)).getByUserId(anyString());
		
		assertThat(listResponse.getShoppingList()).isNotNull();
		assertThat(listResponse.getUserId()).isNotNull().isNotBlank();
	}
	
	@Test
	public void getListByUserId_userListNull(){
		String userId = "ebcddb68-9cd6-4de4-9ae2-23d1e78d0718";
		UserList userList = null;
		
		when(userListRepository.findByUserId(anyString())).thenReturn(userList);
		
		ListResponse listResponse = listService.getListByUserId(userId);
		
		verify(userListRepository, times(1)).findByUserId(anyString());
		verify(listRepository, never()).getByUserId(anyString());
		
		assertThat(listResponse.getShoppingList()).isNotNull();
		assertThat(listResponse.getUserId()).isNotNull().isNotBlank();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getProductList(){
		String userId = "ebcddb68-9cd6-4de4-9ae2-23d1e78d0718";
		String skuId = "174";
		String shoppingList = "[\"55e9faea-5d14-11e7-9538-0ad3911cfea0\",\"923fde2d-625b-11e7-9538-0aa38ef95ab6\",\"fabc5c11-625b-11e7-9538-0aa38ef95ab6\"]";
		List<String> lstShoppingList = new ArrayList<>();
		lstShoppingList.add("55e9faea-5d14-11e7-9538-0ad3911cfea0");
		lstShoppingList.add("923fde2d-625b-11e7-9538-0aa38ef95ab6");
		lstShoppingList.add("fabc5c11-625b-11e7-9538-0aa38ef95ab6");
		UserList userList = new UserList(userId, shoppingList, lstShoppingList);
		
		ListDocument listDocument1 = new ListDocument();
		listDocument1.setActive(true);
		listDocument1.setAdded(false);
		listDocument1.setId("55e9faea-5d14-11e7-9538-0ad3911cfea0");
		listDocument1.setIsStoreList(false);
		listDocument1.setName("Prueba modificar lista");
		listDocument1.setQuantity(2);
		listDocument1.setSkus("{\"174\":{\"quantity\":10},\"2\":{\"quantity\":2}}");
		listDocument1.setType("client");
		listDocument1.setUserId("ebcddb68-9cd6-4de4-9ae2-23d1e78d0718");
		listDocument1.setDefaultList(false);
		
		ListDocument listDocument2 = new ListDocument();
		listDocument2.setActive(null);
		listDocument2.setAdded(null);
		listDocument2.setId("923fde2d-625b-11e7-9538-0aa38ef95ab6");
		listDocument2.setIsStoreList(false);
		listDocument2.setName("Prueba Tiempo respuesta");
		listDocument2.setQuantity(null);
		listDocument2.setSkus(null);
		listDocument2.setType("client");
		listDocument2.setUserId("ebcddb68-9cd6-4de4-9ae2-23d1e78d0718");
		listDocument2.setDefaultList(false);
		
		ListDocument listDocument3 = new ListDocument();
		listDocument3.setActive(null);
		listDocument3.setAdded(null);
		listDocument3.setId("fabc5c11-625b-11e7-9538-0aa38ef95ab6");
		listDocument3.setIsStoreList(false);
		listDocument3.setName("Prueba Tiempo respuesta 2");
		listDocument3.setQuantity(null);
		listDocument3.setSkus(null);
		listDocument3.setType("client");
		listDocument3.setUserId("ebcddb68-9cd6-4de4-9ae2-23d1e78d0718");
		listDocument3.setDefaultList(false);
		
		List<ListDocument> lstDocmuent = new ArrayList<>();
		lstDocmuent.add(listDocument1);
		lstDocmuent.add(listDocument2);
		lstDocmuent.add(listDocument3);
		
		when(userListRepository.findByUserId(anyString())).thenReturn(userList);
		when(listRepository.getByListIdOneByOne(anyList())).thenReturn(lstDocmuent);
		
		ProductList productList = listService.getProductList(userId, skuId);
		
		verify(userListRepository, times(1)).findByUserId(anyString());
		verify(listRepository, times(1)).getByListIdOneByOne((anyList()));
		
		assertThat(productList.getProductShoppingList()).isNotNull();
		assertThat(productList.getUserId()).isNotNull().isNotBlank();
	}
	
	@Test
	public void getProductList_emptyList(){
		String userId = "ebcddb68-9cd6-4de4-9ae2-23d1e78d0718";
		String skuId = "174";
		UserList userList = null;
		
		when(userListRepository.findByUserId(anyString())).thenReturn(userList);		
		ProductList productList = listService.getProductList(userId, skuId);
		
		verify(userListRepository, times(1)).findByUserId(anyString());
		
		
		assertThat(productList.getProductShoppingList()).isNotNull();
		assertThat(productList.getUserId()).isNotNull().isNotBlank();
	}
	
	@Test
	public void create(){
		String userId = "ebcddb68-9cd6-4de4-9ae2-23d1e78d0718";
		String shoppingList = "[\"55e9faea-5d14-11e7-9538-0ad3911cfea0\",\"923fde2d-625b-11e7-9538-0aa38ef95ab6\",\"fabc5c11-625b-11e7-9538-0aa38ef95ab6\"]";
		List<String> lstShoppingList = new ArrayList<>();
		lstShoppingList.add("55e9faea-5d14-11e7-9538-0ad3911cfea0");
		lstShoppingList.add("923fde2d-625b-11e7-9538-0aa38ef95ab6");
		lstShoppingList.add("fabc5c11-625b-11e7-9538-0aa38ef95ab6");
		UserList userList = new UserList(userId, shoppingList, lstShoppingList);
		ListDocument listDocument1 = new ListDocument();
		listDocument1.setActive(true);
		listDocument1.setAdded(false);
		listDocument1.setId("55e9faea-5d14-11e7-9538-0ad3911cfea0");
		listDocument1.setIsStoreList(false);
		listDocument1.setName("Prueba modificar lista");
		listDocument1.setQuantity(2);
		listDocument1.setSkus("{\"174\":{\"quantity\":10},\"2\":{\"quantity\":2}}");
		listDocument1.setType("client");
		listDocument1.setUserId("ebcddb68-9cd6-4de4-9ae2-23d1e78d0718");
		
		when(listRepository.create(any(ListDocument.class))).thenReturn(listDocument1);
		when(userListRepository.findByUserId(anyString())).thenReturn(userList);
		
		ListDocument response = listService.create(listDocument1);
		
		verify(listRepository, times(1)).create(any(ListDocument.class));
		verify(userListRepository, times(1)).findByUserId(anyString());
		verify(userListRepository, times(1)).update(any(UserList.class));
		
		assertThat(response.getId()).isNotNull().isNotBlank();
		assertThat(response.getUserId()).isNotNull().isNotBlank();
	}
	
	@Test
	public void create_findByUserIdNull(){
		List<String> lstShoppingList = new ArrayList<>();
		lstShoppingList.add("55e9faea-5d14-11e7-9538-0ad3911cfea0");
		lstShoppingList.add("923fde2d-625b-11e7-9538-0aa38ef95ab6");
		lstShoppingList.add("fabc5c11-625b-11e7-9538-0aa38ef95ab6");
		ListDocument listDocument1 = new ListDocument();
		listDocument1.setActive(true);
		listDocument1.setAdded(false);
		listDocument1.setId("55e9faea-5d14-11e7-9538-0ad3911cfea0");
		listDocument1.setIsStoreList(false);
		listDocument1.setName("Prueba modificar lista");
		listDocument1.setQuantity(2);
		listDocument1.setSkus("{\"174\":{\"quantity\":10},\"2\":{\"quantity\":2}}");
		listDocument1.setType("client");
		listDocument1.setUserId("ebcddb68-9cd6-4de4-9ae2-23d1e78d0718");
		
		when(listRepository.create(any(ListDocument.class))).thenReturn(listDocument1);
		when(userListRepository.findByUserId(anyString())).thenReturn(null);
		
		ListDocument response = listService.create(listDocument1);
		
		verify(listRepository, times(1)).create(any(ListDocument.class));
		verify(userListRepository, times(1)).findByUserId(anyString());
		verify(userListRepository, times(1)).update(any(UserList.class));
		
		assertThat(response.getId()).isNotNull().isNotBlank();
		assertThat(response.getUserId()).isNotNull().isNotBlank();
	}
	
	@Test
	public void delete(){
		String userId = "ebcddb68-9cd6-4de4-9ae2-23d1e78d0718";
		String shoppingList = "[\"55e9faea-5d14-11e7-9538-0ad3911cfea0\",\"923fde2d-625b-11e7-9538-0aa38ef95ab6\",\"fabc5c11-625b-11e7-9538-0aa38ef95ab6\"]";
		List<String> lstShoppingList = new ArrayList<>();
		lstShoppingList.add("55e9faea-5d14-11e7-9538-0ad3911cfea0");
		lstShoppingList.add("923fde2d-625b-11e7-9538-0aa38ef95ab6");
		lstShoppingList.add("fabc5c11-625b-11e7-9538-0aa38ef95ab6");
		UserList userList = new UserList(userId, shoppingList, lstShoppingList);
		ListDocument listDocument1 = new ListDocument();
		listDocument1.setActive(true);
		listDocument1.setAdded(false);
		listDocument1.setId("55e9faea-5d14-11e7-9538-0ad3911cfea0");
		listDocument1.setIsStoreList(false);
		listDocument1.setName("Prueba modificar lista");
		listDocument1.setQuantity(2);
		listDocument1.setSkus("{\"174\":{\"quantity\":10},\"2\":{\"quantity\":2}}");
		listDocument1.setType("client");
		listDocument1.setUserId("ebcddb68-9cd6-4de4-9ae2-23d1e78d0718");
		
		when(userListRepository.findByUserId(anyString())).thenReturn(userList);
		
		listService.delete(listDocument1);
		
		verify(userListRepository, times(1)).findByUserId(anyString());
		verify(userListRepository, times(1)).update(any(UserList.class));
	}
	
	@Test
	public void updateList(){
		String listId = "55e9faea-5d14-11e7-9538-0ad3911cfea0";
		Product product1 = new Product("174", 10);
		Product product2 = new Product("2", 10);
		List<Product> products = new ArrayList<>();
		products.add(product1);
		products.add(product2);
		ListUpdateRequest listUpdateRequest = new ListUpdateRequest(listId, products);
		
		ListDocument listDocument1 = new ListDocument();
		listDocument1.setActive(true);
		listDocument1.setAdded(false);
		listDocument1.setId("55e9faea-5d14-11e7-9538-0ad3911cfea0");
		listDocument1.setIsStoreList(false);
		listDocument1.setName("Prueba modificar lista");
		listDocument1.setQuantity(2);
		listDocument1.setSkus("{\"174\":{\"quantity\":10},\"2\":{\"quantity\":2}}");
		listDocument1.setType("client");
		listDocument1.setUserId("ebcddb68-9cd6-4de4-9ae2-23d1e78d0718");
		
		when(listRepository.getById(anyString())).thenReturn(listDocument1);
		
		listService.updateList(listUpdateRequest);
		
		verify(listRepository, times(1)).getById(anyString());
		verify(listRepository, times(1)).updateProductList(any(ListDocument.class));
	}
	
	@Test
	public void updateListWithListDocumentEmpty(){
		String listId = "55e9faea-5d14-11e7-9538-0ad3911cfea0";
		Product product1 = new Product("174", 10);
		Product product2 = new Product("2", 10);
		List<Product> products = new ArrayList<>();
		products.add(product1);
		products.add(product2);
		ListUpdateRequest listUpdateRequest = new ListUpdateRequest(listId, products);
				
		when(listRepository.getById(anyString())).thenReturn(new ListDocument());
		
		listService.updateList(listUpdateRequest);
		
		verify(listRepository, times(1)).getById(anyString());
		verify(listRepository, never()).updateProductList(any(ListDocument.class));
	}
	
	@Test
	public void updateProductList(){
		String listId = "55e9faea-5d14-11e7-9538-0ad3911cfea0";
		String skuId = "174";
		ListUpdate listUpdate = new ListUpdate(listId, 10, null, null);
		
		ListProductUpdateRequest listProductUpdateRequest = new ListProductUpdateRequest(skuId, Arrays.asList(listUpdate));
		
		ListDocument listDocument1 = new ListDocument();
		listDocument1.setActive(true);
		listDocument1.setAdded(false);
		listDocument1.setId("55e9faea-5d14-11e7-9538-0ad3911cfea0");
		listDocument1.setIsStoreList(false);
		listDocument1.setName("Prueba modificar lista");
		listDocument1.setQuantity(2);
		listDocument1.setSkus("{\"174\":{\"quantity\":10},\"2\":{\"quantity\":2}}");
		listDocument1.setType("client");
		listDocument1.setUserId("ebcddb68-9cd6-4de4-9ae2-23d1e78d0718");
		
		when(listRepository.getById(anyString())).thenReturn(listDocument1);
		
		ListProductUpdateResponse response = listService.updateProductList(listProductUpdateRequest);
		
		verify(listRepository, times(1)).getById(anyString());
		verify(listRepository, times(1)).updateProductList(any(ListDocument.class));
		
		assertThat(response.getLists()).isNotNull().isNotEmpty();
		for(ListUpdate tmpListUpdate:response.getLists()){
			assertThat(tmpListUpdate.getStatus()).isEqualTo("ok");
			assertThat(tmpListUpdate.getName()).isNotNull().isNotBlank();
		}
	}
	
	@Test
	public void updateProductList_listDocumentEmpty(){
		String listId = "55e9faea-5d14-11e7-9538-0ad3911cfea0";
		String skuId = "174";
		ListUpdate listUpdate = new ListUpdate(listId, 10, null, null);
		
		ListProductUpdateRequest listProductUpdateRequest = new ListProductUpdateRequest(skuId, Arrays.asList(listUpdate));
		
		when(listRepository.getById(anyString())).thenReturn(new ListDocument());
		
		ListProductUpdateResponse response = listService.updateProductList(listProductUpdateRequest);
		
		verify(listRepository, times(1)).getById(anyString());
		verify(listRepository, times(1)).updateProductList(any(ListDocument.class));
		
		assertThat(response.getLists()).isNotNull().isNotEmpty();
		for(ListUpdate tmpListUpdate:response.getLists()){
			assertThat(tmpListUpdate.getStatus()).isEqualTo("ok");
			assertThat(listId.equals(tmpListUpdate.getListId()));
			assertThat(tmpListUpdate.getName()).isNull();
		}
	}
	
	@Test
	public void updateProductList_listDocumentWithIdEmpty(){
		String listId = "";
		String skuId = "174";
		ListUpdate listUpdate = new ListUpdate(listId, 10, null, null);
		
		ListProductUpdateRequest listProductUpdateRequest = new ListProductUpdateRequest(skuId, Arrays.asList(listUpdate));
		
		when(listRepository.getById(anyString())).thenReturn(new ListDocument());
		
		ListProductUpdateResponse response = listService.updateProductList(listProductUpdateRequest);
		
		verify(listRepository, times(1)).getById(anyString());
		verify(listRepository, never()).updateProductList(any(ListDocument.class));
		
		assertThat(response.getLists()).isNotNull().isNotEmpty();
		for(ListUpdate tmpListUpdate:response.getLists()){
			assertThat(tmpListUpdate.getStatus()).isEqualTo("error");
			assertThat(tmpListUpdate.getName()).isNull();
		}
	}
}
