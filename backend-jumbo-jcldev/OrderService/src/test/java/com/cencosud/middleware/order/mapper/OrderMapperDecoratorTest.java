package com.cencosud.middleware.order.mapper;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.middleware.order.client.dto.VtexFullOrder;
import com.cencosud.middleware.order.client.dto.VtexPaymentDetail;
import com.cencosud.middleware.order.client.dto.VtexPaymentInfo;
import com.cencosud.middleware.order.client.dto.VtexTransactionDetail;
import com.cencosud.middleware.order.configuration.ApplicationConfig;
import com.cencosud.middleware.order.configuration.ApplicationConfig.VTexClientProperties;
import com.cencosud.middleware.order.configuration.ApplicationConfig.VTexProperties;
import com.cencosud.middleware.order.model.JumboOrderDetail;

@RunWith(MockitoJUnitRunner.class)
public class OrderMapperDecoratorTest {

	private static final String PAYMENT_SYSTEM_NAME = "Pago de Pruebas";
	private static final int PAYMENT_TOTAL = 908000;

	OrderMapperDecorator decorator = mock(OrderMapperDecorator.class, Mockito.CALLS_REAL_METHODS);

	@Mock
	ApplicationConfig ApplicationConfig;

	@Mock
	OrderMapper orderMapper;

	private JumboOrderDetail jumboOrderDetail;
	private VtexFullOrder vtexFullOrder;

	@Before
	public void setUp() {
		jumboOrderDetail = createJumboOrderDetail();
		vtexFullOrder = createVtexFullOrder();
	}

	@Test
	public void getOrderDetail() {
		getOrderPreconditions();

		JumboOrderDetail jumboOrderDetail = decorator.getOrderDetail(vtexFullOrder);
		Assert.assertThat(jumboOrderDetail.getPaymentDetails().get(0).getPaymentSystem(),
				Matchers.is(PAYMENT_SYSTEM_NAME));
		Assert.assertThat(jumboOrderDetail.getPaymentDetails().get(0).getTotal(),
				Matchers.equalTo(BigDecimal.valueOf(PAYMENT_TOTAL).movePointLeft(2)));

	}

	private void getOrderPreconditions() {
		OrderMapper orderMapper = mock(OrderMapper.class);
		ApplicationConfig applicationConfig = mock(ApplicationConfig.class);
		VTexProperties vTexProperties = mock(VTexProperties.class);
		VTexClientProperties vTexClientProperties = mock(VTexClientProperties.class);

		doReturn(jumboOrderDetail).when(orderMapper).getOrderDetail(Mockito.any(VtexFullOrder.class));
		doReturn(vTexProperties).when(applicationConfig).getVtex();
		doReturn(vTexClientProperties).when(vTexProperties).getR2();
		doReturn("https://escritorio.acepta.com/portalboletas/buscarboletaindex.php?empresa=81201000-K")
				.when(vTexClientProperties).getPaymentTicketUrl();

		decorator.setOrderMapper(orderMapper);
		decorator.setApplicationConfig(applicationConfig);

	}

	private VtexFullOrder createVtexFullOrder() {

		List<VtexPaymentInfo> vtexPaymentInfoList = new ArrayList<>();
		VtexPaymentInfo vtexPaymentInfo = new VtexPaymentInfo();
		vtexPaymentInfo.setPaymentSystemName(PAYMENT_SYSTEM_NAME);
		vtexPaymentInfo.setValue(PAYMENT_TOTAL);
		vtexPaymentInfoList.add(vtexPaymentInfo);

		List<VtexTransactionDetail> vtexTransactionDetailList = new ArrayList<>();
		VtexTransactionDetail vtexTransactionDetail = new VtexTransactionDetail();
		vtexTransactionDetail.setPayments(vtexPaymentInfoList);
		vtexTransactionDetailList.add(vtexTransactionDetail);

		VtexPaymentDetail vtexPaymentDetail = new VtexPaymentDetail();
		vtexPaymentDetail.setTransactions(vtexTransactionDetailList);

		VtexFullOrder vtexFullOrder = new VtexFullOrder();
		vtexFullOrder.setPaymentData(vtexPaymentDetail);

		return vtexFullOrder;
	}

	private JumboOrderDetail createJumboOrderDetail() {
		JumboOrderDetail jumboOrderDetail = new JumboOrderDetail();
		jumboOrderDetail.setDate("2017-09-22T14:13:58.3577331+00:00");
		jumboOrderDetail.setId("v10144004jmch-01");
		jumboOrderDetail.setStatusDescription("Preparando Entrega");
		return jumboOrderDetail;
	}

}
