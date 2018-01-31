package com.cencosud.mobile.dto.profile.jumbo;

/**
 * 
 * 
 * <h1>CategoryDto</h1>
 * <p>
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jun 19, 2017
 */
public class CategoryDto {
	private Integer id;
	private String name;
	private String icon;
	private String icon_pdf;
	private String icon_svg;
	private Boolean active;
	private String hierarchy;
	
	public CategoryDto() {
	}

	public CategoryDto(Integer id, String name, String icon, String icon_pdf, String icon_svg, Boolean active,
			String hierarchy) {
		super();
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.icon_pdf = icon_pdf;
		this.icon_svg = icon_svg;
		this.active = active;
		this.hierarchy = hierarchy;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
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
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return the icon_pdf
	 */
	public String getIcon_pdf() {
		return icon_pdf;
	}

	/**
	 * @param icon_pdf the icon_pdf to set
	 */
	public void setIcon_pdf(String icon_pdf) {
		this.icon_pdf = icon_pdf;
	}

	/**
	 * @return the icon_svg
	 */
	public String getIcon_svg() {
		return icon_svg;
	}

	/**
	 * @param icon_svg the icon_svg to set
	 */
	public void setIcon_svg(String icon_svg) {
		this.icon_svg = icon_svg;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * @return the hierarchy
	 */
	public String getHierarchy() {
		return hierarchy;
	}

	/**
	 * @param hierarchy the hierarchy to set
	 */
	public void setHierarchy(String hierarchy) {
		this.hierarchy = hierarchy;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CategoryDto [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", icon=");
		builder.append(icon);
		builder.append(", icon_pdf=");
		builder.append(icon_pdf);
		builder.append(", icon_svg=");
		builder.append(icon_svg);
		builder.append(", active=");
		builder.append(active);
		builder.append(", hierarchy=");
		builder.append(hierarchy);
		builder.append("]");
		return builder.toString();
	}
	
}
