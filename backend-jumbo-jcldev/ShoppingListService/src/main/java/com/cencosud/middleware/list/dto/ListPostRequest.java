package com.cencosud.middleware.list.dto;

/**
 * Objeto de transferencia para crear una lista
 * @author luiggi.mendoza
 *
 */
public class ListPostRequest {

	private String userId;
	private String name;

	/**
	 * @return user id that owns the list
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id that owns the list
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return name of the list
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the list
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
