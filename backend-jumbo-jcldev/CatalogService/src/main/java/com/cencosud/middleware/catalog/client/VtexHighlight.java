package com.cencosud.middleware.catalog.client;

public class VtexHighlight {
	
	private String id;
	private String name;
	private String svg;
	private String png;
	private Boolean imageAvailable;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VtexHighlight [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", svg=");
		builder.append(svg);
		builder.append(", png=");
		builder.append(png);
		builder.append(", imageAvailable=");
		builder.append("]");
		return builder.toString();
	}
	
}
