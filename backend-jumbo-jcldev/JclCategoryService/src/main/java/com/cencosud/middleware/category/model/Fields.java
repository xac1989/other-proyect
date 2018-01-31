package com.cencosud.middleware.category.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Fields {
	
	private List<Brand> brands;
	private List<Spec> specs;
	private List<CategoryTree> categoriesTrees;
	
	public Fields() {
		super();
	}

	public Fields(List<Brand> brands, List<Spec> specs) {
		super();
		this.brands = brands;
		this.specs = specs;
	}
	
	public List<Brand> getBrands() {
		return brands;
	}
	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}
	public List<Spec> getSpecs() {
		return specs;
	}
	public void setSpecs(List<Spec> spec) {
		this.specs = spec;
	}
			
	public List<CategoryTree> getCategoriesTrees() {
		return categoriesTrees;
	}

	public void setCategoriesTree(List<CategoryTree> categoriesTrees) {
		this.categoriesTrees = categoriesTrees;
	}



	public static class Value {
		private String name;
		private Integer quantity;
		
		public Value() {
			super();
		}

		public Value(String name, Integer quantity) {
			super();
			this.name = name;
			this.quantity = quantity;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}	
	}
	
	public static class Brand extends Value {
		private String brand;

		public Brand() {
			super();
		}

		public Brand(String name, Integer quantity, String link) {
			super(name, quantity);
			this.brand = link.substring(link.lastIndexOf('/') + 1);
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

	}
	
	public static class Spec {
		private String name;
		private String spec;
		private List<Value> values;
		
		 public Spec() {
			super();
		}

		public Spec(String name, String spec, List<Value> values) {
			super();
			this.name = name;
			this.spec = spec;
			this.values = values;
		}
		 
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSpec() {
			return spec;
		}
		public void setSpec(String spec) {
			this.spec = spec;
		}
		public List<Value> getValues() {
			return values;
		}
		public void setValues(List<Value> values) {
			this.values = values;
		}
		
		 

	}
	@JsonInclude(Include.NON_NULL)
	public static class CategoryTree{
		private Integer quantity;
		private String name;
		private String link;
		private String icon;
		private String icon_svg;
		
		public CategoryTree(Integer quantity, String name, String link) {
			this.quantity = quantity;
			this.name = name;
			this.link = link.toLowerCase();
		}
		
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getLink() {
			return link;
		}
		public void setLink(String link) {
			this.link = link;
		}
		
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public String getIcon_svg() {
			return icon_svg;
		}
		public void setIcon_svg(String icon_svg) {
			this.icon_svg = icon_svg;
		}
		
		public void setIcons(String serverPath, String vtexUrlImages, String vtexEnv) {
			String categoryPath = getDepartmentFilter();
			this.setIcon(String.format("%sicon-%s.%s", vtexUrlImages, categoryPath, "png"));
			this.setIcon_svg(String.format("%simages/svg/%s/%s.%s", serverPath, vtexEnv, categoryPath, "svg"));
		}

		private String getDepartmentFilter() {
			String[] filterArray = this.link.split("/");
			return filterArray.length >= 2 ? filterArray[1] : "";
		}

		@Override
		public String toString() {
			StringBuilder categoryTree = new StringBuilder();
			categoryTree.append( "CategoryTree[" );
			categoryTree.append( name );
			categoryTree.append( ":" );
			categoryTree.append( link );
			categoryTree.append( ":" );
			categoryTree.append( quantity );
			categoryTree.append( "]" );
			
			return categoryTree.toString();
		}
		
		
	}
 
}
