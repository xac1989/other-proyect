package com.cencosud.middleware.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.order.client.dto.VtexFullOrder;
import com.cencosud.middleware.order.client.dto.VtexOrder;
import com.cencosud.middleware.order.exception.OrderServiceException;
import com.cencosud.middleware.order.model.Order;
import com.cencosud.middleware.order.model.OrderDetail;
import com.cencosud.middleware.order.repository.OrderVtexRepository;
import com.cencosud.middleware.order.service.OrderService;

@Service
public class OrderR1ServiceImpl implements OrderService {
	
	//Region1 = WONG PERU
	private static final String REGION_ID = "r1";

	@Autowired
	@Qualifier(value="orderVtexRepositoryR1")
	OrderVtexRepository repo;
	
	@Override
	public String getRegionId() {
		return REGION_ID;
	}
	
	@Override
	public List<Order> getAllUserOrders(String email) throws OrderServiceException {
		return repo.getAllUserOrders(email);
	}

	@Override
	public OrderDetail getOrder(String orderId) throws OrderServiceException {
		return repo.getOrder(orderId);
	}

	@Override
	public List<VtexOrder> getVtexOrderList(String email) throws OrderServiceException {
		throw new UnsupportedOperationException("Invalid operation");
	}

	@Override
	public VtexFullOrder getVtexFullOrder(String orderId) throws OrderServiceException {
		throw new UnsupportedOperationException("Invalid operation");
	}

}
