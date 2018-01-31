package com.cencosud.middleware.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.order.client.dto.VtexFullOrder;
import com.cencosud.middleware.order.client.dto.VtexOrder;
import com.cencosud.middleware.order.client.dto.VtexOrderList;
import com.cencosud.middleware.order.exception.OrderServiceException;
import com.cencosud.middleware.order.model.Order;
import com.cencosud.middleware.order.model.OrderDetail;
import com.cencosud.middleware.order.repository.OrderVtexRepository;
import com.cencosud.middleware.order.service.OrderService;

@Service
public class OrderR2ServiceImpl implements OrderService {
	
	//Region2 = JUMBO CHILE
	private static final String REGION_ID = "r2";

	@Autowired
	@Qualifier(value="orderVtexRepositoryR2")
	OrderVtexRepository repo;
	
	@Override
	public String getRegionId() {
		return REGION_ID;
	}
	
	@Override
	public List<VtexOrder> getVtexOrderList(String email) throws OrderServiceException {
		 VtexOrderList orderList = repo.getVtexOrderList(email);
		 return orderList.getList();
	}

	@Override
	public VtexFullOrder getVtexFullOrder(String orderId) throws OrderServiceException {
		return repo.getVtexFullOrder(orderId);
	}

	@Override
	public List<Order> getAllUserOrders(String email) throws OrderServiceException {
		throw new UnsupportedOperationException("Invalid operation");
	}

	@Override
	public OrderDetail getOrder(String orderId) throws OrderServiceException {
		throw new UnsupportedOperationException("Invalid operation");
	}
		

}
