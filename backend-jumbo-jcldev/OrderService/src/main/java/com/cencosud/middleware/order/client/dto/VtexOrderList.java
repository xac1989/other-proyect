package com.cencosud.middleware.order.client.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class VtexOrderList {

	private List<VtexOrder> list;
	
	public List<VtexOrder> getList() {
		return list;
	}
	public void setList(List<VtexOrder> list) {
		this.list = list;
	}
	
	
	
}
