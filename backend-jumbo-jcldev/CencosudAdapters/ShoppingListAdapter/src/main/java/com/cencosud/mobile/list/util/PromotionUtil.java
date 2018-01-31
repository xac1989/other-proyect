package com.cencosud.mobile.list.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cencosud.mobile.list.dto.PromotionDto;
import com.cencosud.mobile.list.dto.ShoppingListProductDto;
import com.cencosud.mobile.list.dto.ShoppingListPromotionDto;

public class PromotionUtil {
	
	private static final String GROUP_CENCO = "cencoCard";

	/**
	 * Returns list of totalPromotions for the given product list. If a product has a promotion, then
	 * the list of products will have a list of totalPromotions, in which the total price will be the
	 * sum of the: product promotion subtotalAmount (if the product has the given promotion) or the product 
	 * priceSubtotal (if the product does not has the given promotion)
	 * 
	 * Example: 
	 * 
	 * 	{
		"listId": "986a0ea7-91b6-11e7-9538-0a65476ebedc",
		"products": [{
			"skuId": "427",
			"priceSubtotal": 2980,
			"promotions": [{
				"group": "otherPayments",
				"subtotalAmount": 2682
			}]
		},
		{
			"skuId": "530",
			"priceSubtotal": 1199,
			"promotions": []
		}],
		"totalPromotions": [{
			"group": "otherPayments",
			"totalPrice": 3881 ---> 2682 (promotion subtotalAmount) + 1199 (product priceSubtotal)
		}]
	},
	
	 * @param products
	 * @return
	 */
	public static List<ShoppingListPromotionDto> getShoppingListPromotions(List<ShoppingListProductDto> products) {
		// promotion map, with key=group
		Map<String, ShoppingListPromotionDto> promotionMap = new HashMap<>();
		
		// map <productId,<promotionGroup,promotionGroup>>
		Map<String,Map<String,PromotionDto>> prodPromotionMap = new HashMap<>();
		
		// first we populate the promotionMap (that will contain all the promotions) and 
		// prodPromotionMap (that will identify for a given product what promotions it has)
		loadMaps(products, promotionMap, prodPromotionMap);
		
		// then we calculate the total price for each promotion
		for (ShoppingListProductDto product : products) {
			
			// first we add the price without promotion for the promotions not present in this product
			for (ShoppingListPromotionDto promotion : promotionMap.values()) {
				if (! hasPromotion(prodPromotionMap, product.getProductId(), promotion.getGroup())) {
					boolean applyOtherPromotion=false;
					if (GROUP_CENCO.equals(promotion.getGroup())){
						// search for otherPayment promotion
						PromotionDto otherPaymentPromotion = getAnyProductPromotion(prodPromotionMap, product.getProductId());
						if (otherPaymentPromotion!=null) {
							promotion.setTotalPrice(promotion.getTotalPrice().add(otherPaymentPromotion.getSubtotalAmount()));
							applyOtherPromotion=true;
						}
						
					}
					if (!applyOtherPromotion) {
						promotion.setTotalPrice(promotion.getTotalPrice().add(product.getPriceSubtotal()));
					}
				}
			}
			
			// then we add the promotion price for promotions within this product
			for (PromotionDto productPromotion : product.getPromotions()) {
				ShoppingListPromotionDto shoppingPromotion = promotionMap.get(productPromotion.getGroup());
				shoppingPromotion.setTotalPrice(shoppingPromotion.getTotalPrice()
						.add(productPromotion.getSubtotalAmount()));
			}
		}
		
		return new ArrayList<>(promotionMap.values());
	}
	
