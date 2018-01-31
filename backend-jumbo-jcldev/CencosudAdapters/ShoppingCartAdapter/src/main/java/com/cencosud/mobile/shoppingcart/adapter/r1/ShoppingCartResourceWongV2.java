package com.cencosud.mobile.shoppingcart.adapter.r1;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cencosud.mobile.exceptions.NotFoundException;
import com.cencosud.mobile.profile.model.Item;
import com.cencosud.mobile.profile.model.ShoppingCartItemResponse;
import com.cencosud.mobile.profile.model.ShoppingCartItemsResponse;
import com.cencosud.mobile.service.ShoppingCartApiService;
import com.cencosud.mobile.service.impl.ShoppingCartApiServiceImpl;
import com.cencosud.mobile.util.RestServiceUtil;
import com.ibm.mfp.adapter.api.OAuthSecurity;
import com.ibm.mfp.server.security.external.resource.AdapterSecurityContext;

import io.swagger.annotations.Api;

@Api(value = "Shopping Cart Adapter Resource Wong V2")
@Path("/r1/v2")
public class ShoppingCartResourceWongV2 {

	static Logger logger = Logger.getLogger(ShoppingCartResourceWongV2.class.getName());
	static final String REGION = "r1";
	static final String VERSION = "v2";

	@Context
	private AdapterSecurityContext securityContext;

	private ShoppingCartApiService shoppingCartService;


	public ShoppingCartApiService getShoppingCartService() {
		return shoppingCartService;
	}


	public void setShoppingCartService(ShoppingCartApiService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}


	@PUT
	@Path("/cart/{shoppingCartId}/items")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@OAuthSecurity(enabled = false)
	public Response editShoppingCartItem(@PathParam("shoppingCartId") String shoppingCartId, Item item)
			throws NotFoundException {
		
		ShoppingCartItemResponse response  = shoppingCartService.editShoppingCartItem(shoppingCartId, REGION,VERSION, item);
		
		return Response.ok(response, MediaType.APPLICATION_JSON).build();

	}
	

	@POST
	@Path("/cart/{shoppingCartId}/items")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@OAuthSecurity(enabled = false)
	public Response addShoppingCartItem(@PathParam("shoppingCartId") String shoppingCartId, List<Item> items)
			throws NotFoundException {
		
		ShoppingCartItemsResponse response = shoppingCartService.addShoppingCartItem(shoppingCartId, REGION,VERSION, items);

		return Response.ok(response, MediaType.APPLICATION_JSON).build();

	}

	
	@DELETE
	@Path("/cart/{shoppingCartId}/items/{itemId}")
	@Produces(MediaType.APPLICATION_JSON)
	@OAuthSecurity(enabled = false)
	public Response removeShoppingCartItem(@PathParam("shoppingCartId") String shoppingCartId, @PathParam("itemId") String itemId)
			throws NotFoundException {
		shoppingCartService.deleteShoppingCartItem(shoppingCartId, itemId, REGION,VERSION);
		return Response.noContent().build();
	}
	

	public static void main(String[] args) throws NotFoundException{
		ShoppingCartResourceWongV2 adapter = new ShoppingCartResourceWongV2();
		ShoppingCartApiServiceImpl service = new ShoppingCartApiServiceImpl();
		RestServiceUtil util = new RestServiceUtil();
		util.setApiBaseUrl("https://api.us.apiconnect.ibmcloud.com/supermercados-cencosud-wong-development/dev");
		util.setApiClientId("5fcf4990-1035-4f93-ab3b-1fe385af6c6a");
		util.setApiSecret("gX5nH3oH7fG1dG4qU2vS7kL5hJ5bS6uC3nY1fE6dI2pY6uR6hU");
		service.setRestServiceUtil(util);
		adapter.setShoppingCartService(service);
		
		System.out.println(adapter.removeShoppingCartItem("e69373ce972d4ac499a2b24f88146f24", "0"));
		
		List<Item> items = new ArrayList<>();
		Item item1 = new Item("436", 0, 2);
		Item item2 = new Item("438", 0, 4);
		items.add(item1);
		items.add(item2);
		System.out.println((ShoppingCartItemsResponse)adapter.addShoppingCartItem("e69373ce972d4ac499a2b24f88146f24", items).getEntity());
		
		Item item = new Item("438",1, 5);
		System.out.println((ShoppingCartItemResponse)adapter.editShoppingCartItem("e69373ce972d4ac499a2b24f88146f24", item).getEntity());
		
	}
	
}
