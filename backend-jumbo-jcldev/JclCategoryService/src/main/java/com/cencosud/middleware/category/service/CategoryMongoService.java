package com.cencosud.middleware.category.service;

import com.cencosud.middleware.category.exception.CategoryServiceException;
import com.cencosud.middleware.category.model.Category;
import com.cencosud.middleware.category.model.CategoryResponse;
import com.cencosud.middleware.category.model.Fields;

public interface CategoryMongoService {

	CategoryResponse getAllCategories();
	
	void save(Category c);
	
	Category findById(int categoryId);
	
	Category findFullTreeById(int categoryId);
	
	void deleteAll();

	void processAndPersistCategories() throws CategoryServiceException;
	
	public Fields getAllCategoriesParents() throws CategoryServiceException;
	
}
