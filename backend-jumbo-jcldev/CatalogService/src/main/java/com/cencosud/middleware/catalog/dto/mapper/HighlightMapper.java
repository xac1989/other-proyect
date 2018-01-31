package com.cencosud.middleware.catalog.dto.mapper;

import org.mapstruct.Mapper;

import com.cencosud.middleware.catalog.client.VtexHighlight;
import com.cencosud.middleware.catalog.model.Highlight;

@Mapper(componentModel="spring")
public interface HighlightMapper {
	VtexHighlight highlightToVtexHighlight(Highlight highlight);
}
