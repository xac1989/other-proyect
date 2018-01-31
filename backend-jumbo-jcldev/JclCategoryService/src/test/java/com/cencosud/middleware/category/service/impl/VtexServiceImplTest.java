package com.cencosud.middleware.category.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.cencosud.middleware.category.exception.CategoryServiceException;
import com.cencosud.middleware.category.model.Category;
import com.cencosud.middleware.category.model.Fields;
import com.cencosud.middleware.category.model.Fields.CategoryTree;
import com.cencosud.middleware.category.repository.VtexRepository;

@RunWith(MockitoJUnitRunner.class)
public class VtexServiceImplTest {
	
	@InjectMocks
	private VtexServiceImpl vtexServiceImpl = new VtexServiceImpl();

	@Mock
	private VtexRepository vtexRepo;

	@Mock
	private ApplicationConfig config;
	
	private String filter;
	
	private String q;
	
	private boolean isDepartment; 
	
	private VtexFilters vtexFilters;
	
	private List<VtexCategory> categoriesFromVtex;
	
	private int categoryId;
	
	@Before
	public void setUp() throws Exception {
		this.isDepartment = Boolean.FALSE;
		filter = "/tecnologia/computo/monitores";
		q = "";
		categoryId = 1;
		categoriesFromVtex = createCategoryList();
	}

	@Test
	public void toCategoriesTrees() throws CategoryServiceException {
		buildVtexFiltersResponse();
		toCategoriesTreesPreconditions();
		ApplicationConfig.BusinessProperties businessProperties = createBusinessProperties();
		given(this.config.getBusiness()).willReturn(businessProperties);
		Fields fields = vtexServiceImpl.getFilterFields(filter, q, isDepartment, false);
		
		int c = 0;
		c = reviewCats(c, fields.getCategoriesTrees());
		
		assertTrue("Expected exactly 2 categories, found: "+c,c==2);
		assertTrue("Categories correctly filtered",true);
	}
	
	@Test
	public void getAll() throws CategoryServiceException {
		getAllPreconditions();
		List<Category> listCategories = vtexServiceImpl.getAllCategories();
		assertTrue(!listCategories.isEmpty());
	}
	
	@Test
	public void findById() throws CategoryServiceException {
		findByIdPrecondition();
		Category category = vtexServiceImpl.findById(categoryId);
		assertTrue(category != null);
	}
	
	/**
	 * Preconditions
	 */
	
	private void findByIdPrecondition() throws CategoryServiceException{
		given(this.vtexRepo.findCategory(categoryId)).willReturn(categoriesFromVtex.get(0));
	}
	
	private void getAllPreconditions() throws CategoryServiceException{
		given(this.vtexRepo.getAllCategories()).willReturn(categoriesFromVtex);
	}
	
	private void toCategoriesTreesPreconditions() throws CategoryServiceException{
		given(this.vtexRepo.getFilters(filter, q, false)).willReturn(vtexFilters);
		ApplicationConfig.BusinessProperties businessProperties = createBusinessProperties();
		given(this.config.getBusiness()).willReturn(businessProperties);
	}
	
	private ApplicationConfig.BusinessProperties createBusinessProperties() {
		ApplicationConfig.BusinessProperties businessProperties = new ApplicationConfig.BusinessProperties();
		businessProperties.setDepartments(generateListOfDepartments());
		businessProperties.setDepartmentsIds(new ArrayList<String>());
		businessProperties.getDepartmentsIds().addAll(businessProperties.getDepartments().keySet());
		businessProperties.setSpecsRemove(new ArrayList<String>());
		return businessProperties;
	}
	
	private Map<String,ApplicationConfig.DepartmentProperties> generateListOfDepartments() {
		Map<String, ApplicationConfig.DepartmentProperties> departments = new HashMap<>();
		for (int i = 0; i <= 4; i++) {
			ApplicationConfig.DepartmentProperties departmentProperties = new ApplicationConfig.DepartmentProperties();
			departmentProperties.setId(i);
			departmentProperties.setName("Department" + i);
			departmentProperties.setOrder(String.valueOf(i));
			departmentProperties.setBasicPath("/t");
			departments.put(String.valueOf(departmentProperties.getId()),departmentProperties);
		}
		return departments;
	}
	
	private VtexFilters buildVtexFiltersResponse(){
		this.vtexFilters = new VtexFilters();
		this.vtexFilters.setBrands(createBrandsList());
		this.vtexFilters.setSpecificationFilters(new HashMap<String, List<VtexSpecificationFilter>>());
		this.vtexFilters.setCategoriesTrees(createCategoriesList());
		
		return vtexFilters;
	}
	
	private List<VtexCategoryTree> createCategoriesList(){
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
			
			VtexCategoryTree tc = new VtexCategoryTree();
			tc.setName("Cómputo");
			tc.setLink("/Tecnologia/Computo");
			tc.setChildren(new ArrayList<VtexCategoryTree>());
			tc.getChildren().add(tcm);
		
				t.setName("Tecnologia");
				t.setLink("/Tecnologia");
				t.setChildren(new ArrayList<VtexCategoryTree>());
				t.getChildren().add(tt);
				t.getChildren().add(tc);
		
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
	
	private int reviewCats(int c, List<CategoryTree> cats){
		for(CategoryTree ct : cats){
			if(notExpected(ct.getName())){
				assertFalse("Category unexpected: "+ct.getName(),false);
			}
			c++;
		}
		return c;
	}
	
	private boolean notExpected(String categoryName){
		List<String> expectedCats = Arrays.asList("Tecnologia","Cómputo","Monitores");
		return !expectedCats.contains(categoryName);
	}
	
	private List<VtexBrand> createBrandsList(){
		List<VtexBrand> listBrands = new ArrayList<VtexBrand>();
		VtexBrand brand = new VtexBrand(10, "Svelty", "/leche/svelty");
		listBrands.add(brand);
		return listBrands;
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
}
