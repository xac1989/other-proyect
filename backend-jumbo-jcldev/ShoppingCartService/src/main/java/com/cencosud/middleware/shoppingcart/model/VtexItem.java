package com.cencosud.middleware.shoppingcart.model;

public class VtexItem {
	
	private String uniqueId;
	private String id;
	private String productId;
	private String refId;
	private Integer quantity;
	
	public VtexItem() {
		super();
	}

	public VtexItem(String uniqueId, String id, String productId, String refId, Integer quantity) {
		super();
		this.uniqueId = uniqueId;
		this.id = id;
		this.productId = productId;
		this.refId = refId;
		this.quantity = quantity;
	}
	
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "VtexItems [uniqueId=" + uniqueId + ", id=" + id + ", productId=" + productId + ", refId=" + refId
				+ ", quantity=" + quantity + "]";
	}
	
}
