package com.cencosud.middleware.catalog.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.cencosud.middleware.catalog.client.VtexHighlight;
import com.cencosud.middleware.catalog.client.VtexProduct;
import com.cencosud.middleware.catalog.client.VtexPromotion;
import com.cencosud.middleware.catalog.client.VtexSkuData;
import com.cencosud.middleware.catalog.dto.productdetail.HighlightDto;
import com.cencosud.middleware.catalog.dto.productdetail.PromotionDto;
import com.cencosud.middleware.catalog.dto.productdetail.SkuDataDto;
import com.cencosud.middleware.catalog.dto.search.SearchProductJumboDto;
import com.cencosud.middleware.catalog.dto.search.SearchResultJumboDto;
import com.cencosud.middleware.catalog.model.SearchResult;

@Mapper(componentModel="spring")
public interface SearchProductJumboMapper extends SearchProductMapper {
	
	SearchResultJumboDto generateSearchResult(SearchResult searchResult);

	@Mappings({
			@Mapping(target = "price", expression = "java(PriceMapper.mapPrice(vtexProduct))"),
			@Mapping(target = "listPrice", expression = "java(PriceMapper.mapListPrice(vtexProduct))"),
			@Mapping(target = "availableQuantity", expression = "java(vtexProduct.getItems().get(0).getSellers().get(0).getCommertialOffer().getAvailableQuantity())"),
			@Mapping(target = "images", expression = "java(vtexProduct.getItems().get(0).getImagesURLList())"),
			@Mapping(target = "available", expression = "java(vtexProduct.getItems().get(0).getSellers().get(0).getCommertialOffer().getAvailableQuantity()>0)"),
			@Mapping(target = "discountRate", expression = "java(vtexProduct.getItems().get(0).getSellers().get(0).getCommertialOffer().getDiscountRate())"),
			@Mapping(target = "skuId", expression = "java(vtexProduct.getItems().get(0).getItemId())"),
			@Mapping(target = "sellerId", expression = "java(vtexProduct.getItems().get(0).getSellers().get(0).getSellerId())"),
			@Mapping(target = "addToCartLink", expression = "java(vtexProduct.getItems().get(0).getSellers().get(0).getAddToCartLink())"),
			@Mapping(target = "promotionShortDescription", expression = "java(vtexProduct.getPromotionShortDescription())"),
			@Mapping(target = "promotionLongDescription", expression = "java(vtexProduct.getPromotionDescription())")})
	SearchProductJumboDto generateSearchProduct(VtexProduct vtexProduct);

	@Mappings({
		@Mapping(target = "cartLimit", expression = "java(vtexSkuData.getCart_limit())"),
		@Mapping(target = "allowNotes", expression = "java(vtexSkuData.getAllow_notes())"),
		@Mapping(target = "allowSubstitute", expression = "java(vtexSkuData.getAllow_substitute())"),
		@Mapping(target = "measurementUnit", expression = "java(vtexSkuData.getMeasurement_unit())"),
		@Mapping(target = "unitMultiplier", expression = "java(vtexSkuData.getUnit_multiplier())"),
		@Mapping(target = "promotions", expression = "java(vtexSkuData.getPromotions())"),
		@Mapping(target = "measurementUnitUn", expression = "java(vtexSkuData.getMeasurement_unit_un())"),
		@Mapping(target = "unitMultiplierUn", expression = "java(vtexSkuData.getUnit_multiplier_un())"),
		@Mapping(target = "measurementUnitSelector", expression = "java(vtexSkuData.getMeasurement_unit_selector())"),
	})
	SkuDataDto generateSkuData(VtexSkuData vtexSkuData);
	
	HighlightDto generateHighlight(VtexHighlight vtexHighlight);
	
	PromotionDto generatePromotion(VtexPromotion vtexPromotion);
}
