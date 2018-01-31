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
import com.cencosud.middleware.category.client.VtexFilters;
import com.cencosud.middleware.category.client.VtexFilters.VtexBrand;
import com.cencosud.middleware.category.client.VtexFilters.VtexCategoryTree;
import com.cencosud.middleware.category.client.VtexFilters.VtexSpecificationFilter;
import com.cencosud.middleware.category.configuration.ApplicationConfig;
import com.cencosud.middleware.category.configuration.ApplicationConfig.VTexProperties;
import com.cencosud.middleware.category.exception.CategoryServiceException;
import com.cencosud.middleware.category.model.Category;
import com.cencosud.middleware.category.model.CategoryResponse;
import com.cencosud.middleware.category.model.Fields;
import com.cencosud.middleware.category.model.Fields.Brand;
import com.cencosud.middleware.category.model.Fields.CategoryTree;
import com.cencosud.middleware.category.model.Fields.Spec;
import com.cencosud.middleware.category.repository.CategoryRepository;
import com.cencosud.middleware.category.repository.VtexRepository;
import com.cencosud.middleware.category.service.CategoryMongoService;
import com.cencosud.middleware.category.service.VtexService;

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
	
	@InjectMocks
	VtexService vtexService = new VtexServiceImpl();
	
	List<VtexCategory> categoriesFromVtex;
	
	List<Category> categories;
	
	private VtexFilters vtexFilters;
	

	@Before
	public void setUp() {
		categoriesFromVtex = createCategoryList();
		categories = createCategories();
	}

	@Test
	public void createAll() throws Exception {
		processAndPersistCategoriesPreconditions();
		categoryMongoService.processAndPersistCategories();
	}
	
	@Test
	public void getAllCategoriesParents() throws CategoryServiceException {
		getAllCategoriesParentsPreconditions();
		Fields fields = categoryMongoService.getAllCategoriesParents();
		assertTrue( fields != null );
		
	}
	
	@Test
	public void getAllCategories() throws CategoryServiceException {
		getAllCategoriesParentsPreconditions();
		CategoryResponse categoryResponse = categoryMongoService.getAllCategories();
		assertTrue( categoryResponse != null );
		
	}
	
	@Test
	public void findCategoryById() throws CategoryServiceException {
		getFindCategoryByIdPreconditions();
		Category category = categoryMongoService.findById(1);
		assertTrue( category != null );
	}
	
	@Test
	public void findFullTreeById() throws CategoryServiceException {
		getFindFullTreeByIdCustomPreconditions();
		Category category = categoryMongoService.findFullTreeById(1);
		assertTrue( category != null );
	}
	
	@Test
	public void deleteAll() throws CategoryServiceException {
		categoryMongoService.deleteAll();
	}
	
	@Test
	public void getFilterFields() throws CategoryServiceException {
		ApplicationConfig.BusinessProperties businessProperties = createBusinessProperties();
		given(this.applicationConfig.getBusiness()).willReturn(businessProperties);
		this.vtexFilters = buildVtexFiltersResponse();
		getFilterFieldsPreconditions();
		Fields fields = vtexService.getFilterFields("/Leche", "Leche", false, false);
		assertTrue(fields.getBrands().size()!=0);
	}
	
	@Test(expected = CategoryServiceException.class)
	public void getFilterFieldsException() throws CategoryServiceException{
		this.vtexFilters = buildVtexFiltersResponse();
		getFilterFieldsPreconditionsExc();
		Fields fields = vtexService.getFilterFields("/Leche", "Leche", false, false);
		assertTrue(fields.getBrands().size()!=0);
	}
	

	/***
	 * Preconditions
	 */
	
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
		businessProperties.setSpecsRemove(new ArrayList<String>());
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
			departmentProperties.setBasicPath("/l");
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
	
	private void getAllCategoriesParentsPreconditions() throws CategoryServiceException{
		given(categoryRepository.findAllCustom()).willReturn(categories);
		ApplicationConfig.ServerProperties serverProperties = createServerProperties();
		given(this.applicationConfig.getServer()).willReturn(serverProperties);
		ApplicationConfig.BusinessProperties businessProperties = createBusinessProperties();
		given(this.applicationConfig.getBusiness()).willReturn(businessProperties);
		ApplicationConfig.VTexProperties vTexProperties = createVtexProperties();
		given(this.applicationConfig.getVtex()).willReturn(vTexProperties);
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
		CategoryTree cate = new CategoryTree(0,"Tecnologia","/tecnologia");
		cate.setName("Tecnologia");
		cate.setLink("/tecnologia");
		cate.setQuantity(0);
		fields.setCategoriesTree(categoriesTrees);
		
		return categories;
	}
	
	private void getFindCategoryByIdPreconditions() throws CategoryServiceException{
		given(categoryRepository.findByIdCustom(1)).willReturn(categories.get(0));
	}
	
	private void getFindFullTreeByIdCustomPreconditions() throws CategoryServiceException{
		given(categoryRepository.findFullTreeByIdCustom(1)).willReturn(categories.get(0));
	}

	private void getFilterFieldsPreconditions() throws CategoryServiceException{
		given(this.vtexRepo.getFilters("/Leche", "Leche", false)).willReturn(vtexFilters);
		ApplicationConfig.BusinessProperties businessProperties = createBusinessProperties();
		given(this.applicationConfig.getBusiness()).willReturn(businessProperties);
	}
	
	@SuppressWarnings("unchecked")
	private void getFilterFieldsPreconditionsExc() throws CategoryServiceException{
		given(this.vtexRepo.getFilters("/Leche", "Leche", false)).willThrow(CategoryServiceException.class);
		ApplicationConfig.BusinessProperties businessProperties = createBusinessProperties();
		given(this.applicationConfig.getBusiness()).willReturn(businessProperties);
	}
	
	private VtexFilters buildVtexFiltersResponse() throws CategoryServiceException{
		vtexFilters = new VtexFilters();
		vtexFilters.setBrands(createBrandsList());
		vtexFilters.setSpecificationFilters(new HashMap<String, List<VtexSpecificationFilter>>());
		vtexFilters.setCategoriesTrees(createCategoriesList());
		
		return vtexFilters;
	}
	
	private List<VtexBrand> createBrandsList(){
		List<VtexBrand> listBrands = new ArrayList<VtexBrand>();
		VtexBrand brand = new VtexBrand(10, "Svelty", "/leche/svelty");
		listBrands.add(brand);
		return listBrands;
	}
	
	private List<VtexCategoryTree> createCategoriesList() throws CategoryServiceException{
		processAndPersistCategoriesPreconditions();
		List<VtexCategoryTree> list = new ArrayList<>();
		
		VtexCategoryTree t = new VtexCategoryTree();
		
		VtexCategoryTree tcm = new VtexCategoryTree();
		tcm.setName("Monitores");
		tcm.setLink("/Tecnologia/Computo/Monitores");
		tcm.setChildren(new ArrayList<VtexCategoryTree>());
		
		VtexCategoryTree tl = new VtexCategoryTree();
		tl.setName("LED");
		tl.setLink("/Tecnologia/Televisores/LED");
		tl.setChildren(new ArrayList<VtexCategoryTree>());
			
			VtexCategoryTree tt = new VtexCategoryTree();
			tt.setName("Televisores");
			tt.setLink("/Tecnologia/Televisores");
			tt.setChildren(new ArrayList<VtexCategoryTree>());
			tt.getChildren().add(tl);
			
				t.setName("Tecnologia");
				t.setLink("/Tecnologia");
				t.setChildren(new ArrayList<VtexCategoryTree>());
				t.getChildren().add(tt);
		
		VtexCategoryTree ewc = new VtexCategoryTree();
		ewc.setName("Computo-y-Celulares");
		ewc.setLink("/Especial/Wong-en-Casa/Computo-y-Celulares");
		ewc.setChildren(new ArrayList<VtexCategoryTree>());
		
			VtexCategoryTree ew = new VtexCategoryTree();
			ew.setName("Wong-en-Casa");
			ew.setLink("/Especial/Wong-en-Casa");
			ew.setChildren(new ArrayList<VtexCategoryTree>());
			ew.getChildren().add(ewc);
		
				VtexCategoryTree e = new VtexCategoryTree();
				e.setName("Especial");
				e.setLink("/Especial");
				e.setChildren(new ArrayList<VtexCategoryTree>());
				e.getChildren().add(ew);
				
		VtexCategoryTree i = new VtexCategoryTree();
		i.setName("Inactivos");
		i.setLink("/Inactivos");
		i.setChildren(new ArrayList<VtexCategoryTree>());
		
		list.add(t);
		list.add(e);
		list.add(i);
				
		return list;
	}
}
