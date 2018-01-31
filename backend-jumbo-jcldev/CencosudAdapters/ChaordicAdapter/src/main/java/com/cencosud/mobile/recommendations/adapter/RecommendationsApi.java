package com.cencosud.mobile.recommendations.adapter;

import com.cencosud.mobile.recommendations.adapter.NotFoundException;
import com.cencosud.mobile.recommendations.adapter.RecommendationsApiService;
import com.cencosud.mobile.recommendations.model.*;

import io.swagger.annotations.ApiParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import java.util.List;

import javax.ws.rs.*;

import com.ibm.mfp.adapter.api.OAuthSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path("/r1/v1/recommendations")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the recommendations API")
@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2016-12-26T16:58:25.127-03:00")
@Component
public class RecommendationsApi  {
   @Autowired
   private RecommendationsApiService delegate = null;
   
   private RecommendationsApiService getRecommendationsApiService()  {

   		return delegate;
   }
   
    @GET
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(
    	value = "", 
		notes = "", 
		response = void.class, 
		authorizations = {
        	
    		}, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "200 OK", response = void.class) })
    @OAuthSecurity(enabled=false)   
    public Response recommendationsGet(@ApiParam(value = "Recomendation type: Similar, FrequentlyBoughtTogether, EndedUpBuying, BestSellers, MostVisited, Trending",required=true) @QueryParam("type") String type
,@ApiParam(value = "Product identification") @QueryParam("productId") List<String> productId
,@ApiParam(value = "Category identification") @QueryParam("categoryId") List<String> categoryId
,@ApiParam(value = "Tag Identification") @QueryParam("tagId") List<String> tagId
,@ApiParam(value = "Device Identification") @QueryParam("deviceId") String deviceId
,@ApiParam(value = "User identification") @QueryParam("userId") String userId
,@ApiParam(value = "Use bought products flag") @QueryParam("useBoughtProducts") String useBoughtProducts
,@ApiParam(value = "Use cart products flag") @QueryParam("useCartProducts") String useCartProducts
,@ApiParam(value = "Use visited products flag") @QueryParam("useVisitedProducts") String useVisitedProducts
,@ApiParam(value = "Elements size") @QueryParam("size") Integer size
, @Context SecurityContext securityContext)
    throws NotFoundException {
        return getRecommendationsApiService().recommendationsGet(type,productId,categoryId,tagId,deviceId,userId,useBoughtProducts,useCartProducts,useVisitedProducts,size,securityContext);
    }
}
