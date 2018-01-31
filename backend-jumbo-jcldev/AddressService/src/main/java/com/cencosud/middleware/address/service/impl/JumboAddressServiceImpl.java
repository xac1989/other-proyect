package com.cencosud.middleware.address.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.address.dto.AddressDto;
import com.cencosud.middleware.address.repository.AddressRepository;
import com.cencosud.middleware.address.service.AddressService;
import com.cencosud.middleware.profile.utils.MsgValidation;
import com.cencosud.middleware.profile.utils.MyBeansUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class JumboAddressServiceImpl implements AddressService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JumboAddressServiceImpl.class);
	
    // Region2 = JUMBO CHILE
	private static final String REGION_ID = "r2";
	
	@Autowired
	private AddressRepository repo;
	    
    @Override
    public String getRegionId() {
        return REGION_ID;
    }

	@Override
	public List<AddressDto> getAddressesByUser(String profileId) {
		LOGGER.info("Find addresses to user profile by id (email)");
		List<AddressDto> addresslist = repo.getAddresses(profileId);
		return addresslist;
	}

	@Override
	public String createAddressByUser(String profileId, AddressDto address) {
		String addresId = "";
		try {
			addresId = repo.createAddress(profileId, address);
		} catch (JSONException | JsonProcessingException e) {
			LOGGER.error("Exception createAddressesByUser ", e);
		} 
		return addresId;
	}

	@Override
	public void updateAddressByUser(String profileId, AddressDto address) {
		try {
			
			if (address == null || StringUtils.isEmpty(address.getAddressId()))
				throw new IllegalArgumentException( MsgValidation.ADDRESSID_NULL );
			
			AddressDto addressFind = this.getAddressById(profileId, address.getAddressId());
			
            BeanUtilsBean notNull  =new MyBeansUtil();
            notNull.copyProperties(addressFind, address);
	            
			repo.updateAddress(profileId, addressFind);
			
		} catch (JSONException | JsonProcessingException | IllegalAccessException | InvocationTargetException e) {
			LOGGER.error("Exception updateAddressesByUser ", e);
		} 
	}
	
	

	@Override
	public void deleteAddressByUser(String addressId, String profileId) {
		repo.deleteAddress(addressId, profileId);
	}

	
	@Override
	public AddressDto getAddressById(String profileId, String addressId) {
		LOGGER.info("Find address by id");
		AddressDto addressRespose = new AddressDto();
		
		if (addressId == null)
			return addressRespose;
		
		List<AddressDto> addresses = getAddressesByUser(profileId);
		
		AddressDto result = addresses.stream()
                .filter(address -> addressId.equals(address.getAddressId()))
                .findAny()
                .orElse(addressRespose);
		
		return result;
	}
	
}
