package com.cencosud.middleware.category.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.category.client.VtexCategory;
import com.cencosud.middleware.category.client.VtexFilters;
import com.cencosud.middleware.category.client.VtexFilters.VtexBrand;
import com.cencosud.middleware.category.client.VtexFilters.VtexCategoryTree;
import com.cencosud.middleware.category.client.VtexFilters.VtexSpecificationFilter;
import com.cencosud.middleware.category.configuration.ApplicationConfig;
import com.cencosud.middleware.category.configuration.ApplicationConfig.DepartmentProperties;
import com.cencosud.middleware.category.exception.CategoryServiceException;
import com.cencosud.middleware.category.model.Category;
import com.cencosud.middleware.category.model.Fields;
import com.cencosud.middleware.category.model.Fields.Brand;
import com.cencosud.middleware.category.model.Fields.CategoryTree;
import com.cencosud.middleware.category.model.Fields.Spec;
import com.cencosud.middleware.category.model.Fields.Value;
import com.cencosud.middleware.category.repository.VtexRepository;
import com.cencosud.middleware.category.service.VtexService;

@Service
public class VtexServiceImpl implements VtexService{

	@Autowired
	VtexRepository repo;

	@Autowired
	ApplicationConfig config;
	
	@Override
	public List<Category> getAllCategories() throws CategoryServiceException {
		
		List<VtexCategory> vtexCategories = repo.getAllCategories();
		List<Category> categories = new ArrayList<Category>(vtexCategories.size());
		for(VtexCategory currentCategory: vtexCategories){
			categories.add(new Category(currentCategory, "", ""));
		}
		return categories;
	}

	@Override
	public Category findById(int id) throws CategoryServiceException {
		return new Category(repo.findCategory(id), "", "");
	}

	@Override
	public Fields getFilterFields(String filter, String q) throws CategoryServiceException {
		Fields fields = new Fields();
		VtexFilters vtexfilters = repo.getFilters(filter,q);
		fields = buildFieldsFromVtex(vtexfilters,filter,q);
		return fields;
	}

	private Fields buildFieldsFromVtex(VtexFilters vtexfilters, String filter, String q) {
		Fields fields = new Fields();
		
		List<Brand> brands = toBrands(vtexfilters.getBrands());
		fields.setBrands(brands);
		
		List<Spec> specs = toSpecs(vtexfilters.getSpecificationFilters());
		fields.setSpecs(specs);
		
		List<CategoryTree> categoriesTrees = toCategoriesTrees(vtexfilters.getCategoriesTrees(),filter,q);
		fields.setCategoriesTree(categoriesTrees);
		
		return fields;
	}
	

	private List<CategoryTree> toCategoriesTrees(List<VtexCategoryTree> vtexCategoriesTrees, String filter, String q) {
		List<CategoryTree> categoriesTrees = new ArrayList<>();
		for(VtexCategoryTree vtexCategoryTree : vtexCategoriesTrees){
			if(this.isAllowed(vtexCategoryTree,filter,q)){
				CategoryTree categoryTree = new CategoryTree();
				categoryTree.setLink(removeQueryFromLink(vtexCategoryTree.getLink(),q));
				categoryTree.setName(vtexCategoryTree.getName());
				categoryTree.setQuantity(vtexCategoryTree.getQuantity());
				categoryTree.setChildren(toCategoriesTrees(vtexCategoryTree.getChildren(),filter,q));
				categoriesTrees.add(categoryTree);
			}
		}
		return categoriesTrees;
	}

	private boolean isAllowed(VtexCategoryTree tree, String filter, String q) {     
		Boolean res = null; 
		if ("".equals(filter) || filter == null) {         
			res = isAllowedCategoryTree(tree);     
			} 
		else {         
			String link = removeQueryFromLink(tree.getLink(), q);         
			res = isAllowedCategoryTree(tree) || isFilterRelated(link, filter);     
			} 
		
		return res;
		}
	
	private boolean isFilterRelated(String link, String filter) {
		 if(link.toLowerCase().startsWith(filter.toLowerCase())){
			 return true;
		 }
		 return false;
	}

	private String removeQueryFromLink(String link, String q) {
		if(q==null || "".equals(q)){
			return link;
		}
		return link.substring(0,link.lastIndexOf("/"));
	}
	
	private List<Spec> toSpecs(Map<String, List<VtexSpecificationFilter>> vtexSpecs) {
		List<Spec> specs = new ArrayList<>();
		for(String vtexSpecKey : vtexSpecs.keySet()){
			Spec spec = new Spec();
			spec.setName(vtexSpecKey);
			
			List<Value> values = new ArrayList<>();
			for(VtexSpecificationFilter vtexSpec : vtexSpecs.get(vtexSpecKey)){
				spec.setSpec(getSpecIdFromLink(vtexSpec.getLink())); //TODO: Do it only once
				if(!vtexSpec.getName().isEmpty() && this.isAllowedSpec(vtexSpec)){
					values.add(new Value(vtexSpec.getName(),vtexSpec.getQuantity()));
				}
			}
			spec.setValues(values);
			
			if(!spec.getValues().isEmpty()){
				specs.add(spec);
			}
		}
		return specs;
	}

	private List<Brand> toBrands(List<VtexBrand> vtexBrands) {
		
		List<Brand> brands = new ArrayList<Brand>(vtexBrands.size());
		for(VtexBrand vtexBrand : vtexBrands){
			
			if(this.isAllowedBrand(vtexBrand)){
				Brand brand = new Brand(vtexBrand.getName(),vtexBrand.getQuantity());
				brands.add(brand);
			}
		}
		return brands;
	}

	private boolean isAllowedLink(String link){
		for(String currentAllowedDepartment: this.config.getBusiness().getDepartmentsIds()){
			DepartmentProperties prop = this.config.getBusiness().getDepartments().get(currentAllowedDepartment);
			if(link.toLowerCase().startsWith(prop.getBasicPath())){
				return true;
			}
		}
		return false;
	}
	private boolean isAllowedBrand(VtexBrand brand){
		return this.isAllowedLink(brand.getLink());
		
	}
	
	private boolean isAllowedSpec(VtexSpecificationFilter spec){
		return this.isAllowedLink(spec.getLink());
	}
	
	
	private boolean isAllowedCategoryTree(VtexCategoryTree tree){
		return this.isAllowedLink(tree.getLink().toLowerCase());
	}
	
	
	private String getSpecIdFromLink(String link) {
		String specNum = link.substring(link.lastIndexOf("_")+1);
		return specNum;
	}
	
}
