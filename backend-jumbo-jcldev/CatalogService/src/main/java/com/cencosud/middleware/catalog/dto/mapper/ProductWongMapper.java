package com.cencosud.middleware.catalog.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.cencosud.middleware.catalog.client.VtexCommertialOffer;
import com.cencosud.middleware.catalog.client.VtexImage;
import com.cencosud.middleware.catalog.client.VtexItem;
import com.cencosud.middleware.catalog.client.VtexProduct;
import com.cencosud.middleware.catalog.client.VtexSeller;
import com.cencosud.middleware.catalog.client.VtexSpecification;
import com.cencosud.middleware.catalog.dto.productdetail.CommertialOfferDto;
import com.cencosud.middleware.catalog.dto.productdetail.ImageWongDto;
import com.cencosud.middleware.catalog.dto.productdetail.ItemsWongDto;
import com.cencosud.middleware.catalog.dto.productdetail.ProductWongDto;
import com.cencosud.middleware.catalog.dto.productdetail.SellerWongDto;
import com.cencosud.middleware.catalog.dto.productdetail.SpecificationDto;

@Mapper(componentModel="spring")
public interface ProductWongMapper extends ProductMapper {

	ProductWongDto generateProduct(VtexProduct vtexProduct);

	ItemsWongDto generateItemDto(VtexItem vtexItem);

	SellerWongDto generateSellerDto(VtexSeller vtexSeller);

	@Mappings({ @Mapping(target = "available", expression = "java(vtexCommertialOffer.getAvailableQuantity()>0)") })
	CommertialOfferDto generateCommertialOfferDto(VtexCommertialOffer vtexCommertialOffer);

	ImageWongDto generateImageDto(VtexImage vtexImage);
	
	SpecificationDto generateSpecification(VtexSpecification vtexSpecification);
}
