package com.cencosud.middleware.address.dto.mapper;

import java.math.BigDecimal;

import com.cencosud.middleware.address.dto.AddressDto;
import com.cencosud.middleware.address.dto.GeoCoordinate;
import com.cencosud.middleware.address.model.Address;

public class GeoCoordinateMapper {
	
	private GeoCoordinateMapper() {
		    throw new IllegalStateException("Utility class");
	}
	
	public static final GeoCoordinate mapGeoCoordinate(Address address){

		GeoCoordinate geoCoor = new GeoCoordinate();
		
		if(address.getGeoCoordinate() != null) {
			geoCoor.setLatitude(address.getGeoCoordinate()[1]);
			geoCoor.setLongitude(address.getGeoCoordinate()[0]);
		}
		
		return geoCoor;
	}
	
	public static final BigDecimal[] mapGeoCoordinateBack(AddressDto address){

		BigDecimal[] geoCoordinate = {new BigDecimal(0), new BigDecimal(0)};
		
		if(address.getGeoCoordinate() != null) {
			geoCoordinate[0] = address.getGeoCoordinate().getLongitude();
			geoCoordinate[1] = address.getGeoCoordinate().getLatitude();
		}
		
		return geoCoordinate;
	}
	
}
