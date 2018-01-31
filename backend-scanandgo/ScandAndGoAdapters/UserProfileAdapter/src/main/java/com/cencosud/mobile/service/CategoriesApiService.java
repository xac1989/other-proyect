package com.cencosud.mobile.service;

import com.cencosud.middleware.category.model.Category;
import com.cencosud.mobile.exceptions.NotFoundException;

public interface CategoriesApiService {
      public Category categoriesGet(String categoryId)
      throws NotFoundException;
}
