package com.cencosud.middleware.catalog.dto.mapper;

import com.cencosud.middleware.catalog.dto.search.SearchResultDto;
import com.cencosud.middleware.catalog.model.SearchResult;

public interface SearchProductMapper {

	SearchResultDto generateSearchResult(SearchResult searchResult);

}
