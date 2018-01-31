package com.cencosud.middleware.order.mapper;

import java.util.List;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.cencosud.middleware.order.client.dto.VtexFullOrder;
import com.cencosud.middleware.order.client.dto.VtexFullOrderItem;
import com.cencosud.middleware.order.client.dto.VtexOrder;
import com.cencosud.middleware.order.client.dto.VtexOrderTotal;
import com.cencosud.middleware.order.client.dto.VtexShippingData;
import com.cencosud.middleware.order.model.JumboItemDetail;
import com.cencosud.middleware.order.model.JumboOrder;
import com.cencosud.middleware.order.model.JumboOrderDetail;
import com.cencosud.middleware.order.model.PaymentTotal;
import com.cencosud.middleware.order.model.ShippingDetail;

@Mapper(componentModel = "spring")
@DecoratedWith(OrderMapperDecorator.class)
public interface OrderMapper {
	@Mappings({ @Mapping(source = "orderId", target = "id"), @Mapping(source = "creationDate", target = "date"),
			@Mapping(target = "totalValue", expression = "java(java.math.BigDecimal.valueOf(vtexOrder.getTotalValue()).movePointLeft(2))"),
			@Mapping(target = "quantityProducts", expression = "java(vtexOrder.getItems().size())") })
	JumboOrder getOrder(VtexOrder vtexOrder);

	List<JumboOrder> getOrderList(List<VtexOrder> vtexOrder);

	@Mappings({ @Mapping(source = "orderId", target = "id"), @Mapping(source = "creationDate", target = "date"),
			@Mapping(source = "shippingData", target = "shippingDetail") })
	JumboOrderDetail getOrderDetail(VtexFullOrder vtexFullOrder);

	@Mappings({ @Mapping(target = "description", source = "name"),
			@Mapping(target = "price", expression = "java(java.math.BigDecimal.valueOf(vtexFullOrderItem.getPrice()).movePointLeft(2))"),
			@Mapping(target = "brand", expression = "java(vtexFullOrderItem.getAdditionalInfo().get(\"brandName\").toString())"),
			@Mapping(target = "productReference", source = "refId"), @Mapping(target = "image", source = "imageUrl"),
			@Mapping(target = "skuId", source = "sellerSku") })
	JumboItemDetail getJumboItemDetail(VtexFullOrderItem vtexFullOrderItem);

	@Mappings({
			@Mapping(target = "total", expression = "java(java.math.BigDecimal.valueOf(vtexOrderTotal.getValue()).movePointLeft(2))") })
	PaymentTotal getPaymentTotal(VtexOrderTotal vtexOrderTotal);

	@Mappings({
			@Mapping(target = "receiverName", expression = "java(vtexShippingData.getAddress() != null ? vtexShippingData.getAddress().getReceiverName() : null)"),
			@Mapping(target = "postalCode", expression = "java(vtexShippingData.getAddress() != null ? vtexShippingData.getAddress().getPostalCode() : null)"),
			@Mapping(target = "city", expression = "java(vtexShippingData.getAddress() != null ? vtexShippingData.getAddress().getCity() : null)"),
			@Mapping(target = "state", expression = "java(vtexShippingData.getAddress() != null ? vtexShippingData.getAddress().getState() : null)"),
			@Mapping(target = "country", expression = "java(vtexShippingData.getAddress() != null ? vtexShippingData.getAddress().getCountry() : null)"),
			@Mapping(target = "street", expression = "java(vtexShippingData.getAddress() != null ? vtexShippingData.getAddress().getStreet() : null)"),
			@Mapping(target = "number", expression = "java(vtexShippingData.getAddress() != null ? vtexShippingData.getAddress().getNumber() : null)") })
	ShippingDetail getShippingDetail(VtexShippingData vtexShippingData);
}
