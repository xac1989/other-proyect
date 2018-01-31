package com.cencosud.middleware.catalog.component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.cencosud.middleware.catalog.client.VtexProduct;
import com.cencosud.middleware.catalog.client.VtexPromotion;
import com.cencosud.middleware.catalog.client.VtexSkuData;
import com.cencosud.middleware.catalog.configuration.ApplicationConfig;
import com.cencosud.middleware.catalog.configuration.ApplicationConfig.BusinessProperties;
import com.cencosud.middleware.catalog.dto.mapper.HighlightMapper;
import com.cencosud.middleware.catalog.dto.mapper.PriceMapper;
import com.cencosud.middleware.catalog.model.Promotion;
import com.cencosud.middleware.catalog.service.PromotionService;
import com.cencosud.middleware.catalog.service.impl.CatalogR2ServiceImpl;

@Component
public class PromotionLoader {
	
	private static final String TITLE_NOMINAL = "nominal";

	private static final String TITLE_MX = "mx";

	private static final String TITLE_DEAL = "deal";

	private static final String TITLE_MXN = "mxn";

	private static final String TITLE_DISCOUNT = "discount";

	private static Logger logger = LoggerFactory.getLogger(CatalogR2ServiceImpl.class);
	
	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    
	@Autowired
	private ApplicationConfig applicationConfig;
	
	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	private HighlightMapper highlightMapper;

	public PromotionService getPromotionService() {
		return promotionService;
	}

	@Autowired
	public void setPromotionService(PromotionService promotionService) {
		this.promotionService = promotionService;
	}


	public List<VtexPromotion> loadPromotions(VtexProduct vtexProduct, String salesChannel) {
		
		
		List<VtexPromotion> promotionsList = null;
		Map<String, VtexPromotion> promotionsMap = new HashMap<>();
		
		if(insuficientSkuData(vtexProduct, salesChannel)){
			logger.warn("Some Data needed to process promotions was not found: sku:{}, salesChannel:{}, product:{}",vtexProduct.getSkuData(), salesChannel, vtexProduct);
			return new ArrayList<>();
		}
		
		ArrayList<VtexSkuData> skuData = vtexProduct.getSkuData();
		String sc = vtexProduct.getSalesChannel();
		BigDecimal basePrice = PriceMapper.mapListPrice(vtexProduct);
		
		if(hasPromotionIds(skuData)){
			
			for (String promoId : getPromotionIds(skuData)) {
				Promotion promo = this.promotionService.getPromotions().get(promoId);
				
				if (promo==null){
					continue;
				}
				
				if(!isThisSalesChannelPromo(promo.getSalesChannels(),sc)) {
					continue;
				}
				
				if(!isValidDate(promo.getStart(), promo.getEnd())){
					continue;
				}
				
				if(!isAvailableToday(promo.getAvailableDays())){
					continue;
				}			

				VtexPromotion vtexPromo = buildVtexPromo(promo, basePrice);
				
				if (vtexPromo != null && 
						vtexPromo.getGroup() != null &&	
						isBetterThan(vtexPromo, promotionsMap.get(vtexPromo.getGroup()))) {
					promotionsMap.put(vtexPromo.getGroup(),vtexPromo);
				}
			}
		}
		
		promotionsList = new ArrayList<>(promotionsMap.values());
		
		promotionsList.sort((a, b)->{
			if(a.getGroup().equals("cencoCard")) {
				return -3;
			}
			if(a.getGroup().equals("otherPayments")) {
				return -2;
			}
			if(a.getGroup().equals("info")) {
				return -1;
			}
			return 0;
		});
		
		if(!promotionsList.isEmpty()) {
			vtexProduct.setPromotionDescription(promotionsList.get(0).getDescription());
			vtexProduct.setPromotionShortDescription(promotionsList.get(0).getShortDescription());
		}else {
			vtexProduct.setPromotionDescription("");
			vtexProduct.setPromotionShortDescription("");
		}
		
		return promotionsList;
	}
	

	private Object cleanKey(String key) {
		return StringUtils.trimWhitespace(key.toLowerCase());
	}

	private boolean insuficientSkuData(VtexProduct vtexProduct, String salesChannel) {
		
		if(vtexProduct.getSkuData()== null ||
				CollectionUtils.isEmpty(vtexProduct.getSkuData())||
				CollectionUtils.isEmpty(vtexProduct.getSkuData().get(0).getPromotions()) ||
				StringUtils.isEmpty(salesChannel) ||
				CollectionUtils.isEmpty(vtexProduct.getItems()) ||
				CollectionUtils.isEmpty(vtexProduct.getItems().get(0).getSellers()) ||
				vtexProduct.getItems().get(0).getSellers().get(0).getCommertialOffer() == null ||
				vtexProduct.getItems().get(0).getSellers().get(0).getCommertialOffer().getListPrice() == null ||
				BigDecimal.ZERO.compareTo(vtexProduct.getItems().get(0).getSellers().get(0).getCommertialOffer().getListPrice())==0){
			
			return true;
		}
		
		return false;
	}
	


	private boolean isBetterThan(VtexPromotion candidatePromo, VtexPromotion lastPromo) {
		if(lastPromo == null){
			return true;
		}
		return candidatePromo.compareTo(lastPromo) == 1;
	}

