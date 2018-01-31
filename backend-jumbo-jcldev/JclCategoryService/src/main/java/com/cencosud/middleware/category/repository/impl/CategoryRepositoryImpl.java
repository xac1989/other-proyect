package com.cencosud.middleware.category.repository.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cencosud.middleware.category.model.Category;
import com.cencosud.middleware.category.repository.MongoRepositoryCustom;

@Repository
public class CategoryRepositoryImpl implements MongoRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(CategoryRepositoryImpl.class);

	@Autowired
	MongoTemplate template;

	@Override
	public List<Category> findAllCustom() {
		logger.info("Searching all categories from category repository");
		BasicQuery query = new BasicQuery("{ parentId : 0 }");
		List<Category> categoryList = template.find(query, Category.class);
		logger.info("Query execution finished.");
		logger.debug("Results from CategoryRepository: {}", categoryList);
		return categoryList;
	}

	@Override
	public Category findByIdCustom(int categoryId) {
		logger.info("Searching category by id");
		logger.debug("Category id: {}", categoryId);
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(categoryId));
		Category category = template.findOne(query, Category.class);
		logger.info("Query execution finished.");
		logger.debug("Result category: {}", category);
		return category;
	}

	@Override
	public Category findFullTreeByIdCustom(int categoryId) {
		logger.info("Searching full tree category by id");
		logger.debug("Category id: {}", categoryId);
		Query query = new Query();
		Criteria criteria = new Criteria();
		criteria.orOperator((Criteria.where("_id").is(categoryId)),
				(Criteria.where("subCategories._id").is(categoryId)),
				Criteria.where("subCategories.subCategories._id").is(categoryId));
		query.addCriteria(criteria);
		Category category = template.findOne(query, Category.class);
		logger.info("Query execution finished.");
		logger.debug("Result full tree category: ", category);
		return category;
	}
}
