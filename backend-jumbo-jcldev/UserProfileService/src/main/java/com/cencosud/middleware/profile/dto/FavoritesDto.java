package com.cencosud.middleware.profile.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.cencosud.middleware.profile.model.Favorites;

public class FavoritesDto {

	List<String> categories;

	public FavoritesDto() { }

	public FavoritesDto(List<String> categories) {
		if (!CollectionUtils.isEmpty(categories)) {
			this.categories = categories;
		}
	}

	public FavoritesDto(Favorites favorites) {
		this(new ArrayList<>(favorites == null ? new ArrayList<String>() : 
			(favorites.getCategories() == null ? new ArrayList<String>() : 
				favorites.getCategories()) ));
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
}
