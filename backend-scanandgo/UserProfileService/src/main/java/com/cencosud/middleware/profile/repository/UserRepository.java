package com.cencosud.middleware.profile.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cencosud.middleware.profile.model.UserWhiteList;

public interface UserRepository extends MongoRepository<UserWhiteList, String>{

	@Query("{ 'email':?0 }")
	List<UserWhiteList> findByEmail(String email);
	
	@Query("{ 'rut':?0 }")
	List<UserWhiteList> findByRut(String rut);
}
