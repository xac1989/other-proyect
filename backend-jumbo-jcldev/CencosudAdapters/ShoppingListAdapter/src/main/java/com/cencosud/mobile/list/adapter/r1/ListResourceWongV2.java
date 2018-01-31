package com.cencosud.mobile.list.adapter.r1;

import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.cencosud.mobile.list.dto.ListPostRequest;
import com.cencosud.mobile.list.dto.ListPostResponse;
import com.cencosud.mobile.list.dto.ListProductUpdateRequest;
import com.cencosud.mobile.list.dto.ListProductUpdateResponse;
import com.cencosud.mobile.list.dto.ListUpdateRequest;
import com.cencosud.mobile.list.dto.ProductListGetResponse;
import com.cencosud.mobile.list.dto.ShoppingListDto;
import com.cencosud.mobile.list.dto.UserShoppingListDto;
import com.cencosud.mobile.list.exception.NotFoundException;
import com.cencosud.mobile.list.model.ProductList;
import com.cencosud.mobile.list.model.UserShoppingList;
import com.cencosud.mobile.list.service.ListApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.mfp.adapter.api.OAuthSecurity;
import com.ibm.mfp.server.registration.external.model.AuthenticatedUser;
import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Api(value = "List Adapter Resource Wong V2")
@Path("/r1/v2")
public class ListResourceWongV2 {
	private final Logger logger = Logger.getLogger(ListResourceWongV2.class.toString());

	private ListApiService delegate;
	private ObjectMapper mapper = new ObjectMapper();
	private static final String REGION = "r1";
	private static final String VERSION = "v2";
	/**
	 * @return the delegate
	 */
	public ListApiService getDelegate() {
		return delegate;
	}

	/**
	 * @param delegate the delegate to set
	 */
	public void setDelegate(ListApiService delegate) {
		this.delegate = delegate;
	}

