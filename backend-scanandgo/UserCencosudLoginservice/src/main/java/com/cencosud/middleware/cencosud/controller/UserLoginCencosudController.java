package com.cencosud.middleware.cencosud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.cencosud.model.CencosudServiceRequest;
import com.cencosud.middleware.cencosud.model.UserProfileInfo;
import com.cencosud.middleware.cencosud.service.UserLoginCencosudService;

@RestController	
@RequestMapping(path="/login", produces="application/json; charset=UTF-8")
public class UserLoginCencosudController {
	
	@Autowired
	private UserLoginCencosudService userService;
	
	@RequestMapping(method = RequestMethod.POST)
	public UserProfileInfo loginUser(@RequestBody CencosudServiceRequest request){
		return userService.loginCencosud(request);
	}
}
