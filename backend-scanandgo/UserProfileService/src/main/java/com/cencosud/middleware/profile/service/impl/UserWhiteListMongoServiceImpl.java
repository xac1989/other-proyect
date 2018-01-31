package com.cencosud.middleware.profile.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.profile.model.UserWhiteList;
import com.cencosud.middleware.profile.repository.UserRepository;
import com.cencosud.middleware.profile.service.UserWhiteListMongoService;

@Service
public class UserWhiteListMongoServiceImpl implements UserWhiteListMongoService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserWhiteList findUserByEmail(String email) {
		List<UserWhiteList> lstUserWhiteList = userRepository.findByEmail(email);
		if(lstUserWhiteList != null && !lstUserWhiteList.isEmpty()){
			return lstUserWhiteList.get(0);
		}
		return null;
	}

	@Override
	public UserWhiteList findUserByRut(String rut) {
		List<UserWhiteList> lstUserWhiteList = userRepository.findByRut(rut);
		if(lstUserWhiteList != null && !lstUserWhiteList.isEmpty()){
			return lstUserWhiteList.get(0);
		}
		return null;
	}

}
