package com.cencosud.middleware.category.client;

import java.util.List;
import java.util.Map;


public class VtexFilters {
	
	private List<VtexBrand> brands;
	private Map<String, List<VtexSpecificationFilter>> specificationFilters;
	private List<Map<String, Object>> departments;
	private List<VtexCategoryTree> categoriesTrees;
	
	public VtexFilters() {
		super();
	}

	public List<VtexBrand> getBrands() {
		return brands;
	}
	
	public void setBrands(List<VtexBrand> brands) {
		this.brands = brands;
	}
	
	public  Map<String, List<VtexSpecificationFilter>> getSpecificationFilters() {
		return specificationFilters;
	}
	
	public void setSpecificationFilters( Map<String, List<VtexSpecificationFilter>> specificationFilters) {
		this.specificationFilters = specificationFilters;
	}
	
	
	public List<Map<String, Object>> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Map<String, Object>> departments) {
		this.departments = departments;
	}

	public List<VtexCategoryTree> getCategoriesTrees() {
		return categoriesTrees;
	}

	public void setCategoriesTrees(List<VtexCategoryTree> categoriesTrees) {
		this.categoriesTrees = categoriesTrees;
	}






	public static class VtexBrand {
		private Integer quantity;
		private String name;
		private String link;
		
		public VtexBrand() {
			super();
		}
		public VtexBrand(Integer quantity, String name, String link) {
			super();
			this.quantity = quantity;
			this.name = name;
			this.link = link;
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
		
		
	}
	
	
	public static class VtexSpecificationFilter {
		
		private Integer quantity;
		private String name;
		private String link;
		
		public VtexSpecificationFilter() {
			super();
		}
		public VtexSpecificationFilter(Integer quantity, String name, String link) {
			super();
			this.quantity = quantity;
			this.name = name;
			this.link = link;
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

	}
	
	public static class VtexCategoryTree{
		private Integer quantity;
		private String name;
		private String link;		
		private List<VtexCategoryTree> children;
		
				
		public VtexCategoryTree() {
			super();
		}
		
		public VtexCategoryTree(Integer quantity, String name, String link, List<VtexCategoryTree> children) {
			super();
			this.quantity = quantity;
			this.name = name;
			this.link = link;
			this.children = children;
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
		public List<VtexCategoryTree> getChildren() {
			return children;
		}
		public void setChildren(List<VtexCategoryTree> children) {
			this.children = children;
		}
		
	}

	
}
