package com.cencosud.middleware.category.repository;

import java.util.List;

import com.cencosud.middleware.category.client.VtexCategory;
import com.cencosud.middleware.category.client.VtexFilters;
import com.cencosud.middleware.category.exception.CategoryServiceException;

public interface VtexRepository {

	List<VtexCategory> getAllCategories() throws CategoryServiceException;
	
	VtexCategory findCategory(int id) throws CategoryServiceException;

	VtexFilters getFilters(String filter, String q) throws CategoryServiceException;
	
}
