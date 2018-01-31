package com.cencosud.middleware.rewards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.rewards.annotation.Loggable;
import com.cencosud.middleware.rewards.model.RewardsResponse;
import com.cencosud.middleware.rewards.service.RewardsService;

@RestController	
@RequestMapping("/rewards")
public class RewardsController {
	
	
	@Autowired
	private RewardsService rewardsService;
	
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/{numdoc}", produces=MediaType.APPLICATION_JSON_VALUE)
	public RewardsResponse getRewards(@PathVariable String numdoc,
			@RequestParam(required = true) Integer doctype) {
		return rewardsService.getRewards(doctype, numdoc);
	}
}
