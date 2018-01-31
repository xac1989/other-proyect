package com.cencosud.mobile.search.adapter.r2;

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
import com.ibm.json.java.JSONObject;
import com.ibm.mfp.adapter.api.OAuthSecurity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/r2/v1")
@Api(value = "Catalog API Region 2 Version 1")
@Component
public class CatalogApiJumboV1 {
	
	private static final Logger logger = Logger.getLogger(CatalogApiJumboV1.class.getName());

	private static final String REGION = "r2";
	private static final String VERSION = "v1";
	@Autowired
	private ProductApiService productDelegate = null;

	private ProductApiService getProductApiService() {

		return productDelegate;
	}
		
	public void setProductDelegate(ProductApiService productDelegate) {
		this.productDelegate = productDelegate;
	}

	public void setProductsDelegate(ProductsApiService productsDelegate) {
		this.productsDelegate = productsDelegate;
	}

	@GET
	@Path("/product/{productId}/{sc}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Returns Product Detail")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product detail returned") })
	@OAuthSecurity(enabled = false)
	public Response productProductIdGet(
			@ApiParam(value = "Product identification", required = true) @PathParam("productId") String productId,
			@ApiParam(value = "Sales Channel", required = true) @PathParam("sc") String sc,
			@Context SecurityContext securityContext) throws NotFoundException {
		return getProductApiService().productProductIdGet(productId, REGION, VERSION, sc);
	}
	
    @Autowired
    private ProductsApiService productsDelegate = null;

    private ProductsApiService getProductsApiService()  {
        return productsDelegate;
    }

	
	@GET
	@Path("/products/search/{sc}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "", notes = "", response = SearchResponse.class, authorizations = {

	}, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Products", response = SearchResponse.class) })
	@OAuthSecurity(enabled = false)
	public Response productsSearchGet(
	        @ApiParam(value = "Sales Channel", required = true) @PathParam("sc") String sc,
			@ApiParam(value = "Criteria for product search") @QueryParam("q") String q,
			@ApiParam(value = "Criteria to order by OrderByPriceASC, OrderByPriceDESC,OrderByTopSaleDESC, OrderByNameASC, OrderByNameDESC, OrderByReleaseDateDESC, OrderByBestDiscountDESC") @QueryParam("o") String o,
			@ApiParam(value = "Index start for paging") @QueryParam("offset") Integer offset,
			@ApiParam(value = "Number of elements") @QueryParam("limit") Integer limit,
			@ApiParam(value = "Category filter") @QueryParam("filter") String filter,
			@ApiParam(value = "Brand") @QueryParam("brand") String brand,
			@ApiParam(value = "Specifications") @QueryParam("spec") List<String> spec,

			@Context SecurityContext securityContext) throws NotFoundException {
		return getProductsApiService().productsSearchGet(
		        new ProductSearchParameter().setRegion(REGION).setVersion(VERSION).setTextSearch(q)
		            .setOrder(o).setOffset(offset).setLimit(limit)
		            .setFilter(filter).setBrand(brand).setSpec(spec)
		            .setSalesChannel(sc)
		        ).toResponse();
	}

	@GET
	@Path("/products/deals/{sc}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "", notes = "", response = SearchResponse.class, authorizations = {

	}, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Products", response = SearchResponse.class) })
	@OAuthSecurity(enabled = false)
	public Response getDeals(
	        @ApiParam(value = "Sales Channel", required = true) @PathParam("sc") String sc,
			@ApiParam(value = "Criteria to order by OrderByPriceASC, OrderByPriceDESC,OrderByTopSaleDESC, OrderByNameASC, OrderByNameDESC, OrderByReleaseDateDESC, OrderByBestDiscountDESC") @QueryParam("o") String o,
			@ApiParam(value = "Index start for paging") @QueryParam("offset") Integer offset,
			@ApiParam(value = "Number of elements") @QueryParam("limit") Integer limit,
            @ApiParam(value = "Criteria for product search") @QueryParam("q") String q,
            @ApiParam(value = "Category filter") @QueryParam("filter") String filter,
            @ApiParam(value = "Brand") @QueryParam("brand") String brand,
            @ApiParam(value = "Specifications") @QueryParam("spec") List<String> spec,
			
			@Context SecurityContext securityContext) throws NotFoundException {
	    return getProductsApiService().getDeals(
		        new ProductSearchParameter().setRegion(REGION).setVersion(VERSION).setOrder(o)
		            .setOffset(offset).setLimit(limit).setSalesChannel(sc)
		            .setTextSearch(q).setFilter(filter).setBrand(brand).setSpec(spec)
		        ).toResponse();
	}

	@GET
	@Path("/health")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@ApiOperation(value = "", notes = "", response = void.class, authorizations = {}, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "200 OK", response = void.class) })
	@OAuthSecurity(enabled = false)
	public Response getHealth() {
		JSONObject objDisk = new JSONObject();
		objDisk.put("status", "UP");
		objDisk.put("total", "0");
		objDisk.put("free", "0");
		objDisk.put("threshold", "0");

		JSONObject obj = new JSONObject();
		obj.put("status", "UP");
		obj.put("diskSpace", objDisk);

		return Response.ok(obj).build();
	}
	
	
	public static void main(String args[]) throws NotFoundException{
		CatalogApiJumboV1 adapter = new CatalogApiJumboV1();
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
		
		logger.info(adapter.productProductIdGet("905", "1", null).toString());
		logger.info(adapter.getDeals("1", null, null, null, null, null, null, null, null).toString());
		logger.info(adapter.productsSearchGet("1", null, null, null, null, null, null, null, null).toString());
	}
}
