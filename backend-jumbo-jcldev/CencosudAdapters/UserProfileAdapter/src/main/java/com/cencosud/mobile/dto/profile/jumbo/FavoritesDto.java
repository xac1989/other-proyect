package com.cencosud.mobile.dto.profile.jumbo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 
 * <h1>FavoritesDto</h1>
 * <p>
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jun 19, 2017
 */
@JsonAutoDetect
public class FavoritesDto {
    @JsonProperty("categories")
	private List<CategoryDto> categories;
	
	public FavoritesDto() {
	}

	public FavoritesDto(List<CategoryDto> categories) {
		super();
		this.categories = categories;
	}

	/**
	 * @return the categoriesDto
	 */
	@JsonProperty("categories")
	public List<CategoryDto> getCategories() {
		return categories;
	}

	/**
	 * @param categories the categoriesDto to set
	 */
	@JsonProperty("categories")
	public void setCategories(List<CategoryDto> categories) {
		this.categories = categories;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FavoritesDto [categories=");
		builder.append(categories);
		builder.append("]");
		return builder.toString();
	}
	
	
}
