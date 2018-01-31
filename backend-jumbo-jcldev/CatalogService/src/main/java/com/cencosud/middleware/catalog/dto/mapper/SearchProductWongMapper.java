package com.cencosud.middleware.catalog.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.cencosud.middleware.catalog.client.VtexCommertialOffer;
import com.cencosud.middleware.catalog.client.VtexImage;
import com.cencosud.middleware.catalog.client.VtexItem;
import com.cencosud.middleware.catalog.client.VtexProduct;
import com.cencosud.middleware.catalog.client.VtexSeller;
import com.cencosud.middleware.catalog.dto.search.SearchCommertialOfferDto;
import com.cencosud.middleware.catalog.dto.search.SearchImageWongDto;
import com.cencosud.middleware.catalog.dto.search.SearchItemWongDto;
import com.cencosud.middleware.catalog.dto.search.SearchProductWongDto;
import com.cencosud.middleware.catalog.dto.search.SearchResultWongDto;
import com.cencosud.middleware.catalog.dto.search.SearchSellerWongDto;
import com.cencosud.middleware.catalog.model.SearchResult;

@Mapper(componentModel="spring")
public interface SearchProductWongMapper extends SearchProductMapper {

	SearchResultWongDto generateSearchResult(SearchResult searchResult);

	SearchProductWongDto generateProduct(VtexProduct vtexProduct);

	SearchItemWongDto generateItemDto(VtexItem vtexItem);

	SearchSellerWongDto generateSellerDto(VtexSeller vtexSeller);

	@Mappings({ @Mapping(target = "available", expression = "java(vtexCommertialOffer.getAvailableQuantity()>0)") })
	SearchCommertialOfferDto generateCommertialOfferDto(VtexCommertialOffer vtexCommertialOffer);

	SearchImageWongDto generateImageDto(VtexImage vtexImage);

}
