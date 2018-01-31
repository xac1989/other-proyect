package com.cencosud.middleware.catalog.dto.mapper;

import com.cencosud.middleware.catalog.client.VtexProduct;
import com.cencosud.middleware.catalog.dto.productdetail.ProductDto;

public interface ProductMapper {

	ProductDto generateProduct(VtexProduct vtexProduct);

}
