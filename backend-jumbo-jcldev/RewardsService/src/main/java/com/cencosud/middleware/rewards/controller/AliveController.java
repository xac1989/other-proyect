package com.cencosud.middleware.rewards.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController	
@RequestMapping("/isAlive")
public class AliveController {
	
	private static final Logger logger = LoggerFactory.getLogger(AliveController.class);

	@RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Boolean isAlive() {
		logger.info("Checking if service is alive");
		return true;
	}
}
