package com.cencosud.middleware.category.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cencosud.middleware.category.client.VtexCategory;
import com.cencosud.middleware.category.configuration.ApplicationConfig;
import com.cencosud.middleware.category.configuration.ApplicationConfig.DepartmentProperties;
import com.cencosud.middleware.category.exception.CategoryServiceException;
import com.cencosud.middleware.category.model.Category;
import com.cencosud.middleware.category.model.CategoryResponse;
import com.cencosud.middleware.category.model.Deals;
import com.cencosud.middleware.category.model.ExtensionEnum;
import com.cencosud.middleware.category.model.Fields;
import com.cencosud.middleware.category.model.Fields.Brand;
import com.cencosud.middleware.category.model.Fields.CategoryTree;
import com.cencosud.middleware.category.model.Fields.Spec;
import com.cencosud.middleware.category.repository.CategoryRepository;
import com.cencosud.middleware.category.repository.VtexRepository;
import com.cencosud.middleware.category.service.CategoryMongoService;

@Service
public class CategoryMongoServiceImpl implements CategoryMongoService {

	private static final Logger logger = LoggerFactory.getLogger(CategoryMongoServiceImpl.class);
	
	static final Integer DEFAULT_QUANTITY = 0;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ApplicationConfig applicationConfig;

	@Autowired
	private VtexRepository vtexRepo;
	
	@Override
	public CategoryResponse getAllCategories() {
		logger.debug("Get all categories from MongoService");
		
		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse.setDeals(getDeals());
		categoryResponse.setCategories(categoryRepository.findAllCustom());
		
		return categoryResponse;
	}

	@Override
	public void save(Category c) {
		logger.debug("Save category from MongoService");
		categoryRepository.save(c);
	}

	@Override
	public Category findById(int categoryId) {
		logger.debug("Get category from MongoService by categoryId: "+categoryId);
		return categoryRepository.findByIdCustom(categoryId);
	}

	@Override
	public void deleteAll() {
		logger.debug("Delete all categories from MongoService");
		categoryRepository.deleteAll();
	}

	@Override
	public void processAndPersistCategories() throws CategoryServiceException {
		
		logger.debug("Delete categories");
		categoryRepository.deleteAll();
		
		List<VtexCategory> categoriesFromVtex = vtexRepo.getAllCategories();
		List<Category> categories = new ArrayList<Category>(categoriesFromVtex.size());


		for (VtexCategory vtexCategory : categoriesFromVtex) {
			Category category = new Category(vtexCategory, applicationConfig.getServer().getPath(), applicationConfig.getVtex().getEnv(),
					applicationConfig.getVtex().getUrlImages());
			if (mergeWithCategoryAllowed(vtexCategory, category)) {
				logger.debug("The Merge category method was called");
				categories.add(category);
			}
		}
		logger.debug("Call Pre-Saving categories");
		categories = this.preSaveCategories(categories, 0);

		logger.debug("Call persist categories");
		this.persistCategories(categories, 0);
	}

	private void persistCategories(List<Category> categories, int parentId) {
		for (Category category : categories) {
			category.setParentId(parentId);
			categoryRepository.save(category);
			logger.debug("category parent: " + category);
			if (category.hasSubCategories()) {
				logger.debug("Persisting subccategories : " + category.getSubCategories() + " id: " + category.getId());
				this.persistCategories(category.getSubCategories(), category.getId());
			}
		}
	}

	@Override
	public Category findFullTreeById(int categoryId) {
		logger.debug("Find tree by id");
		return categoryRepository.findFullTreeByIdCustom(categoryId);
	}

	private List<Category> preSaveCategories(List<Category> categories, int parentId) {
		logger.debug("Pre-Saving categories");
		List<Category> result = new ArrayList<Category>();
		for (Category currentCategory : categories) {
			currentCategory.setParentId(parentId);
			logger.debug("subcategories: " + currentCategory.getSubCategories());
			currentCategory.setSubCategories(preSaveCategories(currentCategory.getSubCategories(), currentCategory.getId()));
			result.add(currentCategory);
		}
		return result;
	}

	private boolean mergeWithCategoryAllowed(VtexCategory vtexCategory, Category category) {
		logger.debug("Going through the departments");
		for (String currentDepaertmentKey : this.applicationConfig.getBusiness().getDepartmentsIds()) {
			DepartmentProperties allowedDepartment = this.applicationConfig.getBusiness().getDepartments().get(currentDepaertmentKey);
			if(allowedDepartment != null){
				if (allowedDepartment.getId() == vtexCategory.getId()) {
					logger.debug("Merging with the allowed category");
//					String iconPath = applicationConfig.getServer().getPath() + "images/" + vtexCategory.getId() + ".png";
//					String imagePath = applicationConfig.getServer().getPath() + "images/" + vtexCategory.getId() + "_background.png";
//					category.setIcon(iconPath);
//					category.setImage(imagePath);
					category.setName(allowedDepartment.getName());
					category.setCategoryListOrder( Integer.parseInt(allowedDepartment.getOrder()) );
					category.setFood(allowedDepartment.isFood());
					return true;
				}
			}
		}
		logger.debug("Not merged category");
		return false;
	}

	@Override
	public Fields getAllCategoriesParents() throws CategoryServiceException{
		
		Fields fields = new Fields();
		
		logger.debug("Get all categories from MongoDB");
		List<Category> categories = this.getAllCategories().getCategories();
		
		if (!CollectionUtils.isEmpty(categories)) {
			logger.debug("Transforming Category to CategoryTree");

			CategoryTree catTree;
//			List<CategoryTree> children = Collections.<CategoryTree>emptyList();
			List<CategoryTree> categoriesTrees = new ArrayList<>();

			for (Category cate : categories) {
				catTree = new CategoryTree(DEFAULT_QUANTITY,cate.getName(),cate.getFilter());
				categoriesTrees.add(catTree);
			}

			List<Brand> brands = Collections.emptyList();
			List<Spec> specs = Collections.emptyList();

			fields.setBrands( brands );
			fields.setSpecs( specs );
			fields.setCategoriesTree( categoriesTrees );
			logger.debug("Results: {}", categoriesTrees);
		}
		return fields;
	}

	private Deals getDeals() {
		Deals deals = new Deals();
		deals.setName("Ofertas");
		deals.setIcon( String.format("%sicon-%s.%s", applicationConfig.getVtex().getUrlImages(),applicationConfig.getBusiness().getDepartmentDeals(),
				 ExtensionEnum.EXT_PNG.getExtensionCode()) );
		deals.setIcon_svg( String.format("%simages/svg/%s/%s.%s", applicationConfig.getServer().getPath(), applicationConfig.getVtex().getEnv(),
				applicationConfig.getBusiness().getDepartmentDeals(), ExtensionEnum.EXT_SVG.getExtensionCode()) );
		deals.setImage("");
		return deals;
	}
}
