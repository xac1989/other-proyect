package utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utils {
	
	private static final List<String> orderOptions = Arrays.asList(
			"OrderByPriceASC",
			"OrderByPriceDESC",
			"OrderByTopSaleDESC",
			"OrderByReviewRateDESC",
			"OrderByNameASC",
			"OrderByNameDESC",
			"OrderByReleaseDateDESC",
			"OrderByBestDiscountDESC",
			"OrderByPriceAsc",
			"OrderByPriceDesc",
			"OrderByTopSaleDesc",
			"OrderByReviewRateDesc",
			"OrderByNameAsc",
			"OrderByNameDesc",
			"OrderByReleaseDateDesc",
			"OrderByBestDiscountDesc");
	
	public static String randomOption()  {
		return orderOptions.get(new Random().nextInt(orderOptions.size()));
	}

	public static boolean isString(String str){
		String regex = "^[a-zA-Z]+$";
		return str.matches(regex);
	}
}
