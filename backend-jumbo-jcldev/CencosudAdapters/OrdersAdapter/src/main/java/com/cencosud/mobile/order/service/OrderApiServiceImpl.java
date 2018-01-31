package com.cencosud.mobile.order.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.cencosud.mobile.order.adapter.NotFoundException;
import com.cencosud.mobile.order.adapter.OrderApiService;
import com.cencosud.mobile.order.model.ItemDetailR2;
import com.cencosud.mobile.order.model.OrderDetailResponseR2;
import com.cencosud.mobile.order.model.Product;
import com.cencosud.mobile.order.util.RestServiceUtil;


public class OrderApiServiceImpl implements OrderApiService{
	
	private static final String ORDER_PATH = "/orders";
	private static final String CATALOG_PATH = "/catalog";
	
	
	private RestServiceUtil restServiceUtil;


	public RestServiceUtil getRestServiceUtil() {
		return restServiceUtil;
	}

	public void setRestServiceUtil(RestServiceUtil restServiceUtil) {
		this.restServiceUtil = restServiceUtil;
	}

	public Response getOrderById(String region, String version, String orderId,String sc) throws NotFoundException {
		
		String orderPath = ORDER_PATH.concat("/").concat(region).concat("/").concat(version).concat("/order").concat("/")
				.concat(orderId);
		OrderDetailResponseR2 orderResponse = restServiceUtil.executeService(orderPath,OrderDetailResponseR2.class,null);
		Set<String> skusSet = new HashSet<>();
		
		for(ItemDetailR2 itemDetailR2 : orderResponse.getOrder().getItems()){
			skusSet.add(itemDetailR2.getSkuId());
		}
		String skus = StringUtils.join(skusSet, ",");
		
		Map<String,Product> productMap = new HashMap<>();
		
		String catalogPath = CATALOG_PATH.concat("/").concat(region).concat("/").concat(version).concat("/products");
		Map<String, Object> paramteres = new HashMap<>();
		paramteres.put("skuId", skus);
		paramteres.put("sc", sc);
		Product[] products = restServiceUtil.executeService(catalogPath, Product[].class, paramteres);
		for (Product product : products) {
			productMap.put(product.getSkuId(), product);
		}
		for (ItemDetailR2 itemDetail : orderResponse.getOrder().getItems()) {
			Product product = productMap.get(itemDetail.getSkuId());
			if ( product != null ) {
				itemDetail.setAddToCartLink(product.getAddToCartLink());
				itemDetail.setSkuData(product.getSkuData());
				itemDetail.setSellerId(product.getSellerId());
			}
		}
		return Response.status(Response.Status.OK).entity(orderResponse).build();
	}
	
	public Response getOrderById(String region, String version, String orderId) throws Exception {
		String path = ORDER_PATH.concat("/").concat(region).concat("/").concat(version).concat("/order").concat("/")
				.concat(orderId);
		String response = restServiceUtil.executeService(path,String.class,null);
		return Response.status(Response.Status.OK).entity(response).build();
		
	}

	public Response findOrders(String region, String version, String email) throws NotFoundException {
		String path = ORDER_PATH.concat("/").concat(region).concat("/").concat(version).concat("/search");
		Map<String,Object> parameters = new HashMap<>();
		parameters.put("email", email);
		
		String orders = restServiceUtil.executeService(path, String.class, parameters);
		return Response.status(Response.Status.OK).entity(orders).build();
	
	}



}
