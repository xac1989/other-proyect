package com.cencosud.middleware.order.repository;

import java.util.List;

import com.cencosud.middleware.order.client.dto.VtexFullOrder;
import com.cencosud.middleware.order.client.dto.VtexOrderList;
import com.cencosud.middleware.order.exception.OrderServiceException;
import com.cencosud.middleware.order.model.Order;
import com.cencosud.middleware.order.model.OrderDetail;

/*
 * Esta pendiente una migración de lógica en Wong para que trabaje de igual que Jumbo
 */
public interface OrderVtexRepository {

	List<Order> getAllUserOrders(String email) throws OrderServiceException;
	OrderDetail getOrder(String orderId) throws OrderServiceException;
	VtexOrderList getVtexOrderList(String email) throws OrderServiceException;
	VtexFullOrder getVtexFullOrder(String orderId) throws OrderServiceException;
	
}
