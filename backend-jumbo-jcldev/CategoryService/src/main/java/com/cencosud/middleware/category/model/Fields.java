package com.cencosud.middleware.category.model;

import java.util.List;


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
	
	public static class Brand extends Value{

		public Brand() {
			super();
		}

		public Brand(String name, Integer quantity) {
			super(name, quantity);
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
	
	public static class CategoryTree{
		private Integer quantity;
		private String name;
		private String link;		
		private List<CategoryTree> children;
		
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
		public List<CategoryTree> getChildren() {
			return children;
		}
		public void setChildren(List<CategoryTree> children) {
			this.children = children;
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
			categoryTree.append( ":num hijos:" );
			categoryTree.append( children == null ? "" : children.size() );
			categoryTree.append( "]" );
			
			return categoryTree.toString();
		}
		
		
	}
 
}
