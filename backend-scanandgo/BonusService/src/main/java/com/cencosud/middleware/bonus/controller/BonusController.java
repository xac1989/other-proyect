package com.cencosud.middleware.bonus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.bonus.annotation.Loggable;
import com.cencosud.middleware.bonus.model.BonusResponse;
import com.cencosud.middleware.bonus.service.BonusService;

/**
 * 
 * 
 * <h1>BonusController</h1>
 * <p>
 * Servicios expuestos para el uso de mobile
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since May 31, 2017
 */
@RestController	
@RequestMapping("/bonus")
public class BonusController {
	
	
	@Autowired
	private BonusService bonusService;
	
	/**
	 * Recupera la informacion de puntos del usuario
	 * @param doctype {@link String}
	 * @param numdoc {@link String}
	 * @return {@link BonusResponse}
	 */
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/{doctype}", produces=MediaType.APPLICATION_JSON_VALUE)
	public BonusResponse getBonus(@PathVariable String doctype,
			@RequestParam(required = true) String numdoc) {
		return bonusService.getBonus(doctype, numdoc);
	}
}
