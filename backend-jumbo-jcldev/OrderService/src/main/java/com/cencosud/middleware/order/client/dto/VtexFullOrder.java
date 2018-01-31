package com.cencosud.middleware.order.client.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cencosud.middleware.order.model.ItemDetail;
import com.cencosud.middleware.order.model.OrderDetail;
import com.cencosud.middleware.order.model.PaymentDetail;
import com.cencosud.middleware.order.model.PaymentTotal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class VtexFullOrder {

	private String orderId;
	private String sequence;
	private String marketplaceOrderId;
	private String marketplaceServicesEndpoint;
	private String sellerOrderId;
	private String origin;
	private String affiliateId;
	private String salesChannel;
	private String merchantName;
	private String status;
	private String statusDescription;
	private int value;
	private String creationDate;
	private String lastChange;
	private String orderGroup;
	private String giftRegistryData;
	private Map<String, Object> marketingData;
	private String callCenterOperatorData;
	private String followUpEmail;
	private String lastMessage;
	private String hostname;
	private String changesAttachment;
	private String openTextField;
	private int roundingError;
	private String orderFormId;
	private List<String> marketplaceItems;

	
	private Map<String, Object> clientProfileData;
	private Map<String, Object> ratesAndBenefitsData;
	private Map<String, Object> packageAttachment;
	
	private List<VtexFullOrderItem> items;
	private List<VtexOrderTotal> totals;
	private List<VtexSeller> sellers;
	private VtexShippingData shippingData;
	private VtexPaymentDetail paymentData;
	
	
	
	public List<String> getMarketplaceItems() {
		return marketplaceItems;
	}
	public void setMarketplaceItems(List<String> marketplaceItems) {
		this.marketplaceItems = marketplaceItems;
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getMarketplaceOrderId() {
		return marketplaceOrderId;
	}
	public void setMarketplaceOrderId(String marketplaceOrderId) {
		this.marketplaceOrderId = marketplaceOrderId;
	}
	public String getMarketplaceServicesEndpoint() {
		return marketplaceServicesEndpoint;
	}
	public void setMarketplaceServicesEndpoint(String marketplaceServicesEndpoint) {
		this.marketplaceServicesEndpoint = marketplaceServicesEndpoint;
	}
	public String getSellerOrderId() {
		return sellerOrderId;
	}
	public void setSellerOrderId(String sellerOrderId) {
		this.sellerOrderId = sellerOrderId;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getAffiliateId() {
		return affiliateId;
	}
	public void setAffiliateId(String affiliateId) {
		this.affiliateId = affiliateId;
	}
	public String getSalesChannel() {
		return salesChannel;
	}
	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
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
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getLastChange() {
		return lastChange;
	}
	public void setLastChange(String lastChange) {
		this.lastChange = lastChange;
	}
	public String getOrderGroup() {
		return orderGroup;
	}
	public void setOrderGroup(String orderGroup) {
		this.orderGroup = orderGroup;
	}
	public String getGiftRegistryData() {
		return giftRegistryData;
	}
	public void setGiftRegistryData(String giftRegistryData) {
		this.giftRegistryData = giftRegistryData;
	}
	public Map<String, Object> getMarketingData() {
		return marketingData;
	}
	public void setMarketingData(Map<String, Object> marketingData) {
		this.marketingData = marketingData;
	}
	public String getCallCenterOperatorData() {
		return callCenterOperatorData;
	}
	public void setCallCenterOperatorData(String callCenterOperatorData) {
		this.callCenterOperatorData = callCenterOperatorData;
	}
	public String getFollowUpEmail() {
		return followUpEmail;
	}
	public void setFollowUpEmail(String followUpEmail) {
		this.followUpEmail = followUpEmail;
	}
	public String getLastMessage() {
		return lastMessage;
	}
	public void setLastMessage(String lastMessage) {
		this.lastMessage = lastMessage;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getChangesAttachment() {
		return changesAttachment;
	}
	public void setChangesAttachment(String changesAttachment) {
		this.changesAttachment = changesAttachment;
	}
	public String getOpenTextField() {
		return openTextField;
	}
	public void setOpenTextField(String openTextField) {
		this.openTextField = openTextField;
	}
	public int getRoundingError() {
		return roundingError;
	}
	public void setRoundingError(int roundingError) {
		this.roundingError = roundingError;
	}
	public String getOrderFormId() {
		return orderFormId;
	}
	public void setOrderFormId(String orderFormId) {
		this.orderFormId = orderFormId;
	}
	public Map<String, Object> getClientProfileData() {
		return clientProfileData;
	}
	public void setClientProfileData(Map<String, Object> clientProfileData) {
		this.clientProfileData = clientProfileData;
	}
	public Map<String, Object> getRatesAndBenefitsData() {
		return ratesAndBenefitsData;
	}
	public void setRatesAndBenefitsData(Map<String, Object> ratesAndBenefitsData) {
		this.ratesAndBenefitsData = ratesAndBenefitsData;
	}
	public VtexShippingData getShippingData() {
		return shippingData;
	}
	public void setShippingData(VtexShippingData shippingData) {
		this.shippingData = shippingData;
	}
	public VtexPaymentDetail getPaymentData() {
		return paymentData;
	}
	public void setPaymentData(VtexPaymentDetail paymentData) {
		this.paymentData = paymentData;
	}
	public Map<String, Object> getPackageAttachment() {
		return packageAttachment;
	}
	public void setPackageAttachment(Map<String, Object> packageAttachment) {
		this.packageAttachment = packageAttachment;
	}
	public List<VtexFullOrderItem> getItems() {
		return items;
	}
	public void setItems(List<VtexFullOrderItem> items) {
		this.items = items;
	}
	public List<VtexOrderTotal> getTotals() {
		return totals;
	}
	public void setTotals(List<VtexOrderTotal> totals) {
		this.totals = totals;
	}
	public List<VtexSeller> getSellers() {
		return sellers;
	}
	public void setSellers(List<VtexSeller> sellers) {
		this.sellers = sellers;
	}
	
	public OrderDetail toModelOrderDetail(){
		
		if(this.paymentData.getTransactions() == null){
			
		}
		List<PaymentDetail> paymentDetails = new ArrayList<PaymentDetail>(this.paymentData.getTransactions().size());
		for(VtexTransactionDetail currentvtexTransaction: this.paymentData.getTransactions()){
			paymentDetails.addAll(currentvtexTransaction.getPaymentsAsModelPaymentDetail());
		}
		
		
		if(this.totals == null){
			this.totals = new ArrayList<VtexOrderTotal>();
		}
		List<PaymentTotal> paymentTotals = new ArrayList<PaymentTotal>(this.totals.size());
		for(VtexOrderTotal currentvtexTotal: this.totals){
			paymentTotals.add(currentvtexTotal.toModelPaymentTotal());
		}
		
		if(this.items == null){
			this.items = new ArrayList<VtexFullOrderItem>();
		}
		List<ItemDetail> orderItems = new ArrayList<ItemDetail>(this.items.size());
		for(VtexFullOrderItem currentvtexItem: this.items){
			orderItems.add(currentvtexItem.toModelItemDetail());
		}
		
		
		return new OrderDetail(this.orderId, this.getCreationDate(), this.shippingData.toModelShippingDetail(), paymentDetails, paymentTotals, orderItems);
	
	}
	
	
}
