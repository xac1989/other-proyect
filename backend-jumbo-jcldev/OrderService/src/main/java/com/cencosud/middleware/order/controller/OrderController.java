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
import com.cencosud.middleware.order.exception.OrderServiceException;
import com.cencosud.middleware.order.factory.OrderServiceFactory;
import com.cencosud.middleware.order.model.Order;
import com.cencosud.middleware.order.model.OrderDetail;

@RestController
@RequestMapping("/r1/v1/order")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	private static final String REGION_1 = "r1";
	
	@Autowired
	OrderServiceFactory serviceFactory;

	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Order> getAllUserOrders(@RequestParam(required = true) String email) throws OrderServiceException {
		logger.debug("email: {}", email);
		if (StringUtils.isEmpty(email)) {
			throw new OrderServiceException("El email es obligatorio");
		}

		return serviceFactory.getService(REGION_1).getAllUserOrders(email);
	}

	@Loggable
	@RequestMapping(method = RequestMethod.GET, value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public OrderDetail getOrder(@PathVariable String orderId) throws OrderServiceException {
		logger.debug("orderId: {}", orderId);
		return serviceFactory.getService(REGION_1).getOrder(orderId);
	}
}
