package com.cencosud.middleware.catalog.dto.productdetail;

public class HighlightDto {
	
	private String name;
	private String svg;
	private String png;
	private Boolean imageAvailable;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPng() {
		return png;
	}
	public void setPng(String png) {
		this.png = png;
	}
	public Boolean getImageAvailable() {
		return imageAvailable;
	}
	public void setImageAvailable(Boolean imageAvailable) {
		this.imageAvailable = imageAvailable;
	}
	public String getSvg() {
		return svg;
	}
	public void setSvg(String svg) {
		this.svg = svg;
	}	
	
}
