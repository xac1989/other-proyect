package com.cencosud.middleware.coupon.model;


public class CouponsResult {

	private String image;
	private String discount;
	private String message;
	private String offlineBarcode;
	private String onlineCode;
	private String endDate;
	private String legal;
	private String channel;
	
	public CouponsResult(){
		super();
	}

	public CouponsResult(String image, String discount, String message, String offlineBarcode, String onlineCode,
			String endDate, String legal, String channel) {
		super();
		this.image = image;
		this.discount = discount;
		this.message = message;
		this.offlineBarcode = offlineBarcode;
		this.onlineCode = onlineCode;
		this.endDate = endDate;
		this.legal = legal;
		this.channel = channel;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getOfflineBarcode() {
		return offlineBarcode;
	}
	public void setOfflineBarcode(String offlineBarcode) {
		this.offlineBarcode = offlineBarcode;
	}
	public String getOnlineCode() {
		return onlineCode;
	}
	public void setOnlineCode(String onlineCode) {
		this.onlineCode = onlineCode;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getLegal() {
		return legal;
	}
	public void setLegal(String legal) {
		this.legal = legal;
	}
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	

}
