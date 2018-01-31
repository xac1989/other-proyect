package com.cencosud.middleware.category.service;

import java.util.List;

import com.cencosud.middleware.category.exception.CategoryServiceException;
import com.cencosud.middleware.category.model.Category;
import com.cencosud.middleware.category.model.Fields;

public interface VtexService {

	List<Category> getAllCategories() throws CategoryServiceException;
	
	Category findById(int id) throws CategoryServiceException;

	Fields getFilterFields(String filter, String q, boolean isByDepartment, boolean deals) throws CategoryServiceException;
	
}
