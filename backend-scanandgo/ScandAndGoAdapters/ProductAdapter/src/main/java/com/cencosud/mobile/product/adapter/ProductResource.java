package com.cencosud.mobile.product.adapter;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.product.model.ProductResponse;
import com.cencosud.mobile.service.ProductApiService;
import com.ibm.mfp.adapter.api.OAuthSecurity;
import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Product Adapter Resource")
@Path("/r1/v1/product")
public class ProductResource {
	static Logger logger = Logger.getLogger(ProductResource.class.getName());

	@Context
	private AdapterSecurityContext securityContext;

	private ProductApiService productService;
	

	public ProductApiService getProductService() {
		return productService;
	}

	public void setProductService(ProductApiService productService) {
		this.productService = productService;
	}

	@ApiOperation(value = "Returns product attributes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product attributes returned") })
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = false)
	public ProductResponse product(@QueryParam("ean") String ean, @QueryParam("storeId") String storeId) throws NotFoundException {
		return this.getProduct(ean, storeId);
	}

	private ProductResponse getProduct(String ean, String storeId) throws NotFoundException {		
		return productService.productGet(ean, storeId);
	}
}
