package com.cencosud.middleware.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.order.annotation.Loggable;
import com.cencosud.middleware.order.client.dto.VtexFullOrder;
import com.cencosud.middleware.order.client.dto.VtexOrder;
import com.cencosud.middleware.order.exception.OrderServiceException;
import com.cencosud.middleware.order.factory.OrderServiceFactory;
import com.cencosud.middleware.order.mapper.OrderMapper;
import com.cencosud.middleware.order.model.JumboOrder;
import com.cencosud.middleware.order.model.JumboOrderDetail;

@RestController
@RequestMapping("/r2/v1/order")
public class OrderControllerR2 {

	private static final Logger logger = LoggerFactory.getLogger(OrderControllerR2.class);

	private static final String REGION_2 = "r2";

	@Autowired
	OrderServiceFactory serviceFactory;

	@Autowired
	private OrderMapper orderMapper;

	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<JumboOrder> getOrders(@RequestParam(required = true) String email) throws OrderServiceException {
		logger.debug("email: {}", email);
		if (StringUtils.isEmpty(email)) {
			throw new OrderServiceException("email is required");
		}

		List<VtexOrder> orderList = serviceFactory.getService(REGION_2).getVtexOrderList(email);

		return orderMapper.getOrderList(orderList);
	}

	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public JumboOrderDetail getOrder(@PathVariable String orderId) throws OrderServiceException {
		logger.debug("orderId: {}", orderId);

		VtexFullOrder vtexFullOrder = serviceFactory.getService(REGION_2).getVtexFullOrder(orderId);

		return orderMapper.getOrderDetail(vtexFullOrder);
	}
}
