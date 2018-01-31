package com.cencosud.middleware.address.model;

import java.util.List;

/**
 * 
 * @author anyela.herrera
 * @version 1.0
 * @since Aug 21, 2017
 *
 */
public class AddressListDto {

	private List<Address> addresses;

	public AddressListDto() {
		super();
	}

	public AddressListDto(List<Address> addresses) {
		super();
		this.addresses = addresses;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
}
