package com.cencosud.middleware.order.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cencosud.middleware.order.client.dto.VtexFullOrder;
import com.cencosud.middleware.order.client.dto.VtexPaymentInfo;
import com.cencosud.middleware.order.client.dto.VtexTransactionDetail;
import com.cencosud.middleware.order.configuration.ApplicationConfig;
import com.cencosud.middleware.order.model.JumboOrderDetail;
import com.cencosud.middleware.order.model.PaymentDetail;

public abstract class OrderMapperDecorator implements OrderMapper {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private ApplicationConfig applicationConfig;

	public void setOrderMapper(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}

	public void setApplicationConfig(ApplicationConfig applicationConfig) {
		this.applicationConfig = applicationConfig;
	}

	@Override
	public JumboOrderDetail getOrderDetail(VtexFullOrder vtexFullOrder) {
		JumboOrderDetail jumboOrderDetail = orderMapper.getOrderDetail(vtexFullOrder);
		jumboOrderDetail.setPaymentTicketUrl(applicationConfig.getVtex().getR2().getPaymentTicketUrl());

		List<PaymentDetail> paymentDetailList = new ArrayList<>();
		for (VtexTransactionDetail vtexTransactionDetail : vtexFullOrder.getPaymentData().getTransactions()) {
			for (VtexPaymentInfo vtexPaymentInfo : vtexTransactionDetail.getPayments()) {
				PaymentDetail paymentDetail = new PaymentDetail();
				paymentDetail.setPaymentSystem(vtexPaymentInfo.getPaymentSystemName());
				paymentDetail.setTotal(BigDecimal.valueOf(vtexPaymentInfo.getValue()).movePointLeft(2));
				paymentDetailList.add(paymentDetail);
			}
		}

		jumboOrderDetail.setPaymentDetails(paymentDetailList);
		return jumboOrderDetail;
	}

}
