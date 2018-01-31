package com.cencosud.middleware.catalog.model;

public class SearchInfo {

	private int result;
	private int totalResult;

	public SearchInfo(int result, int totalResult) {
		super();
		this.result = result;
		this.totalResult = totalResult;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

}
