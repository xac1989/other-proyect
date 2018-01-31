package com.cencosud.middleware.order.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Repository;

import com.cencosud.middleware.order.client.VtexClient;
import com.cencosud.middleware.order.client.dto.VtexFullOrder;
import com.cencosud.middleware.order.client.dto.VtexOrder;
import com.cencosud.middleware.order.client.dto.VtexOrderList;
import com.cencosud.middleware.order.exception.OrderServiceException;
import com.cencosud.middleware.order.model.Order;
import com.cencosud.middleware.order.model.OrderDetail;
import com.cencosud.middleware.order.model.enums.RequestProtocolEnum;
import com.cencosud.middleware.order.repository.OrderVtexRepository;
import com.fasterxml.jackson.core.type.TypeReference;

@Repository
public class OrderVtexR1RepositoryImpl implements OrderVtexRepository {
	
	VtexClient client;
	
	public void setClient(VtexClient client) {
		this.client = client;
	}
	
	private static final String FIND_ORDERS_URL = "/api/oms/pvt/orders";
	private static final TypeReference<VtexOrderList> ORDER_LIST_REF = new TypeReference<VtexOrderList>() {};
	private static final TypeReference<VtexFullOrder> ORDER_DETAIL_REF = new TypeReference<VtexFullOrder>() {};
	
	@Override
	public List<Order> getAllUserOrders(String email) throws OrderServiceException {
		List<NameValuePair> nvps = new ArrayList<>(1);
		nvps.add(new BasicNameValuePair("clientEmail", email));
		VtexOrderList orderList = client.execute(FIND_ORDERS_URL, nvps, RequestProtocolEnum.GET, ORDER_LIST_REF);
		List<Order> orders;
		if (orderList.getList() != null) {
			orders = new ArrayList<>(orderList.getList().size());
			for (VtexOrder currentVtexOrder: orderList.getList()) {
				orders.add(currentVtexOrder.toModelOrder());
			}
		} else {
			orders = new ArrayList<>();
		}
		return orders;
	}

	@Override
	public OrderDetail getOrder(String orderId) throws OrderServiceException {
		VtexFullOrder fullOrder = client.execute(
				FIND_ORDERS_URL+"/"+orderId, 
				null, 
				RequestProtocolEnum.GET, 
				ORDER_DETAIL_REF);
		return fullOrder.toModelOrderDetail();
	}

	@Override
	public VtexOrderList getVtexOrderList(String email) throws OrderServiceException {
		throw new UnsupportedOperationException("Invalid operation");
	}

	@Override
	public VtexFullOrder getVtexFullOrder(String orderId) throws OrderServiceException {
		throw new UnsupportedOperationException("Invalid operation");
	}
}
