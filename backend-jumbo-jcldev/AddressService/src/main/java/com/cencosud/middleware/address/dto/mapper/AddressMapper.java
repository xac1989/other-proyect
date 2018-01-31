package com.cencosud.middleware.address.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.cencosud.middleware.address.dto.AddressDto;
import com.cencosud.middleware.address.model.Address;

@Mapper(componentModel="spring")
public interface AddressMapper {
	
	@Mappings({
			@Mapping(target = "userId", expression = "java(address.getUserId())"),
			@Mapping(target = "addressId", expression = "java(address.getAddressId())"),
			@Mapping(target = "addressName", expression = "java(address.getAddressName())"),
			@Mapping(target = "addressType", expression = "java(address.getAddressType())"),
			@Mapping(target = "postalCode", expression = "java(address.getPostalCode())"),
			@Mapping(target = "city", expression = "java(address.getCity())"),
			@Mapping(target = "country", expression = "java(address.getCountry())"),
			@Mapping(target = "state", expression = "java(address.getState())"),
			@Mapping(target = "street", expression = "java(address.getStreet())"),
			@Mapping(target = "number", expression = "java(address.getNumber())"),
			@Mapping(target = "neighborhood", expression = "java(address.getNeighborhood())"),
			@Mapping(target = "complement", expression = "java(address.getComplement())"),
			@Mapping(target = "receiverName", expression = "java(address.getReceiverName())"),
			@Mapping(target = "geoCoordinate", expression = "java(GeoCoordinateMapper.mapGeoCoordinate(address))")})
	AddressDto generateAddress(Address address);

	@Mappings({
		@Mapping(target = "userId", expression = "java(addressDto.getUserId())"),
		@Mapping(target = "addressId", expression = "java(addressDto.getAddressId())"),
		@Mapping(target = "addressName", expression = "java(addressDto.getAddressName())"),
		@Mapping(target = "addressType", expression = "java(addressDto.getAddressType())"),
		@Mapping(target = "postalCode", expression = "java(addressDto.getPostalCode())"),
		@Mapping(target = "city", expression = "java(addressDto.getCity())"),
		@Mapping(target = "country", expression = "java(addressDto.getCountry())"),
		@Mapping(target = "state", expression = "java(addressDto.getState())"),
		@Mapping(target = "street", expression = "java(addressDto.getStreet())"),
		@Mapping(target = "number", expression = "java(addressDto.getNumber())"),
		@Mapping(target = "neighborhood", expression = "java(addressDto.getNeighborhood())"),
		@Mapping(target = "complement", expression = "java(addressDto.getComplement())"),
		@Mapping(target = "receiverName", expression = "java(addressDto.getReceiverName())"),
		@Mapping(target = "geoCoordinate", expression = "java(GeoCoordinateMapper.mapGeoCoordinateBack(addressDto))")})
	Address generateAddressBack(AddressDto addressDto);
}
