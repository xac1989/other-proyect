package com.cencosud.middleware.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.delivery.annotation.Loggable;
import com.cencosud.middleware.delivery.factory.DeliveryServiceFactory;
import com.cencosud.middleware.delivery.model.DeliveryDto;
import com.cencosud.middleware.delivery.model.DeliveryMode;

@RestController
@RequestMapping(path = "/{region}/delivery", produces = "application/json; charset=UTF-8")
public class DeliveryController {

	@Autowired
	private DeliveryServiceFactory serviceFactory;

	@Loggable
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<DeliveryMode> sendDeliveryMode(@PathVariable("region") String region,
			@RequestParam(required = true) String profileId, @RequestBody(required = true) DeliveryDto deliveryDto) {

		if (deliveryDto == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		DeliveryMode deliveryMode = serviceFactory.getService(region).sendDeliveryMode(profileId, deliveryDto);

		return new ResponseEntity<DeliveryMode>(deliveryMode, HttpStatus.OK);
	}

}
