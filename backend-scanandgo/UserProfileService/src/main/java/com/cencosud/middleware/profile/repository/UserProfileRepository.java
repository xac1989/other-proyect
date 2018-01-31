package com.cencosud.middleware.profile.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cencosud.middleware.profile.model.UserProfile;

public interface UserProfileRepository extends MongoRepository<UserProfile, String>{

}
