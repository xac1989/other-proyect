package com.cencosud.middleware.list.dto;

import com.cencosud.middleware.list.model.ListDocument;

public class ListPostResponse {

	private String userId;
	private String listId;

	public ListPostResponse() { }

	public ListPostResponse(ListDocument result) {
		this.userId = result.getUserId();
		this.listId = result.getId();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}
}
