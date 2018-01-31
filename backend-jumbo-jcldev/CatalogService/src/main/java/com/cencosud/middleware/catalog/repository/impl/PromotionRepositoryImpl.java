package com.cencosud.middleware.catalog.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

import com.cencosud.middleware.catalog.configuration.ApplicationConfig;
import com.cencosud.middleware.catalog.model.Highlight;
import com.cencosud.middleware.catalog.model.Promotion;
import com.cencosud.middleware.catalog.model.PromotionInfo;
import com.cencosud.middleware.catalog.repository.PromotionRepository;
import com.cencosud.middleware.catalog.util.VtexServiceUtil;

@Repository
public class PromotionRepositoryImpl implements PromotionRepository {
	@Autowired
	private VtexServiceUtil serviceUtil;

	@Autowired
	private ApplicationConfig applicationConfig;
	
	private static final String PATH_PROMOTIONS = "/dataentities/PM/documents/Promos";
	private static final String FLAG = "flag_";
	private static final String TCENCO = "tcenco";

	@Override
	public Map<String, Promotion> getPromotions() {
		Map<String, String> queryParams = new HashMap<>();
		List<String> highlights = applicationConfig.getVtex().getR2().getBusiness().getHighlights();
		
		queryParams.put("_fields", "value,id");

		PromotionInfo promoInfo = serviceUtil.executeService(PATH_PROMOTIONS, null, PromotionInfo.class, HttpMethod.GET,
				queryParams);

		promoInfo.getValue().values().stream()
				.forEach(promotion -> {
					Highlight highlight = null; 
					String highlightId = promotion.getName();
					
					highlightId = highlightId.split("-")[0].trim()
							.replace(" ", "_")
							.replace("ñ", "n")
							.replace("%", "pc")
							.replace("á", "a")
							.replace("é", "e")
							.replace("í", "i")
							.replace("ó", "o")
							.replace("ú", "u").toLowerCase();
					
					highlight = new Highlight();
					String imageUrl;
					Boolean isBusiness = null;
					
					if(highlightId.contains(TCENCO)) {
						imageUrl = FLAG + "descuento_cencosud";
						isBusiness = true;
						highlight.setId(highlightId);
					}else {
						imageUrl = FLAG + highlightId;
						highlight.setId(FLAG + highlightId);
						isBusiness = highlights.indexOf(highlight.getId())>-1;
					}
						
					
					if (isBusiness){						

						highlight.setName(promotion.getName());
						highlight.setImageAvailable(true);
						highlight.setPng(applicationConfig.getVtex().getR2().getBusiness().getSourcesBaseURL()+"png/"+imageUrl+".png");
						highlight.setSvg(applicationConfig.getVtex().getR2().getBusiness().getSourcesBaseURL()+"svg/"+imageUrl+".svg");
						
					}
					promotion.setHighlight(highlight);
				});

		return promoInfo.getValue();

	}

}
