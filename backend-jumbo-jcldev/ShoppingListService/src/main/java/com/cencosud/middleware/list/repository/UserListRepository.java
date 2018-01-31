package com.cencosud.middleware.list.repository;

import com.cencosud.middleware.list.dto.ListCreateResponse;
import com.cencosud.middleware.list.model.UserList;

public interface UserListRepository {

	/**
	 * Obtiene la relación de usuario y sus listas a partir del id de usuario
	 * @param userId Id del usuario
	 * @return
	 */
	UserList findByUserId(String userId);

	/**
	 * Actualiza por completo la relación de usuario y sus listas
	 * @param userList nuevo estado 
	 * @return
	 */
	ListCreateResponse update(UserList userList);
}
