package com.cencosud.middleware.category.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;


@JsonAutoDetect
public class Category {

	private int id;
	private String name;
	private String icon;
	private String image;
	private int categoryListOrder;
	private String filter;
	private int parentId;
	private List<Category> subCategories;

	public Category() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getCategoryListOrder() {
		return categoryListOrder;
	}

	public void setCategoryListOrder(int categoryListOrder) {
		this.categoryListOrder = categoryListOrder;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public List<Category> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<Category> subCategories) {
		this.subCategories = subCategories;
	}
	
	public boolean hasSubCategories() {
		return (this.subCategories != null && this.subCategories.size() > 0);
	}

}
