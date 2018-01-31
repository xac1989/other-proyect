package com.cencosud.middleware.catalog.model;

public class Highlight {
	private String id;
	private String name;
	private String svg;
	private String png;
	private Boolean imageAvailable;
	
	public Highlight() {
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the svg
	 */
	public String getSvg() {
		return svg;
	}

	/**
	 * @param svg the svg to set
	 */
	public void setSvg(String svg) {
		this.svg = svg;
	}

	/**
	 * @return the png
	 */
	public String getPng() {
		return png;
	}

	/**
	 * @param png the png to set
	 */
	public void setPng(String png) {
		this.png = png;
	}

	/**
	 * @return the imageAvailable
	 */
	public Boolean getImageAvailable() {
		return imageAvailable;
	}

	/**
	 * @param imageAvailable the imageAvailable to set
	 */
	public void setImageAvailable(Boolean imageAvailable) {
		this.imageAvailable = imageAvailable;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Highlight [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", svg=");
		builder.append(svg);
		builder.append(", png=");
		builder.append(png);
		builder.append(", imageAvailable=");
		builder.append(imageAvailable);
		builder.append("]");
		return builder.toString();
	}

}
