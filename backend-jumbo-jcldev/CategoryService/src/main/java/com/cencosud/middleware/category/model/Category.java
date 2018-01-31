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

	public Category() {
	}
	
	public Category(VtexCategory category, String serverPath, String vtexEnv) {
		this.id = category.getId();
		this.name = category.getName();
		if (category.getChildren() == null) {
			category.setChildren(new ArrayList<VtexCategory>());
		}
		List<Category> subCategories = new ArrayList<Category>(category.getChildren().size());
		for (VtexCategory currentCat : category.getChildren()) {
			subCategories.add(new Category(currentCat, serverPath, vtexEnv));
		}
		this.subCategories = subCategories;
		this.parentId = category.getParentId();
		this.setIcon(getIconPath(serverPath, vtexEnv, this.id, "png"));
		this.setIcon_pdf(getIconPath(serverPath, vtexEnv, this.id, "pdf"));
		this.setIcon_svg(getIconPath(serverPath, vtexEnv, this.id, "svg"));
		this.setImage(serverPath + "images/logo_background.png");
		try {
			URL filterUrl = new URL(category.getUrl());
			this.filter = filterUrl.getPath();
		} catch (MalformedURLException e) {
			this.filter = "";
		}
	}

	private String getIconPath(String serverPath, String  vtexEnv, int id, String format) {
		StringBuilder sb = new StringBuilder();
		sb.append(serverPath);
		
		if(format == null){
			sb.append("images/logo.png");
		}else{
			sb.append("images/");
			sb.append(format);
			sb.append("/");
			sb.append(vtexEnv);
			sb.append("/");
			sb.append(id);
			sb.append(".");
			sb.append(format);
		}
		
		return sb.toString();
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
