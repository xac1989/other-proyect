package com.cencosud.middleware.order.client.dto;

import java.util.List;

import com.cencosud.middleware.order.model.ShippingDetail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VtexShippingData {

	private String id;
	private VtexShippingAddress address;
	private List<VtexLogisticDetail> logisticsInfo;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public VtexShippingAddress getAddress() {
		return address;
	}
	public void setAddress(VtexShippingAddress address) {
		this.address = address;
	}
	public List<VtexLogisticDetail> getLogisticsInfo() {
		return logisticsInfo;
	}
	public void setLogisticsInfo(List<VtexLogisticDetail> logisticsInfo) {
		this.logisticsInfo = logisticsInfo;
	}
	
	
	public ShippingDetail toModelShippingDetail(){
		
		if(this.address == null){
			return null;
		}
		
		return new ShippingDetail(
				this.address.getReceiverName(), 
				this.address.getPostalCode(), 
				this.address.getCity(), 
				this.address.getState(), 
				this.address.getCountry(), 
				this.address.getStreet(), 
				this.address.getNumber()
			);
	}
	
	
}
