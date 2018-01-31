package com.cencosud.middleware.order.service;

import java.util.List;

import com.cencosud.middleware.order.client.dto.VtexFullOrder;
import com.cencosud.middleware.order.client.dto.VtexOrder;
import com.cencosud.middleware.order.exception.OrderServiceException;
import com.cencosud.middleware.order.model.Order;
import com.cencosud.middleware.order.model.OrderDetail;

/*
 * Esta pendiente una migración de lógica en Wong para que trabaje de igual que Jumbo
 */
public interface OrderService {
	
	String getRegionId();
	
	List<Order> getAllUserOrders(String email) throws OrderServiceException;
	OrderDetail getOrder(String orderId) throws OrderServiceException;

	List<VtexOrder> getVtexOrderList(String email) throws OrderServiceException;

	VtexFullOrder getVtexFullOrder(String orderId) throws OrderServiceException;
}
