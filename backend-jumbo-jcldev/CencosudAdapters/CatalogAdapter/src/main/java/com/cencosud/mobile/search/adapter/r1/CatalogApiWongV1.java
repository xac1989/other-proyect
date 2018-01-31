package com.cencosud.mobile.search.adapter.r1;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cencosud.mobile.search.adapter.NotFoundException;
import com.cencosud.mobile.search.adapter.ProductApiService;
import com.cencosud.mobile.search.adapter.ProductsApiService;
import com.cencosud.mobile.search.model.ProductSearchParameter;
import com.cencosud.mobile.search.model.SearchResponse;
import com.cencosud.mobile.service.ProductApiServiceImpl;
import com.cencosud.mobile.service.ProductsApiServiceImpl;
import com.cencosud.mobile.util.RestServiceUtil;
import com.ibm.mfp.adapter.api.OAuthSecurity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/r1/v1")
@Api(value = "Catalog API Region 1 Version 1")
@Component
public class CatalogApiWongV1 {
	
	private static final Logger logger = Logger.getLogger(CatalogApiWongV1.class.getName());
	
	private static final String REGION = "r1";
	private static final String VERSION = "v1";
	
	@Autowired
	private ProductApiService productDelegate = null;
	
	@Autowired
    private ProductsApiService productsDelegate = null;

    private ProductsApiService getProductsApiService()  {
        return productsDelegate;
    }
    
	private ProductApiService getProductApiService() {

		return productDelegate;
	}
    
	public ProductApiService getProductDelegate() {
		return productDelegate;
	}

	public void setProductDelegate(ProductApiService productDelegate) {
		this.productDelegate = productDelegate;
	}

	public void setProductsDelegate(ProductsApiService productsDelegate) {
		this.productsDelegate = productsDelegate;
	}


	@GET
	@Path("/product/{productId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Returns Product Detail")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product detail returned") })
	@OAuthSecurity(enabled = false)
	public Response productProductIdGet(
			@ApiParam(value = "Product identification", required = true) @PathParam("productId") String productId,
			@Context SecurityContext securityContext) throws NotFoundException {
		return getProductApiService().productProductIdGet(productId, REGION, VERSION);
	}
	
	
	@GET
	@Path("/products/search")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "", notes = "", response = SearchResponse.class, authorizations = {

	}, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Products", response = SearchResponse.class) })
	@OAuthSecurity(enabled = false)
	public Response productsSearchGet(@ApiParam(value = "Criteria for product search") @QueryParam("q") String q,
			@ApiParam(value = "Criteria to order by OrderByPriceASC, OrderByPriceDESC,OrderByTopSaleDESC, OrderByReviewRateDESC, OrderByNameASC, OrderByNameDESC, OrderByReleaseDateDESC, OrderByBestDiscountDESC") @QueryParam("o") String o,
			@ApiParam(value = "Index start for paging") @QueryParam("offset") Integer offset,
			@ApiParam(value = "Number of elements") @QueryParam("limit") Integer limit,
			@ApiParam(value = "Category filter") @QueryParam("filter") String filter,
			@ApiParam(value = "Brand") @QueryParam("brand") String brand,
			@ApiParam(value = "Specifications") @QueryParam("spec") List<String> spec,
			@Context SecurityContext securityContext) throws NotFoundException {
		return getProductsApiService().productsSearchGet(
		        new ProductSearchParameter().setRegion(REGION).setVersion(VERSION).setTextSearch(q)
                .setOrder(o).setOffset(offset).setLimit(limit)
                .setFilter(filter).setBrand(brand).setSpec(spec))
		        .toResponse();
	}

	public static void main(String args[]) throws NotFoundException{
		CatalogApiWongV1 adapter = new CatalogApiWongV1();
		ProductApiServiceImpl productDel = new ProductApiServiceImpl();
		ProductsApiServiceImpl productsDel = new ProductsApiServiceImpl();
		RestServiceUtil util = new RestServiceUtil();
		
		util.setApiBaseUrl("https://api.us.apiconnect.ibmcloud.com/supermercados-cencosud-wong-development/sandbox");
		util.setApiClientId("a8b2ca23-8fc0-4643-8bcf-469fa8b0c814");
		util.setApiSecret("N5pC2xE1wY4gH6sN0xK0wI1kU8vO7fI3xN3kX1lA5fA4fE1aD6");
		productDel.setRestServiceUtil(util);
		productsDel.setRestServiceUtil(util);
		
		adapter.setProductDelegate(productDel);
		adapter.setProductsDelegate(productsDel);
		
		logger.info(adapter.productProductIdGet("523284", null).toString());
		logger.info(adapter.productsSearchGet(null, null, null, null, null, null, null, null).toString());
	}

}
