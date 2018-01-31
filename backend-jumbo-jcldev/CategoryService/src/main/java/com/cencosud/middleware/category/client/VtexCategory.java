package com.cencosud.middleware.category.client;

import java.util.List;


public class VtexCategory {
	
	public VtexCategory() {
		super();
	}
	public VtexCategory(int id, String name, boolean hasChildren, String url, List<VtexCategory> children, int parentId) {
		super();
		this.id = id;
		this.name = name;
		this.hasChildren = hasChildren;
		this.url = url;
		this.children = children;
		this.parentId = parentId;
	}

	private int id;
	private String name;
	private boolean hasChildren;
	private String url;
	private List<VtexCategory> children;
	private int parentId;
	
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
	public boolean isHasChildren() {
		return hasChildren;
	}
	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}
	public List<VtexCategory> getChildren() {
		return children;
	}
	public void setChildren(List<VtexCategory> children) {
		this.children = children;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
}
