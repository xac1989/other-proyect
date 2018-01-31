package com.cencosud.middleware.list.repository.impl;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

import com.cencosud.common.util.rest.RestClient;
import com.cencosud.middleware.list.dto.ListCreateResponse;
import com.cencosud.middleware.list.model.UserList;
import com.cencosud.middleware.list.repository.UserListRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class UserListRepositoryImpl implements UserListRepository {

	private Logger logger = LoggerFactory.getLogger(UserListRepositoryImpl.class);

	@Autowired
	private RestClient client;

	private ObjectMapper mapper = new ObjectMapper();

	private static final String PATH_USER_LIST_ENTITY = "/dataentities/UL/documents/";

	@Override
	public UserList findByUserId(String userId) {
		Map<String, String> queryParams = new LinkedHashMap<>();
		queryParams.put("id", userId);
		queryParams.put("_fields", "shoppingList,id");
		UserList userList = client.executeService(PATH_USER_LIST_ENTITY+userId, null, UserList.class, HttpMethod.GET, queryParams).getResponse();
		
		if(userList == null) {
			return userList;
		}
			
		try {
			List<String> shoppingListFromJson = mapper.readValue(userList.getShoppingList(), new TypeReference<List<String>>() { });
			userList.setShoppingListFromJson(shoppingListFromJson);
		} catch (Exception e) {
			logger.error("Error al leer relación entre usuario y sus listas", e);
		}

		return userList;
	}

	@Override
	public ListCreateResponse update(UserList userList) {
		try {
			String shoppingList = mapper.writeValueAsString(userList.getShoppingListFromJson());
			return client.executeService(PATH_USER_LIST_ENTITY+userList.getId(),
					new UserList(userList.getId(), shoppingList, null),
					ListCreateResponse.class, HttpMethod.PUT).getResponse();
		} catch (IOException e) {
			logger.error("Error al convertir shoppingListFromJson a json para actualizar usuario y sus listas", e);
			throw new RuntimeException("No se puede actualizar la relación entre usuario y sus listas.", e);
		}
	}
}