	private VtexPromotion buildVtexPromo(Promotion promo, BigDecimal basePrice) {
		
		VtexPromotion vtexPromotion = new VtexPromotion();
		
		BigDecimal discountAmount = BigDecimal.ZERO;
		BigDecimal discountRate = BigDecimal.ZERO;
		BigDecimal promotionPrice = BigDecimal.ZERO;
		BigDecimal singleProductPrice = BigDecimal.ZERO;
		Integer quantityM = new Integer(0);
		Integer quantityN = new Integer(0); 
		String titleString = new String();
		String titleColor = new String();
		String groupString = new String();
		String typeString = new String();
		
		
		
		quantityM = promo.getQuantity()!=null && !promo.getQuantity().isEmpty()?new Integer(promo.getQuantity()):1;
		
		
		switch(promo.getType()) {
			case "p":	
				discountRate = new BigDecimal(promo.getValue());
				discountAmount = amountFromPercentage(basePrice, discountRate);
				singleProductPrice = promotionPrice = basePrice.subtract(discountAmount);
				
				switch(promo.getPromotionType()) {
					case "regular":
						titleString = getBussinesConf().getPromotionTitles().get(TITLE_DISCOUNT)
								.replace("{1}", discountRate.toBigInteger().toString());
						break;
						
					case "for_the_price_of":
						quantityN = 0;//TODO: find a way to know or calculate N value
						titleString = getBussinesConf().getPromotionTitles().get(TITLE_MXN)
								.replace("{1}", quantityM.toString()
								.replace("{2}", quantityN.toString()));
						break;
				}
					
				break;

			case "n"://TODO: deprecate
				discountAmount = new BigDecimal(promo.getValue());
				discountRate = percentageRate(basePrice, discountAmount);
				singleProductPrice = promotionPrice = basePrice.subtract(discountAmount);
				titleString = getBussinesConf().getPromotionTitles().get(TITLE_NOMINAL).replace("{1}", discountAmount.toBigInteger().toString());
				break;
				
			case "m":
				
				singleProductPrice = new BigDecimal(promo.getValue());
				discountAmount = basePrice.subtract(singleProductPrice);
				discountRate = percentageRate(basePrice, basePrice.subtract(singleProductPrice));
				promotionPrice = singleProductPrice.multiply(new BigDecimal(quantityM));
								
				if(quantityM == 1) {
					titleString = getBussinesConf().getPromotionTitles().get(TITLE_DEAL);
				}else {
					titleString = getBussinesConf().getPromotionTitles().get(TITLE_MX)
							.replace("{1}",quantityM.toString());
				}
				break;
				
			
			 default:
				 return null;
		}
		
		
		groupString = getBussinesConf().getPromotionKeys().get(cleanKey(promo.getGroup()));		
		typeString = getBussinesConf().getPromotionTypes().get(cleanKey(promo.getType()));
		titleColor = getBussinesConf().getPromotionColors().get(groupString);
		
						
		vtexPromotion.setPromotionPrice(promotionPrice);
		vtexPromotion.setDiscountAmount(discountAmount);
		vtexPromotion.setDiscountRate(discountRate);
		vtexPromotion.setGroup(groupString);
		vtexPromotion.setType(typeString);
		vtexPromotion.setSingleProductPrice(singleProductPrice);
		vtexPromotion.setQuantityM(quantityM);
		vtexPromotion.setQuantityN(quantityN);
		vtexPromotion.setTitle(titleString);
		vtexPromotion.setTitleColor(titleColor);
		vtexPromotion.setExpirationDate(promo.getEnd());	
		vtexPromotion.setHighlight(highlightMapper.highlightToVtexHighlight(promo.getHighlight()));
		vtexPromotion.setDescription(promo.getDescription());
		vtexPromotion.setShortDescription(promo.getShort_description());
		
		return vtexPromotion;
	}

	private BusinessProperties getBussinesConf() {
		return applicationConfig.getVtex().getR2().getBusiness();
	}

	private boolean isAvailableToday(List<String> availableDays) {
		if(availableDays.isEmpty()){
			return true;
		}
		return availableDays.contains(String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1));
	}

	private boolean isValidDate(Date start, Date end) {
		Date now = new Date();
		return now.after(start) && now.before(end);
	}

	private boolean isThisSalesChannelPromo(List<String> scList, String sc) {
		if(scList.isEmpty()){
			return true;
		}
		return scList.contains(sc);
	}

	private List<String> getPromotionIds(ArrayList<VtexSkuData> skuData) {
		return skuData.get(0).getPromotions();
	}

	private boolean hasPromotionIds(ArrayList<VtexSkuData> skuData) {
		return skuData != null && !skuData.isEmpty() && !skuData.get(0).getPromotions().isEmpty();
	}

	public static BigDecimal amountFromPercentage(BigDecimal base, BigDecimal percentage){
	    return base.multiply(percentage).divide(ONE_HUNDRED, 2, RoundingMode.HALF_EVEN);
	}
	
	public static BigDecimal percentageRate(BigDecimal base, BigDecimal pctAmount){
		System.out.println();
	    return pctAmount.multiply(ONE_HUNDRED).divide(base, 2, RoundingMode.HALF_EVEN);

	}


}