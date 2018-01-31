package com.cencosud.middleware.order.client.dto;

import java.util.ArrayList;
import java.util.List;

import com.cencosud.middleware.order.model.Item;
import com.cencosud.middleware.order.model.Order;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class VtexOrder {
	private String orderId;
	private String creationDate;
	private String clientName;
	private int totalValue;
	private String paymentNames;
	private String status;
	private String statusDescription;
	private String marketPlaceOrderId;
	private String sequence;
	private String salesChannel;
	private String affiliateId;
	private String origin;
	private boolean workflowInErrorState;
	private boolean workflowInRetry;
	private String lastMessageUnread;
	private String shippingEstimatedDate;
	private boolean orderIsComplete;
	private String listId;
	private String listType;
	private List<VtexItem> items;
	private String authorizedDate;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public int getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}

	public String getPaymentNames() {
		return paymentNames;
	}

	public void setPaymentNames(String paymentNames) {
		this.paymentNames = paymentNames;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public String getMarketPlaceOrderId() {
		return marketPlaceOrderId;
	}

	public void setMarketPlaceOrderId(String marketPlaceOrderId) {
		this.marketPlaceOrderId = marketPlaceOrderId;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getSalesChannel() {
		return salesChannel;
	}

	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}

	public String getAffiliateId() {
		return affiliateId;
	}

	public void setAffiliateId(String affiliateId) {
		this.affiliateId = affiliateId;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public boolean isWorkflowInErrorState() {
		return workflowInErrorState;
	}

	public void setWorkflowInErrorState(boolean workflowInErrorState) {
		this.workflowInErrorState = workflowInErrorState;
	}

	public boolean isWorkflowInRetry() {
		return workflowInRetry;
	}

	public void setWorkflowInRetry(boolean workflowInRetry) {
		this.workflowInRetry = workflowInRetry;
	}

	public String getLastMessageUnread() {
		return lastMessageUnread;
	}

	public void setLastMessageUnread(String lastMessageUnread) {
		this.lastMessageUnread = lastMessageUnread;
	}

	public String getShippingEstimatedDate() {
		return shippingEstimatedDate;
	}

	public void setShippingEstimatedDate(String shippingEstimatedDate) {
		this.shippingEstimatedDate = shippingEstimatedDate;
	}

	public boolean isOrderIsComplete() {
		return orderIsComplete;
	}

	public void setOrderIsComplete(boolean orderIsComplete) {
		this.orderIsComplete = orderIsComplete;
	}

	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public List<VtexItem> getItems() {
		return items;
	}

	public void setItems(List<VtexItem> items) {
		this.items = items;
	}

	public String getAuthorizedDate() {
		return authorizedDate;
	}

	public void setAuthorizedDate(String authorizedDate) {
		this.authorizedDate = authorizedDate;
	}

	public Order toModelOrder() {
		Order order = new Order();
		order.setId(this.orderId);
		order.setDate(this.creationDate);

		if (this.items == null) {
			this.items = new ArrayList<VtexItem>();
		}
		List<Item> orderItems = new ArrayList<Item>(this.items.size());
		for (VtexItem currentvtexItem : this.items) {
			orderItems.add(currentvtexItem.toModelItem());
		}
		order.setItems(orderItems);

		return order;

	}
}
