package com.cencosud.middleware.category.repository;

import java.util.List;

import com.cencosud.middleware.category.model.Category;

public interface MongoRepositoryCustom  {

	List<Category> findAllCustom();

	Category findByIdCustom(int id);
	
	Category findFullTreeByIdCustom(int id);
}
