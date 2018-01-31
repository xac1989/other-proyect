package com.cencosud.mobile.profile.model;

public class AddressResponse {
	
	private Address address;
	private Metadata metadata;
	
	
	public AddressResponse() {
		super();
	}
	
	public AddressResponse(Address address) {
		super();
		this.address = address;
	}
	
	public AddressResponse(Address address, Metadata metadata) {
		super();
		this.address = address;
		this.metadata = metadata;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Metadata getMetadata() {
		return metadata;
	}
	
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	
}
