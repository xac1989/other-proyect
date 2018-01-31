package com.cencosud.middleware.category.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cencosud.middleware.category.model.Category;

public interface CategoryRepository extends MongoRepository<Category, String>, MongoRepositoryCustom  {

}
