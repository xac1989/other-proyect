package com.cencosud.mobile.list.model;

import java.util.ArrayList;

import com.cencosud.mobile.list.util.ListUtil;

public class ShoppingListProduct extends Product {

	private String id;
	private SkuQuantity skuQuantity;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SkuQuantity getSkuQuantity() {
		return skuQuantity;
	}

	public void setSkuQuantity(SkuQuantity skuQuantity) {
		this.skuQuantity = skuQuantity;
	}

	public void updateFromProduct(Product another) {
		if (another != null) {
			this.skuId = another.skuId;
			this.productName = another.productName;
			this.productId = another.productId;
			this.description = ListUtil.copy(another.description);
			this.productReference = another.productReference;
			this.brand = another.brand;
			this.price = another.price;
			this.listPrice = another.listPrice;
			this.discountRate = another.discountRate;
			this.availableQuantity = another.availableQuantity;
			this.available = another.available;
			this.addToCartLink = another.addToCartLink;
			this.images = ListUtil.copy(another.images);
			this.salesChannel = another.salesChannel;
			this.sellerId = another.getSellerId();
			if (another.specifications != null) {
				this.specifications = new ArrayList<>();
				for (ProductSpecification specificationDto : another.specifications) {
					this.specifications.add(new ProductSpecification(specificationDto));
				}
			} else {
				this.specifications = new ArrayList<>();
			}
			if (another.skuData != null) {
				this.skuData = new ArrayList<>();
				for (ProductSkuData skuDataDto : another.skuData) {
					this.skuData.add(new ProductSkuData(skuDataDto));
				}
			} else {
				this.skuData = new ArrayList<>();
			}
			this.promotions = another.getPromotions();
			this.promotionLongDescription = another.promotionLongDescription;
			this.promotionShortDescription = another.promotionShortDescription;
		}
	}
}
