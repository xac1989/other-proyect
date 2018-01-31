package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.cencosud.mobile.list.dto.PromotionDto;
import com.cencosud.mobile.list.dto.ShoppingListProductDto;
import com.cencosud.mobile.list.dto.ShoppingListPromotionDto;
import com.cencosud.mobile.list.util.PromotionUtil;

public class PromotionUtilTest {

	private final String GROUP_CENCO = "cencoCard";
	private final String GROUP_OTHER_PAYMENT = "otherPayment";

	private final BigDecimal promoCencoPrice = new BigDecimal(200);
	private final BigDecimal promoOtherPrice = new BigDecimal(300);
	private final BigDecimal prod1Price = new BigDecimal(400);
	private final BigDecimal prod2Price = new BigDecimal(500);

	@Test
	public void onlyCencoPromotion() {
		PromotionDto promotion = getPromotion(promoCencoPrice, GROUP_CENCO);
		List<PromotionDto> promotions = Arrays.asList(promotion);
		
		ShoppingListProductDto prod1 = getProduct(1, prod1Price, promotions);
		ShoppingListProductDto prod2 = getProduct(2, prod2Price);
		
		List<ShoppingListProductDto> products = Arrays.asList(prod1, prod2);
		
		List<ShoppingListPromotionDto> totalPromotions = PromotionUtil.getShoppingListPromotions(products);
		
		ShoppingListPromotionDto cencoPromotion = getPromotionByGroup(totalPromotions, GROUP_CENCO);
		ShoppingListPromotionDto otherPromotion = getPromotionByGroup(totalPromotions, GROUP_OTHER_PAYMENT);
		
		assertNotNull("cenco promotion should not be null", cencoPromotion);
		assertNull("other promotion should be null", otherPromotion);
	
		assertEquals(promoCencoPrice.add(prod2Price), cencoPromotion.getTotalPrice());
	}

	@Test
	public void onlyOtherPromotion() {
		PromotionDto promotion = getPromotion(promoOtherPrice, GROUP_OTHER_PAYMENT);
		List<PromotionDto> promotions = Arrays.asList(promotion);
		
		ShoppingListProductDto prod1 = getProduct(1, prod1Price, promotions);
		ShoppingListProductDto prod2 = getProduct(2, prod2Price);
		
		List<ShoppingListProductDto> products = Arrays.asList(prod1, prod2);
		
		List<ShoppingListPromotionDto> totalPromotions = PromotionUtil.getShoppingListPromotions(products);
		
		ShoppingListPromotionDto cencoPromotion = getPromotionByGroup(totalPromotions, GROUP_CENCO);
		ShoppingListPromotionDto otherPromotion = getPromotionByGroup(totalPromotions, GROUP_OTHER_PAYMENT);
		
		assertNull("cenco promotion should not be null", cencoPromotion);
		assertNotNull("other promotion should be null", otherPromotion);
	
		assertEquals(promoOtherPrice.add(prod2Price), otherPromotion.getTotalPrice());
	}

	@Test
	public void bothPromotions() {
		BigDecimal prod3Price = new BigDecimal(150);
		
		PromotionDto promotionCenco = getPromotion(promoCencoPrice, GROUP_CENCO);
		PromotionDto promotionOther = getPromotion(promoOtherPrice, GROUP_OTHER_PAYMENT);
		
		ShoppingListProductDto prod1 = getProduct(1, prod1Price, Arrays.asList(promotionCenco, promotionOther));
		ShoppingListProductDto prod2 = getProduct(2, prod2Price, Arrays.asList(promotionOther));
		ShoppingListProductDto prod3 = getProduct(3, prod3Price);
		
		List<ShoppingListProductDto> products = Arrays.asList(prod1, prod2, prod3);
		
		List<ShoppingListPromotionDto> totalPromotions = PromotionUtil.getShoppingListPromotions(products);
		
		ShoppingListPromotionDto cencoPromotion = getPromotionByGroup(totalPromotions, GROUP_CENCO);
		ShoppingListPromotionDto otherPromotion = getPromotionByGroup(totalPromotions, GROUP_OTHER_PAYMENT);
		
		assertNotNull("cenco promotion should not be null", cencoPromotion);
		assertNotNull("other promotion should not be null", otherPromotion);
	
		assertEquals(promoCencoPrice.add(promoOtherPrice).add(prod3Price), cencoPromotion.getTotalPrice());
	}

	private ShoppingListProductDto getProduct(int id, BigDecimal prodPrice) {
		return getProduct(id, prodPrice, null);
	}
	
	private ShoppingListProductDto getProduct(int id, BigDecimal prodPrice, List<PromotionDto> promotions) {
		if (promotions==null) {
			promotions = new ArrayList<>();
		}
		ShoppingListProductDto product1 = new ShoppingListProductDto();
		product1.setProductId(String.valueOf(id));
		product1.setPriceSubtotal(prodPrice);
		product1.setPromotions(promotions);
		return product1;
	}
	
	private ShoppingListPromotionDto getPromotionByGroup(List<ShoppingListPromotionDto> totalPromotions, 
			String group) {
		ShoppingListPromotionDto promotion = null;
		
		for (ShoppingListPromotionDto totalPromotion : totalPromotions) {
			if ((GROUP_CENCO.equals(group) && totalPromotion.getGroup().equals(GROUP_CENCO)) || 
					(!GROUP_CENCO.equals(group) && !totalPromotion.getGroup().equals(GROUP_CENCO))){
				promotion = totalPromotion;
				break;
			}
		}
		
		return promotion;
	}
	
	private PromotionDto getPromotion(BigDecimal prodPromoPrice, String group) {
		PromotionDto promotion = new PromotionDto();
		promotion.setGroup(group);
		promotion.setSubtotalAmount(prodPromoPrice);
		return promotion;
	}


}