	@Path("/lists")
	@GET
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@OAuthSecurity(enabled = true, scope = "customer")
	public Response getList(@ApiParam(value = "Sales Chanel") @QueryParam("salesChannel") String salesChannel,
			@Context AdapterSecurityContext securityContext) throws NotFoundException {
		AuthenticatedUser user = securityContext.getAuthenticatedUser();
		Map<String, Object> attributes = user.getAttributes();
		String userId = getAttributeFromContext(attributes, "email");
		if (userId == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		UserShoppingList response = delegate.listsGet(userId, salesChannel,REGION,VERSION);
		return Response.status(Response.Status.OK).entity(new UserShoppingListDto(response)).build();
	}

	@Path("/unsec/lists")
	@GET
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@OAuthSecurity(enabled = false)
	public Response getListUnsec(@ApiParam(value = "User Id") @QueryParam("userId") String userId,
			@ApiParam(value = "Sales Chanel") @QueryParam("salesChannel") String salesChannel)
			throws NotFoundException {
		logger.info("@@@@@@@@@@@ Entrando en metodo unsec getList parametros :: userId::" +userId+" salesChannel::"+salesChannel );
		UserShoppingList response = delegate.listsGet(userId, salesChannel,REGION,VERSION);
		logger.info("@@@@@@@@ Luego de la llamada al servicio Get");
		return Response.status(Response.Status.OK).entity(new UserShoppingListDto(response)).build();
	}

	@Path("/lists")
	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@OAuthSecurity(enabled = true, scope = "customer")
	public Response deleteList(@QueryParam("listId") String listId, @Context AdapterSecurityContext securityContext)
			throws NotFoundException {
		AuthenticatedUser user = securityContext.getAuthenticatedUser();
		Map<String, Object> attributes = user.getAttributes();
		String userId = getAttributeFromContext(attributes, "email");
		if(userId == null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		delegate.deleteList(listId, userId,REGION,VERSION);
		
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	@Path("/unsec/lists")
	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@OAuthSecurity(enabled = false)
	public Response deleteList(@QueryParam("listId") String listId, @QueryParam("userId") String userId)
			throws NotFoundException {
		delegate.deleteList(listId, userId,REGION,VERSION);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	@Path("/lists")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@OAuthSecurity(enabled = true, scope = "customer")
	public Response postList(ListPostRequest request, @Context AdapterSecurityContext securityContext)
			throws NotFoundException {
		AuthenticatedUser user = securityContext.getAuthenticatedUser();
		Map<String, Object> attributes = user.getAttributes();
		String userId = getAttributeFromContext(attributes, "email");
		if(userId == null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		request.setUserId(userId);
		ListPostResponse response = delegate.createList(request,REGION,VERSION);
		return Response.status(Response.Status.OK).entity(response).build();
	}
	
	@Path("/unsec/lists")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@OAuthSecurity(enabled = false)
	public Response postList(ListPostRequest request)
			throws NotFoundException {
		ListPostResponse response = delegate.createList(request,REGION,VERSION);
		return Response.status(Response.Status.OK).entity(response).build();
	}
	
	private String getAttributeFromContext(Map<String, Object> attributes, String property) {
		if(attributes.containsKey(property)){
			String userId = (String) attributes.get(property);
			if(userId != null && !userId.equals("")){
				return userId;
			}
		}
		return null;
	}

	
	@Path("/lists/products")
    @GET
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @OAuthSecurity(enabled = true, scope = "customer")
    public Response getListProducto(@ApiParam(value = "SKU Id") @QueryParam("skuId") String skuId,
            @Context AdapterSecurityContext securityContext) throws NotFoundException {
        AuthenticatedUser user = securityContext.getAuthenticatedUser();
        Map<String, Object> attributes = user.getAttributes();
        String userId = getAttributeFromContext(attributes, "email");
        if(userId == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
		logger.info(">>>>>>>>>>> Entrando en metodo unsec getListProducts parametros :: userId::" +userId+" skuId::"+skuId);
		ProductList productList= delegate.getListProductGet(userId, skuId,REGION,VERSION);
		logger.info(">>>>>> servicio Get  termino");
		return Response.status(Response.Status.OK).entity(new ProductListGetResponse(productList)).build();
    }
	
	@Path("/unsec/lists/products")
	@GET
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@OAuthSecurity(enabled = false)
	public Response getList(@ApiParam(value = "User Id") @QueryParam("userId") String userId,
			@ApiParam(value = "Sku Id") @QueryParam("skuId") String skuId)
			throws NotFoundException {
		logger.info(">>>>>>>>>>> Entrando en metodo seguro getListProducts parametros :: userId::" +userId+" skuId::"+skuId);
		ProductList productList= delegate.getListProductGet(userId, skuId,REGION,VERSION);
		logger.info(">>>>>> servicio Get secure termino");
		return Response.status(Response.Status.OK).entity(new ProductListGetResponse(productList)).build();
	}
	
	
    @Path("/lists/products")
    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @OAuthSecurity(enabled = true, scope = "customer")
    public Response putList(ListProductUpdateRequest request,
            @Context AdapterSecurityContext securityContext) throws NotFoundException {
        AuthenticatedUser user = securityContext.getAuthenticatedUser();
        Map<String, Object> attributes = user.getAttributes();
        String userId = getAttributeFromContext(attributes, "email");
        if(userId == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        ListProductUpdateResponse response = delegate.updateProductList(request,REGION,VERSION);
        return Response.status(Response.Status.OK).entity(response).build();
    }
    @Path("/unsec/lists/products")
    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @OAuthSecurity(enabled = false)
    public Response putList(ListProductUpdateRequest request) throws NotFoundException {
    	ListProductUpdateResponse response = delegate.updateProductList(request,REGION,VERSION);
        return Response.status(Response.Status.OK).entity(response).build();
    }
    
    @Path("/lists")
    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @OAuthSecurity(enabled = true, scope = "customer")
    public Response putList(ListUpdateRequest request,
            @Context AdapterSecurityContext securityContext) throws NotFoundException {
        AuthenticatedUser user = securityContext.getAuthenticatedUser();
        Map<String, Object> attributes = user.getAttributes();
        String userId = getAttributeFromContext(attributes, "email");
        if (userId == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        ShoppingListDto response = delegate.updateList(request,REGION,VERSION);
        try {
			String strResponse = mapper.writeValueAsString(response);
			return Response.status(Response.Status.OK).entity(strResponse).build();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new NotFoundException(404, e.getMessage());
		}
    }
    
    @Path("/unsec/lists")
    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @OAuthSecurity(enabled = false)
    public Response putList(ListUpdateRequest request) throws NotFoundException {
    	ShoppingListDto response = delegate.updateList(request,REGION,VERSION);
        try {
			String strResponse = mapper.writeValueAsString(response);
			return Response.status(Response.Status.OK).entity(strResponse).build();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new NotFoundException(404, e.getMessage());
		}
    }
    
    @GET
    @Path("/health")
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
    public Response getHealth() {
        
        JSONObject obj = new JSONObject();
        
        obj.put("status", "UP");
        
        JSONObject objDisk = new JSONObject();
        objDisk.put("status", "UP");
        objDisk.put("total", "0");
        objDisk.put("free", "0");
        objDisk.put("threshold", "0");
        
        obj.put("diskSpace", objDisk);
            
        
        return Response.status(Response.Status.OK).entity(obj.toString()).build();
    }
}
