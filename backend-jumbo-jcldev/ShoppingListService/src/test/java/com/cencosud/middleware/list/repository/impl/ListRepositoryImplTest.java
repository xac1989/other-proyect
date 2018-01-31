package com.cencosud.middleware.list.repository.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMapOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;

import com.cencosud.common.util.rest.RestClient;
import com.cencosud.common.util.rest.RestClientResponse;
import com.cencosud.middleware.list.model.ListDocument;

/**
 * <h1>Test of ListRepositoryImpl</h1>
 * <p>
 * This contain the next tests:
 * <ol>
 * <li>{@link #getById()}</li>
 * </ol>
 * </p>
 * 
 * @author cesar.chacon
 * @since 2017-09-14
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ListRepositoryImplTest {

	@InjectMocks
	private ListRepositoryImpl listRepository;

	@Mock
	private RestClient client;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getById() {
		String listId = "55e9faea-5d14-11e7-9538-0ad3911cfea0";

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

		ListDocument listsDocuments = listDocument1;
		
		RestClientResponse<ListDocument> response = new RestClientResponse<>(200);
		response.setResponse(listsDocuments);
		
		when(client.executeService(anyString(), any(), eq(ListDocument.class), eq(HttpMethod.GET),
				anyMapOf(String.class, String.class))).thenReturn(response);

		ListDocument document = listRepository.getById(listId);

		assertThat(document).isNotNull();
//		assertThat(document.getId().equals(listId));
	}

	@Test
	public void getByIdReturnEmptyListDocument() {
		String listId = "55e9faea-5d14-11e7-9538-0ad3911cfea0";
		ListDocument listsDocuments = new ListDocument();
		RestClientResponse<ListDocument> response = new RestClientResponse<>(200);
		response.setResponse(listsDocuments);
		when(client.executeService(anyString(), any(), eq(ListDocument.class), eq(HttpMethod.GET),
				anyMapOf(String.class, String.class))).thenReturn(response);

		ListDocument document = listRepository.getById(listId);

		assertThat(document).isNotNull();
		assertThat(document.getId() == null);
	}

}
