package com.cencosud.mobile.categories.adapter;

import javax.ws.rs.core.Response;

@javax.annotation.Generated(value = "class com.github.mfpdev.adapters.swagger.codegen.MfpAdapterCodegen", date = "2017-01-09T10:51:58.936-03:00")
public interface CategoriesApiService {

	Response categoriesGet(String region, String version);

    Response filterFieldsGet(String region, String version, String filter, String q, boolean isDepartment, boolean deals);
}
