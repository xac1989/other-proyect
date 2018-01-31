package com.cencosud.middleware.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.category.annotation.Loggable;
import com.cencosud.middleware.category.exception.CategoryServiceException;
import com.cencosud.middleware.category.model.Category;
import com.cencosud.middleware.category.model.Fields;
import com.cencosud.middleware.category.service.CategoryMongoService;
import com.cencosud.middleware.category.service.VtexService;

@RestController	
@RequestMapping("/category")
public class MongoCategoryController {
	
	@Autowired
	CategoryMongoService mongoService;

	@Autowired
	VtexService vtexService;
	
	@Loggable
	@RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Category> getAllMongo() {
		List<Category> categories = mongoService.getAllCategories();
		return categories;
	}
	
	
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/{categoryId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Category findById(@PathVariable int categoryId) {
		Category category = mongoService.findById(categoryId);
		return category; 
	}
	
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/tree/{categoryId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Category findFullTreeById(@PathVariable int categoryId) {
		Category category = mongoService.findFullTreeById(categoryId);
		return category; 
	}
	
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/create", produces=MediaType.APPLICATION_JSON_VALUE)
	public void createAll() throws CategoryServiceException {
		mongoService.processAndPersistCategories();
	}
	
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/delete", produces=MediaType.APPLICATION_JSON_VALUE)
	public void deleteAll() {
		mongoService.deleteAll();
	}
	
	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/filter/fields", produces=MediaType.APPLICATION_JSON_VALUE)
	public Fields getFilterFields(@RequestParam(required=false) String filter, @RequestParam(required=false) String q) throws CategoryServiceException {
		
		Fields fields = null;
		if(isEmptyParameter(filter) && isEmptyParameter(q)){
			fields = mongoService.getAllCategoriesParents();
		}else{
			fields = vtexService.getFilterFields(filter, q);
		}
		return fields;
	}


	private boolean isEmptyParameter(String parameter) {
		return "".equals(parameter) || parameter == null;
	}
	
}
