package com.cencosud.mobile.profile.model;

import java.util.List;

public class AddressesResponse {

	private List<Address> addresses;
	private Metadata metadata;
	
	public AddressesResponse() {
		super();
	}

	public AddressesResponse(List<Address> addresses) {
		super();
		this.addresses = addresses;
	}
	public AddressesResponse(List<Address> addresses, Metadata metadata) {
		super();
		this.addresses = addresses;
		this.metadata = metadata;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	
}