	public static List<ShoppingListPromotionDto> calculateTotalPromotions(List<ShoppingListProductDto> products){
		ShoppingListPromotionDto promotionsListPrice = new ShoppingListPromotionDto("listPrice",new BigDecimal("0"));
		ShoppingListPromotionDto promotionsPrice = new ShoppingListPromotionDto("price",new BigDecimal("0"));
		ShoppingListPromotionDto promotionsOtherPayment = new ShoppingListPromotionDto("otherPayments",new BigDecimal("0"));
		ShoppingListPromotionDto promotionsTcenco = new ShoppingListPromotionDto("cencoCard",new BigDecimal("0"));
		boolean cencoFlag = false;
		for(ShoppingListProductDto tempShoppingList:products) {
			promotionsListPrice.setTotalPrice(promotionsListPrice.getTotalPrice().add(tempShoppingList.getListPrice()));
			promotionsPrice.setTotalPrice(promotionsPrice.getTotalPrice().add(tempShoppingList.getPrice()));
			
			List<PromotionDto> listPromotion = tempShoppingList.getPromotions();
			
			if(listPromotion != null) {
				PromotionDto cencoPromotion = getBestPromotion(listPromotion, true);
				PromotionDto otherPromotion = getBestPromotion(listPromotion, false);
				
				if(otherPromotion != null) {
					if(cencoPromotion != null) {
						cencoFlag = true;
						promotionsTcenco.setTotalPrice(promotionsTcenco.getTotalPrice().add(cencoPromotion.getSubtotalAmount()));
					}else {
						promotionsTcenco.setTotalPrice(promotionsTcenco.getTotalPrice().add(otherPromotion.getSubtotalAmount()));
					}
					promotionsOtherPayment.setTotalPrice(promotionsOtherPayment.getTotalPrice().add(otherPromotion.getSubtotalAmount()));
				}else {
					if(cencoPromotion != null) {
						cencoFlag = true;
						promotionsTcenco.setTotalPrice(promotionsTcenco.getTotalPrice().add(cencoPromotion.getSubtotalAmount()));
					}else {
						validatePriceListPrice(promotionsTcenco, tempShoppingList);
					}
					validatePriceListPrice(promotionsOtherPayment, tempShoppingList);
				}
			}else {
				validatePriceListPrice(promotionsOtherPayment, tempShoppingList);
				validatePriceListPrice(promotionsTcenco, tempShoppingList);
			}
		}
		
		List<ShoppingListPromotionDto> listResponse = new ArrayList<>(4);
		listResponse.add(promotionsListPrice);
		listResponse.add(promotionsPrice);
		listResponse.add(promotionsOtherPayment);
		if(cencoFlag) {
			listResponse.add(promotionsTcenco);
		}
		
		return listResponse;
	}

	private static void validatePriceListPrice(ShoppingListPromotionDto promotionsOtherPayment,
			ShoppingListProductDto tempShoppingList) {
		if(tempShoppingList.getPrice() != null) {
			if(tempShoppingList.getListPrice() != null) {
				if(tempShoppingList.getPrice().compareTo(tempShoppingList.getListPrice()) < 0) {
					promotionsOtherPayment.setTotalPrice(promotionsOtherPayment.getTotalPrice().add(tempShoppingList.getPrice()));
				}else {
					promotionsOtherPayment.setTotalPrice(promotionsOtherPayment.getTotalPrice().add(tempShoppingList.getListPrice()));
				}
			}
		}
	}

	private static PromotionDto getAnyProductPromotion(Map<String, Map<String, PromotionDto>> prodPromotionMap,
			String productId) {
		PromotionDto promotion=null;
		if (prodPromotionMap.containsKey(productId)){
			promotion = prodPromotionMap.get(productId).values().iterator().next();
		}
		return promotion;
	}

	private static void loadMaps(List<ShoppingListProductDto> products,
			Map<String, ShoppingListPromotionDto> promotionMap, 
			Map<String, Map<String, PromotionDto>> prodPromotionMap) {
		
		for (ShoppingListProductDto product : products) {
			if (product.getPromotions()!=null) {
				for (PromotionDto promotion : product.getPromotions()) {
					if (!promotionMap.containsKey(promotion.getGroup())){
						promotionMap.put(promotion.getGroup(), 
								new ShoppingListPromotionDto(promotion.getGroup(), BigDecimal.ZERO));
					}
					
					Map<String,PromotionDto> promotionGroupMap = prodPromotionMap.get(product.getProductId());
					
					if (promotionGroupMap==null) {
						promotionGroupMap = new HashMap<>();
						prodPromotionMap.put(product.getProductId(), promotionGroupMap);
					}
					
					promotionGroupMap.put(promotion.getGroup(), promotion);
				} 
			}
		}
	}

	private static boolean hasPromotion(Map<String, Map<String, PromotionDto>> prodPromotionMap,
			String productId, String promotionGroup) {
		boolean hasPromotion=false;
		
		if (prodPromotionMap.containsKey(productId)) {
			Map<String,PromotionDto> promotionGroupMap = prodPromotionMap.get(productId);
			hasPromotion = promotionGroupMap.containsKey(promotionGroup);
		}
		return hasPromotion;
	}
	
	private static PromotionDto getBestPromotion (List<PromotionDto> promotionList, boolean isTcenco) {
		PromotionDto bestPromotion = null;
		for(PromotionDto tmpPromotion:promotionList) {
			if(tmpPromotion.getGroup().equals(GROUP_CENCO) && isTcenco) {
				bestPromotion = tmpPromotion;
			}
			if(!tmpPromotion.getGroup().equals(GROUP_CENCO) && !isTcenco) {
				bestPromotion = tmpPromotion;
			}
		}
		return bestPromotion;
	}
	
}
