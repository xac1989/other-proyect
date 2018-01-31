package com.cencosud.middleware.category.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.cencosud.middleware.category.client.VtexCategory;

public class Category {

	@Id
	private int id;
	private String name;
	private String icon;
	private String icon_pdf;
	private String icon_svg;
	private String image;
	private int categoryListOrder;
	private String filter;
	private int parentId;
	private List<Category> subCategories;
	private boolean food;

	public Category() {
	}
	
	public Category(VtexCategory category, String serverPath, String vtexEnv, String vtexUrlImages) {
		this.id = category.getId();
		this.name = category.getName();
		
		this.parentId = category.getParentId();
		
		try {
			URL filterUrl = new URL(category.getUrl());
			this.filter = filterUrl.getPath();
		} catch (MalformedURLException e) {
			this.filter = "";
		}
		
		String categoryPath = getDepartmentFilter();
		this.setIcon( String.format("%sicon-%s.%s", vtexUrlImages, categoryPath, ExtensionEnum.EXT_PNG.getExtensionCode()) );
		this.setIcon_svg( String.format("%simages/svg/%s/%s.%s", serverPath, vtexEnv, categoryPath, ExtensionEnum.EXT_SVG.getExtensionCode()) );
		this.setImage( String.format("%sbanner-%s.%s", vtexUrlImages, categoryPath, ExtensionEnum.EXT_JPG.getExtensionCode() ) );
		
		
		if (category.getChildren() == null) {
			category.setChildren(new ArrayList<VtexCategory>());
		}
		List<Category> subCategories = new ArrayList<Category>(category.getChildren().size());
		for (VtexCategory currentCat : category.getChildren()) {
			subCategories.add(new Category(currentCat, serverPath, vtexEnv, vtexUrlImages));
		}
		this.subCategories = subCategories;
		
	}
	
	private String getDepartmentFilter(){
		String[] filterArray = this.filter.split("/");
		return filterArray.length>=2 ? filterArray[1] : "";
	}

	public boolean isFood() {
		return food;
	}

	public void setFood(boolean food) {
		this.food = food;
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
	
	public String getIcon_pdf() {
		return icon_pdf;
	}

	public void setIcon_pdf(String icon_pdf) {
		this.icon_pdf = icon_pdf;
	}
	
	public String getIcon_svg() {
		return icon_svg;
	}

	public void setIcon_svg(String icon_svg) {
		this.icon_svg = icon_svg;
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
