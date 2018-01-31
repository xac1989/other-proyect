package com.cencosud.mobile.list.dto;

import java.util.List;

import com.cencosud.mobile.list.model.Metadata;

public class ListProductResponse {
	private String userId;
	private List<ListProductInfo> lists;
	private Metadata metadata;

	public ListProductResponse() {
	}

	/**
	 * @param userId
	 * @param lists
	 * @param metadata
	 */
	public ListProductResponse(String userId, List<ListProductInfo> lists, Metadata metadata) {
		super();
		this.userId = userId;
		this.lists = lists;
		this.metadata = metadata;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the lists
	 */
	public List<ListProductInfo> getLists() {
		return lists;
	}

	/**
	 * @param lists
	 *            the lists to set
	 */
	public void setLists(List<ListProductInfo> lists) {
		this.lists = lists;
	}

	/**
	 * @return the metadata
	 */
	public Metadata getMetadata() {
		return metadata;
	}

	/**
	 * @param metadata
	 *            the metadata to set
	 */
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListProductResponse [userId=");
		builder.append(userId);
		builder.append(", lists=");
		builder.append(lists);
		builder.append(", metadata=");
		builder.append(metadata);
		builder.append("]");
		return builder.toString();
	}

}
