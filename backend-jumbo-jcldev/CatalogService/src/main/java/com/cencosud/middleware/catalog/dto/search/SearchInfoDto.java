package com.cencosud.middleware.catalog.dto.search;

public class SearchInfoDto {

	private int result;
	private int totalResult;

	public SearchInfoDto(int result, int totalResult) {
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
