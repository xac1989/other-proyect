package com.cencosud.middleware.category.service.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.middleware.category.client.VtexCategory;
import com.cencosud.middleware.category.configuration.ApplicationConfig;
import com.cencosud.middleware.category.configuration.ApplicationConfig.VTexProperties;
import com.cencosud.middleware.category.exception.CategoryServiceException;
import com.cencosud.middleware.category.model.Category;
import com.cencosud.middleware.category.model.Fields;
import com.cencosud.middleware.category.model.Fields.Brand;
import com.cencosud.middleware.category.model.Fields.CategoryTree;
import com.cencosud.middleware.category.model.Fields.Spec;
import com.cencosud.middleware.category.repository.CategoryRepository;
import com.cencosud.middleware.category.repository.VtexRepository;
import com.cencosud.middleware.category.service.CategoryMongoService;

@RunWith(MockitoJUnitRunner.class)
public class CategoryMongoServiceImplTest {

	@InjectMocks
	CategoryMongoService categoryMongoService = new CategoryMongoServiceImpl();

	@Mock
	private VtexRepository vtexRepo;

	@Mock
	private CategoryRepository categoryRepository;

	@Mock
	private ApplicationConfig applicationConfig;

	List<VtexCategory> categoriesFromVtex;
	
	List<Category> categories;
	

	@Before
	public void setUp() {
		categoriesFromVtex = createCategoryList();
		categories = createCategories();
	}

	@Test
	public void processAndPersistCategories() throws Exception {
		processAndPersistCategoriesPreconditions();
		categoryMongoService.processAndPersistCategories();
	}

	private void processAndPersistCategoriesPreconditions() throws CategoryServiceException {
		given(this.vtexRepo.getAllCategories()).willReturn(categoriesFromVtex);
		ApplicationConfig.ServerProperties serverProperties = createServerProperties();
		given(this.applicationConfig.getServer()).willReturn(serverProperties);
		ApplicationConfig.BusinessProperties businessProperties = createBusinessProperties();
		given(this.applicationConfig.getBusiness()).willReturn(businessProperties);
		ApplicationConfig.VTexProperties vTexProperties = createVtexProperties();
		given(this.applicationConfig.getVtex()).willReturn(vTexProperties);
	}

	private VTexProperties createVtexProperties() {
		ApplicationConfig.VTexProperties vTexProperties = new VTexProperties();
		vTexProperties.setEnv("qa");
		return vTexProperties;
	}

	private ApplicationConfig.BusinessProperties createBusinessProperties() {
		ApplicationConfig.BusinessProperties businessProperties = new ApplicationConfig.BusinessProperties();
		businessProperties.setDepartments(generateListOfDepartments());
		businessProperties.setDepartmentsIds(new ArrayList<String>());
		businessProperties.getDepartmentsIds().addAll(businessProperties.getDepartments().keySet());
		return businessProperties;
	}

	private ApplicationConfig.ServerProperties createServerProperties() {
		ApplicationConfig.ServerProperties serverProperties = new ApplicationConfig.ServerProperties();
		serverProperties.setPath("path");
		return serverProperties;
	}

	private Map<String,ApplicationConfig.DepartmentProperties> generateListOfDepartments() {
		Map<String, ApplicationConfig.DepartmentProperties> departments = new HashMap<>();
		for (int i = 0; i <= 4; i++) {
			ApplicationConfig.DepartmentProperties departmentProperties = new ApplicationConfig.DepartmentProperties();
			departmentProperties.setId(i);
			departmentProperties.setName("Department" + i);
			departmentProperties.setOrder(String.valueOf(i));
			departments.put(String.valueOf(departmentProperties.getId()),departmentProperties);
		}
		return departments;
	}

	private List<VtexCategory> createCategoryList() {
		ArrayList<VtexCategory> categoriesFromVtex = new ArrayList<>();
		for (int i = 0; i <= 4; i++) {
			ArrayList<VtexCategory> children = new ArrayList<>();
			VtexCategory child = new VtexCategory(2 + i, "subcat", false, "", null, 2);
			children.add(child);
			VtexCategory category = new VtexCategory(i, "cat", true, "", children, 1);
			categoriesFromVtex.add(category);
		}
		return categoriesFromVtex;
	}
	
	@Test
	public void getAllCategoriesParents() throws CategoryServiceException {
		
		getAllCategoriesParentsPreconditions();
		Fields fields = categoryMongoService.getAllCategoriesParents();
		assertTrue( fields != null );
		
	}
	
	private void getAllCategoriesParentsPreconditions() throws CategoryServiceException{
		given(categoryRepository.findAllCustom()).willReturn(categories);
		ApplicationConfig.ServerProperties serverProperties = createServerProperties();
		given(this.applicationConfig.getServer()).willReturn(serverProperties);
		ApplicationConfig.BusinessProperties businessProperties = createBusinessProperties();
		given(this.applicationConfig.getBusiness()).willReturn(businessProperties);
	}
	
	private List<Category> createCategories(){
		List<Category> categories = new ArrayList<>();
		Category category = new Category();
		category.setId(1);
		category.setName("Tecnologia");
		category.setIcon("icono");
		category.setImage("image");
		category.setCategoryListOrder(1);
		category.setFilter("/tecnologia");
		category.setParentId(1);
		category.setSubCategories(Collections.<Category>emptyList());
		categories.add(category);
		
		Fields fields = new Fields();
		fields.setBrands(Collections.<Brand>emptyList());
		fields.setSpecs(Collections.<Spec>emptyList());
		List<CategoryTree> categoriesTrees = new ArrayList<>();
		CategoryTree cate = new CategoryTree();
		cate.setName("Tecnologia");
		cate.setLink("/tecnologia");
		cate.setQuantity(0);
		cate.setChildren(Collections.<CategoryTree>emptyList());
		fields.setCategoriesTree(categoriesTrees);
		
		return categories;
	}
}
