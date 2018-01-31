package com.cencosud.middleware.profile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cencosud.middleware.profile.model.WongUserProfile;

public interface UserProfileRepository extends MongoRepository<WongUserProfile, String>{

}
