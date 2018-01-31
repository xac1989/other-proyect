package com.cencosud.middleware.address.service;

import java.util.List;

import com.cencosud.middleware.address.dto.AddressDto;
import com.cencosud.middleware.address.model.Address;

public interface AddressService {

	List<AddressDto> getAddressesByUser(String profileId);
	
	AddressDto getAddressById(String profileId, String addressId);
	
	String createAddressByUser(String profileId, AddressDto address);
	
	void updateAddressByUser(String profileId, AddressDto address);
		
	void deleteAddressByUser(String addressId, String profileId);
	
	String getRegionId();
}
