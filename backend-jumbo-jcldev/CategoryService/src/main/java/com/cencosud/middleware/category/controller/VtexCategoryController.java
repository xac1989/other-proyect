package com.cencosud.middleware.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.category.annotation.Loggable;
import com.cencosud.middleware.category.exception.CategoryServiceException;
import com.cencosud.middleware.category.model.Category;
import com.cencosud.middleware.category.service.VtexService;



@RestController	
@RequestMapping("/vtex")
public class VtexCategoryController {

	
	@Autowired
	VtexService vtexService;
	
	@Loggable
	@RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Category> getAll() throws CategoryServiceException {
		List<Category> categories = vtexService.getAllCategories();
		return categories; 
	}
	
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/{categoryId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Category findById(@PathVariable int categoryId) throws CategoryServiceException {
		Category category = vtexService.findById(categoryId);
		return category; 
	}
	
	

	
}
