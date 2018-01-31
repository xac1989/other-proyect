package com.cencosud.middleware.order.model;

public class ShippingDetail {
	
	private String receiverName;
	private String postalCode;
	private String city;
	private String state;
	private String country;
	private String street;
	private String number;
	
	
	public ShippingDetail(String receiverName, String postalCode, String city, String state, String country,
			String street, String number) {
		super();
		this.receiverName = receiverName;
		this.postalCode = postalCode;
		this.city = city;
		this.state = state;
		this.country = country;
		this.street = street;
		this.number = number;
	}
	
	public ShippingDetail() {
	}
	
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
}
