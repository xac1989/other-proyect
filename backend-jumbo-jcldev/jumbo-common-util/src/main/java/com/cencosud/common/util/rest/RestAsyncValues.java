package com.cencosud.common.util.rest;

import java.util.Map;

public class RestAsyncValues<E> {
	
	private String url; 
	private E document;
	Map<String, String> queryParams;
	
	public RestAsyncValues() {
	}

	/**
	 * @param url
	 * @param document
	 * @param queryParams
	 */
	public RestAsyncValues(String url, E document, Map<String, String> queryParams) {
		super();
		this.url = url;
		this.document = document;
		this.queryParams = queryParams;
	}
	
	public RestAsyncValues(String url, E document) {
		super();
		this.url = url;
		this.document = null;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the document
	 */
	public E getDocument() {
		return document;
	}

	/**
	 * @param document the document to set
	 */
	public void setDocument(E document) {
		this.document = document;
	}

	/**
	 * @return the queryParams
	 */
	public Map<String, String> getQueryParams() {
		return queryParams;
	}

	/**
	 * @param queryParams the queryParams to set
	 */
	public void setQueryParams(Map<String, String> queryParams) {
		this.queryParams = queryParams;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RestAsyncValues [url=");
		builder.append(url);
		builder.append(", document=");
		builder.append(document);
		builder.append(", queryParams=");
		builder.append(queryParams);
		builder.append("]");
		return builder.toString();
	}
	
}
