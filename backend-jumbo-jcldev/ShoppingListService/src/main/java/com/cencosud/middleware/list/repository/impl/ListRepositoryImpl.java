package com.cencosud.middleware.list.repository.impl;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

import com.cencosud.common.util.rest.RestAsyncValues;
import com.cencosud.common.util.rest.RestClient;
import com.cencosud.common.util.rest.RestClientResponse;
import com.cencosud.middleware.list.dto.ListCreateResponse;
import com.cencosud.middleware.list.model.ListDocument;
import com.cencosud.middleware.list.repository.ListRepository;

/**
 * 
 * 
 * <h1>ListRepositoryImpl</h1>
 * <p>
 * Implementación del repository
 * </p>
 * 
 * @author fernando.castro
 * @version 1.0
 * @since Jun 26, 2017
 */
@Repository("listRepository")
public class ListRepositoryImpl implements ListRepository {

	private Logger logger = LoggerFactory.getLogger(ListRepositoryImpl.class);

	@Autowired
	@Qualifier("vTexClient")
	private RestClient client;

	private static final String PATH_LIST_ENTITY = "/dataentities/LS/documents/";
	private static final String PATH_LIST_SEARCH_DOCUMENT = "/dataentities/LS/";
	private static final String PATH_LIST_SEARCH_DOCUMENT_SEARCH = "/dataentities/LS/documents/";

	private static final String PAGE_RANGE_HEADER = "rest-range";
	private static final String PAGE_RANGE_HEADER_VALUE_FORMAT = "resources=%d-%d";
	private static final int PAGE_SIZE = 40;
	private static final String PAGE_CONTENT_RANGE = "REST-Content-Range";
	private static final String FIELDS = "active,added,isStoreList,name,quantity,skus,type,userId,id,defaultList";

	@Override
	public ListDocument create(ListDocument listDocument) {
		ListCreateResponse response = client.executeService(PATH_LIST_ENTITY, listDocument,
				ListCreateResponse.class, HttpMethod.POST).getResponse();
		ListDocument result = new ListDocument();
		result.setUserId(listDocument.getUserId());
		result.setId(response.getId().substring(3));
		logger.debug("Result after creating list: {}", result);
		return result;
	}

	@Override
	public void delete(ListDocument listDocument) {
		String url = PATH_LIST_ENTITY + listDocument.getId();
		client.executeService(url, null, Void.class, HttpMethod.DELETE);
	}

	@Override
	public ListDocument getById(String listId) {
		Map<String, String> queryParams = new LinkedHashMap<>();
		queryParams.put("_fields", FIELDS);
		return findListById(queryParams, listId);
	}
	
	private ListDocument findListById(Map<String, String> queryParams, String listId) {
		ListDocument listDocument = client.executeService(PATH_LIST_SEARCH_DOCUMENT_SEARCH+listId, null,
				ListDocument.class, HttpMethod.GET, queryParams).getResponse();
		if( listDocument != null ) {
			return listDocument;
		}
		return new ListDocument();
	}

	@Override
	public ListDocument updateProductList(ListDocument request) {
		client.executeService(PATH_LIST_ENTITY+request.getId(), request, String.class, HttpMethod.PATCH);
		return request;
	}

	@Override
	public List<ListDocument> getByUserId(String userId) {
		Map<String, String> queryParams = new LinkedHashMap<>();
		queryParams.put("userId", userId);
		queryParams.put("_fields", FIELDS);
		Map<String, String> headers = new LinkedHashMap<>();
		int page = 1;
		headers.put(PAGE_RANGE_HEADER, String.format(PAGE_RANGE_HEADER_VALUE_FORMAT, PAGE_SIZE * (page - 1), PAGE_SIZE * page - 1 ));
		List<ListDocument> results = new ArrayList<>();

		RestClientResponse<ListDocument[]> pageResponse = client.executeService(PATH_LIST_SEARCH_DOCUMENT.concat("search"), null,
				ListDocument[].class, HttpMethod.GET, queryParams, headers);
		results.addAll( asList(pageResponse.getResponse()) );
		int totalResults = Integer.parseInt(pageResponse.getHeaders().get(PAGE_CONTENT_RANGE).get(0).split("/")[1]);
		logger.debug("Total de listas para usuario {}: {}", userId, totalResults);
		if (totalResults > PAGE_SIZE) {
			int totalPages = totalResults / PAGE_SIZE + 1;
			logger.debug("Total de páginas de listas para usuario {}: {}", userId, totalPages);
			while (++page < totalPages) {
				pageResponse = client.executeServiceAsValid(PATH_LIST_SEARCH_DOCUMENT.concat("search"), null,
						ListDocument[].class, HttpMethod.GET, queryParams, headers);
				results.addAll( asList(pageResponse.getResponse()) );
			}
		}

		return results;
	}

	@Override
	public List<ListDocument> getByListId(List<String> listIdList) {
		Map<String, String> queryParams = new LinkedHashMap<>();
		queryParams.put("_fields", FIELDS);
		List<ListDocument> results = new ArrayList<>();
		
		int count =0;
		int iterator = listIdList.size()/PAGE_SIZE;
		int iteratorCount = 1;
		
		if(listIdList.size()%PAGE_SIZE >0) {
			iterator++;
		}
		
		while(iteratorCount <= iterator) {
			StringBuilder sb = new StringBuilder("(");
			while( count < PAGE_SIZE*iteratorCount && count < listIdList.size()) {
				sb.append("id%3D").append(listIdList.get(count));
				if(count < (PAGE_SIZE*iteratorCount)-1) {
					sb.append("%20OR%20");
				}
				count++;
			}
			
			sb.append(")");
			queryParams.put("_where",sb.toString());
			
			Map<String, String> headers = new LinkedHashMap<>();
			int page = 1;
			headers.put(PAGE_RANGE_HEADER, String.format(PAGE_RANGE_HEADER_VALUE_FORMAT, PAGE_SIZE * (page - 1), PAGE_SIZE * page - 1 ));
			
			ListDocument[] listResponse = client.executeService(PATH_LIST_SEARCH_DOCUMENT.concat("search"), null, ListDocument[].class, HttpMethod.GET,queryParams, headers).getResponse();
			
			results.addAll(Arrays.asList(listResponse));
			iteratorCount++;
		}
		return results;
	}
	
	@Override
	public List<ListDocument> getByListIdOneByOne(List<String> listIdList) {
		List<ListDocument> results;
		List<RestClientResponse<ListDocument>> restClientResponse;
		List<RestAsyncValues<Void>> requestList = new ArrayList<>(listIdList.size()); 
		Map<String, String> queryParams = new LinkedHashMap<>();
		queryParams.put("_fields", FIELDS);
		listIdList.forEach(tmpListId -> {
			RestAsyncValues<Void> tempRestAsyncValue = new RestAsyncValues<>();
			tempRestAsyncValue.setUrl(PATH_LIST_SEARCH_DOCUMENT_SEARCH+tmpListId);
			tempRestAsyncValue.setQueryParams(queryParams);
			requestList.add(tempRestAsyncValue);
		});
		restClientResponse = client.executeAsyncServices(requestList, ListDocument.class, HttpMethod.GET, null);
		
		if(restClientResponse != null && !restClientResponse.isEmpty()) {
			results = new ArrayList<>(restClientResponse.size());
			for(RestClientResponse<ListDocument> tmpResponse:restClientResponse) {
				if(tmpResponse.getResponse() != null) {
					results.add(tmpResponse.getResponse());
				}
			}
		}else {
			results = new ArrayList<>(0);
		}
		return results;
	}
}
