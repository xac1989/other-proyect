package com.cencosud.middleware.catalog.dto.productdetail;

import java.util.List;

public class ProductWongDto extends ProductDto {

	private List<ItemsWongDto> items;
	private List<SpecificationDto> specifications;
	private String generalInfo;
	private Long categoryId;

	public String getGeneralInfo() {
		return generalInfo;
	}

	public void setGeneralInfo(String generalInfo) {
		this.generalInfo = generalInfo;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public List<ItemsWongDto> getItems() {
		return items;
	}

	public void setItems(List<ItemsWongDto> items) {
		this.items = items;
	}

	public List<SpecificationDto> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(List<SpecificationDto> specifications) {
		this.specifications = specifications;
	}

}
