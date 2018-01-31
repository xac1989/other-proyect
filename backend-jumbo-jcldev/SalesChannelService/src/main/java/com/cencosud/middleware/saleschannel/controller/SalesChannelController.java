package com.cencosud.middleware.saleschannel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.saleschannel.annotation.Loggable;
import com.cencosud.middleware.saleschannel.dto.RegionDto;
import com.cencosud.middleware.saleschannel.dto.RegionMapping;
import com.cencosud.middleware.saleschannel.dto.SalesChannelListDto;
import com.cencosud.middleware.saleschannel.factory.ShoppingListServiceFactory;
import com.cencosud.middleware.saleschannel.model.StoresInfo;

/**
 * 
 * 
 * <h1>SalesChannelController</h1>
 * <p>
 * Metodos expuestos para el servicio de Sales Channel
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jul 21, 2017
 */

@Loggable
@RestController()
@RequestMapping(path = { "/{region}/v1/", "/{region}/v1" }, produces = "application/json; charset=UTF-8")
public class SalesChannelController {
	
	@Autowired
	private ShoppingListServiceFactory serviceFactory;
	
	/**
	 * Método para la consulta de Sale Channel
	 * @param region
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=UTF-8",value={"/salesChannels","/salesChannels/"})
	public ResponseEntity<SalesChannelListDto> getSalesChannelList(@PathVariable("region") String region) {

		SalesChannelListDto salesChannelResponse = new SalesChannelListDto(serviceFactory.getService(region).getSalesChannels());
		return new ResponseEntity<SalesChannelListDto>(salesChannelResponse, HttpStatus.OK);
	}

	/**
	 * Método para la consulta de Regiones
	 * @param region
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=UTF-8",value={"/regions","/regions/"})
	public ResponseEntity<List<RegionDto>> getStoresInfo(@PathVariable("region") String region) {
		StoresInfo storesInfo = serviceFactory.getService(region).getStoresInfo();
		RegionMapping mapping = new RegionMapping(storesInfo);

		return new ResponseEntity<List<RegionDto>>(mapping.getRegionDtoList(), HttpStatus.OK);
	}
}
