package com.cencosud.middleware.address.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.address.annotation.Loggable;
import com.cencosud.middleware.address.dto.AddressDto;
import com.cencosud.middleware.address.exception.AddressException;
import com.cencosud.middleware.address.model.Address;
import com.cencosud.middleware.address.service.AddressService;

@RestController
@RequestMapping(path = "/{region}/addresses", produces = "application/json; charset=UTF-8")
public class AddressController {

	@Autowired
	AddressService service;

	@Loggable
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AddressDto>> findAddressesByUser(@PathVariable("region") String region,
			@RequestParam(required = true) String profileId) throws AddressException {
		
		return getAddressesByUser(profileId);
	}

	private ResponseEntity<List<AddressDto>> getAddressesByUser(String profileId) {
		List<AddressDto> addresses = service.getAddressesByUser(profileId);
		
		if (addresses.isEmpty()) 
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<AddressDto>>(addresses, HttpStatus.OK);
	}

	@Loggable
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAddressById(@PathVariable("region") String region,
			@RequestParam(required = true) String profileId,
			@RequestParam(required = true) String addressId) {
		
		service.deleteAddressByUser(addressId, profileId);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
		
	@Loggable
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AddressDto> saveAddressesByUser(@PathVariable("region") String region,
			@RequestParam(required = true) String profileId,
			@Valid @RequestBody(required = true) AddressDto address,
			Errors errors) {

		if (errors.hasErrors()) {
			throw new IllegalArgumentException( this.getValidationErrors(errors) );
        }
		String addresId = service.createAddressByUser(profileId, address);
		
		return getAddressesById(profileId, addresId);
	}
	
	@Loggable
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<AddressDto> updateAddressesByUser(@PathVariable("region") String region,
			@RequestParam(required = true) String profileId,
			@RequestBody(required = true) AddressDto address) {

		service.updateAddressByUser(profileId, address);
		
		return getAddressesById(profileId, address.getAddressId());
	}
	
	@Loggable
	private ResponseEntity<AddressDto> getAddressesById(String profileId, String addressId) {
		AddressDto address = service.getAddressById(profileId, addressId);
		
		if (address == null) 
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<AddressDto>(address, HttpStatus.OK);
	}
	
	@Loggable
	private String getValidationErrors(Errors errors) {
		String messageErrors = (errors.getAllErrors()
				.stream()
				.map(x1 -> x1.getDefaultMessage())
				.collect(Collectors.joining(",")));
		
		return messageErrors;
	}
}
